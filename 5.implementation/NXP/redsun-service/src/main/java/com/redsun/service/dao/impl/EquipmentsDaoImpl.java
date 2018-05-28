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

import com.redsun.service.dao.EquipmentsDao;
import com.redsun.service.dao.mapper.EquipmentsFilterMapper;
import com.redsun.service.dao.mapper.EquipmentsMapper;
import com.redsun.service.entities.Equipments;

@Repository
public class EquipmentsDaoImpl implements EquipmentsDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private static Logger log = Logger.getLogger(EquipmentsDaoImpl.class);
	

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //code 
			java.sql.Types.VARCHAR, //name 
			java.sql.Types.INTEGER, //unit
			java.sql.Types.INTEGER, //quantity
			java.sql.Types.DOUBLE, //net_price
			java.sql.Types.DOUBLE, //price
			java.sql.Types.VARCHAR, //description
			java.sql.Types.INTEGER //client_id int4
	};
	private static String SQL_UPDATE = "UPDATE equipments SET name=?, unit=?, quantity=?, net_price=?, price=?, description=? WHERE id = ?";
	private static String SQL_SELECT_PAGING ="SELECT e.id, e.code, e.name, e.unit, units.name as unit_name, e.quantity, e.net_price, e.price, e.description, e.client_id, count(*) over() AS totalCount  FROM equipments as e "
			+ "INNER JOIN units ON e.unit = units.id WHERE e.client_id = ?";
	private static String SQL_INSERT = "INSERT INTO equipments (code, name, unit, quantity, net_price, price, description, client_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static String SELECT_ALL = "SELECT e.id, e.code, e.name, e.unit, units.name as unit_name, e.quantity, e.net_price, e.price, e.description, e.client_id, count(*) over() AS totalCount  FROM equipments as e "
			+ "INNER JOIN units ON e.unit = units.id WHERE e.client_id = ?";
	private static String GET_BY_ID = "SELECT e.id, e.code, e.name, e.unit, units.name as unit_name, e.quantity, e.net_price, e.price, e.description, e.client_id, count(*) over() AS totalCount  FROM equipments as e "
			+ "INNER JOIN units ON e.unit = units.id WHERE e.id = ? AND e.client_id = ?";
	private static String SQL_EXIST = "SELECT e.id, e.code, e.name, e.unit, units.name as unit_name, e.quantity, e.net_price, e.price, e.description, e.client_id, count(*) over() AS totalCount  FROM equipments as e "
			+ "INNER JOIN units ON e.unit = units.id WHERE e.client_id = ? AND e.id != ?";
	private final static String SQL_COUNT_ALL = "select count(*) from equipments where client_id =  ?";
	private final static String SQL_GET_BY_CODE = "SELECT e.id, e.code, e.name, e.unit, units.name as unit_name, e.quantity, e.net_price, e.price, e.description, e.client_id, count(*) over() AS totalCount  FROM equipments as e "
			+ "INNER JOIN units ON e.unit = units.id WHERE e.code = ? AND e.client_id = ?";
	
	private final static String SQL_SELECT_FILTER = "(SELECT e.id, e.name, u.name as unit_name, e.net_price, e.price, e.client_id "
			+ "FROM equipments AS e INNER JOIN units AS u ON u.id = e.unit ) " 
			+ "EXCEPT "
			+ "(SELECT e.id , e.name, u.name as unit_name, e.net_price, e.price, e.client_id "
			+ "FROM equipments as e  INNER JOIN units AS u ON u.id = e.unit " 
			+ "INNER JOIN project_equipments as pe ON pe.equipment_id = e.id WHERE pe.project_id = ? and pe.client_id = ?)";
	private final static String SQL_DELETE = "DELETE FROM equipments WHERE id = ?";
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Equipments equipment) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(equipment));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final  Equipments equipment)  {
		return new Object[] {
				equipment.getCode(),
				equipment.getName(),
				equipment.getUnit(),
				equipment.getQuantity(),
				equipment.getNetPrice(),
				equipment.getPrice(),
				equipment.getDescription(), 			
				equipment.getClientId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final  Equipments equipment)  {
		return new Object[] {
				equipment.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public int insert(final Equipments equipment) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(equipment), keyHolder);

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
	public List<Equipments> getAll(int clientId) {
		List<Equipments> listEquipments = null;
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		try {
			listEquipments = jdbcTemplate.query(SELECT_ALL, params.toArray(), new EquipmentsMapper());
			return listEquipments;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listEquipments;
	}

	@Override
	public List<Equipments> filterEquipment(int projectId, int clientId) {
		List<Object> params = new ArrayList<Object>();
		params.add(projectId);
		params.add(clientId);
		List<Equipments> result = jdbcTemplate.query(SQL_SELECT_FILTER, params.toArray(), new EquipmentsFilterMapper());
		return result;
	}


	@Override
	public List<Equipments> listEquipmentsForPage(int clientId, int itemsPerPage, int pageNo) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		// Paging.
		sql += " ORDER BY id ASC limit ? offset ?";
		params.add(clientId);
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Equipments> result = jdbcTemplate.query(sql, params.toArray(), new EquipmentsMapper());
		return result;
	}

	@Override
	public long count(int clientId) {
		Object[] primaryKey = new Object[] { clientId };
		return jdbcTemplate.queryForObject(SQL_COUNT_ALL, primaryKey, Long.class);
	}

	@Override
	public int update(Equipments equipment) {
		int result = 0;
		try {

			result = jdbcTemplate.update(SQL_UPDATE,
					new Object[] {
							equipment.getName(),
							equipment.getUnit(),
							equipment.getQuantity(),
							equipment.getNetPrice(),
							equipment.getPrice(),
							equipment.getDescription(),
							equipment.getId()
							});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Equipments getById(int id, int clientId) {
		Object[] primaryKey = new Object[] { clientId, id };
		try {
			return jdbcTemplate.queryForObject(GET_BY_ID, primaryKey, new EquipmentsMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<Equipments> getByCode(String code, int clientId){
		Object[] primaryKey = new Object[] { code, clientId };
		return jdbcTemplate.query(SQL_GET_BY_CODE, primaryKey, new EquipmentsMapper());
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
	public List<Equipments> checkExist(int clientId, int id) {
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(id);
		
		List<Equipments> result = jdbcTemplate.query(SQL_EXIST, params.toArray(), new EquipmentsMapper());
		return result;
}
}