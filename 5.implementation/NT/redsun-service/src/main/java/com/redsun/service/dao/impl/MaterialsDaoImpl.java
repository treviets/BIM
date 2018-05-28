package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.MaterialsDao;
import com.redsun.service.dao.mapper.MaterialsFilterMapper;
import com.redsun.service.dao.mapper.MaterialsMapper;
import com.redsun.service.entities.Materials;

@Repository
public class MaterialsDaoImpl implements MaterialsDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(MaterialsDaoImpl.class);

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //code 
			java.sql.Types.VARCHAR, //name 
			java.sql.Types.INTEGER, //unit
			java.sql.Types.DOUBLE, //quantity
			java.sql.Types.DOUBLE, //net_price
			java.sql.Types.DOUBLE, //price
			java.sql.Types.VARCHAR, //description
			java.sql.Types.INTEGER //client_id int4
	};
	private static String SQL_INSERT = "INSERT INTO materials (code, name, unit, quantity, net_price, price, description, client_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static String SQL_UPDATE = "UPDATE materials SET name=?, unit=?, quantity=?, net_price=?, price=?, description=? WHERE id = ?";
	private static String GET_BY_ID = "SELECT m.id, m.code, m.name, m.unit, units.name as unit_name, m.quantity, m.net_price, m.price, m.description, m.client_id, count(*) over() AS totalCount  FROM materials as m "
			+ "INNER JOIN units ON m.unit = units.id WHERE m.id = ? AND m.client_id = ?";
	private static String SQL_EXIST = "SELECT m.id, m.code, m.name, m.unit, units.name as unit_name, m.quantity, m.net_price, m.price, m.description, m.client_id, count(*) over() AS totalCount  FROM materials as m "
			+ "INNER JOIN units ON m.unit = units.id WHERE m.client_id = ? AND m.id != ?";
	
	private final static String SQL_DELETE = "DELETE FROM materials WHERE id = ?";
	private static String SELECT_ALL = "SELECT m.id, m.code, m.name, m.unit, units.name as unit_name, m.quantity, m.net_price, m.price, m.description, m.client_id, count(*) over() AS totalCount  FROM materials as m "
			+ "INNER JOIN units ON m.unit = units.id WHERE m.client_id = ?";
	private static String SQL_SELECT_PAGING = "SELECT m.id, m.code, m.name, m.unit, units.name as unit_name, m.quantity, m.net_price, m.price, m.description, m.client_id, count(*) over() AS totalCount  FROM materials as m "
			+ "INNER JOIN units ON m.unit = units.id WHERE m.client_id = ? ORDER BY m.name";
	private final static String SQL_SELECT_FILTER = "(SELECT m.id , m.name, u.name AS unit_name, m.net_price, m.price, m.client_id "
			+ "FROM materials as m INNER JOIN units AS u ON u.id = m.unit ) " + "EXCEPT " + "(SELECT m.id , m.name, u.name AS unit_name, m.net_price, m.price, m.client_id "
			+ "FROM materials as m INNER JOIN units AS u ON u.id = m.unit "
			+ "INNER JOIN project_materials as pm ON pm.material_id = m.id WHERE pm.project_id = ? and pm.client_id = ?)";
	private final static String SQL_COUNT_ALL = "select count(*) from materials where client_id =  ?";
	private final static String SQL_GET_BY_CODE = 
			"SELECT m.id, m.code, m.name, m.unit, units.name as unit_name, m.quantity, m.net_price, m.price, m.description, m.client_id, count(*) over() AS totalCount  FROM materials as m "
					+ "INNER JOIN units ON m.unit = units.id WHERE m.code = ? AND m.client_id = ?";
	
	private PreparedStatementCreator getPreparedStatementCreator(Materials material) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(material));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(Materials material)  {
		return new Object[] {
				material.getCode(),
				material.getName(),
				material.getUnit(),
				material.getQuantity(),
				material.getNetPrice(),
				material.getPrice(),
				material.getDescription(), 			
				material.getClientId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(Materials material)  {
		return new Object[] {
				material.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public int insert(Materials material) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(material), keyHolder);

		if (result != 1) {
			throw new RuntimeException("Unexpected return value after INSERT : " + result + " (1 expected) ");
		}
		
		Number key = keyHolder.getKey();
		if (key != null) {
			return key.intValue();
		}
		else {
			throw new RuntimeException("Cannot retrive generated key after INSERT : KeyHolder returns null");
		}
	}
	@Override
	public List<Materials> getAll(int clientId) {
		List<Materials> listMaterials = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listMaterials = jdbcTemplate.query(SELECT_ALL, params.toArray(), new MaterialsMapper());
			return listMaterials;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listMaterials;
	}

	@Override
	public List<Materials> filterMaterial(int projectId, int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		List<Materials> result = jdbcTemplate.query(SQL_SELECT_FILTER, params.toArray(), new MaterialsFilterMapper());
		return result;
	}

	@Override
	public List<Materials> listMaterialsForPage(int clientId, int itemsPerPage, int pageNo) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		// Paging.
		sql += " limit ? offset ?";
		params.add(clientId);
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Materials> result = jdbcTemplate.query(sql, params.toArray(), new MaterialsMapper());
		return result;
	}


	@Override
	public long count(int clientId) {
		Object[] primaryKey = new Object[] { clientId };
		return jdbcTemplate.queryForObject(SQL_COUNT_ALL, primaryKey, Long.class);
	}

	@Override
	public int update(Materials material) {
		int result = 0;
		try {

			result = jdbcTemplate.update(SQL_UPDATE,
					new Object[] {
							material.getName(),
							material.getUnit(),
							material.getQuantity(),
							material.getNetPrice(),
							material.getPrice(),
							material.getDescription(),
							material.getId()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int id) {
		Object[] primaryKey = new Object[] { id };
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

	@Override
	public Materials getById(int id, int clientId) {
		Object[] primaryKey = new Object[] { clientId, id };
		try {
			return jdbcTemplate.queryForObject(GET_BY_ID, primaryKey, new MaterialsMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<Materials> getByCode(String code, int clientId){
		Object[] primaryKey = new Object[] { code, clientId };
		return jdbcTemplate.query(SQL_GET_BY_CODE, primaryKey, new MaterialsMapper());
	}

	@Override
	public List<Materials> checkExist(int clientId, int id) {
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(id);
		
		List<Materials> result = jdbcTemplate.query(SQL_EXIST, params.toArray(), new MaterialsMapper());
		return result;
	}

}