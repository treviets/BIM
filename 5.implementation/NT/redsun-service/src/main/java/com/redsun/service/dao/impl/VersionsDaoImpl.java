package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.VersionsDao;
import com.redsun.service.dao.mapper.VersionsRowMapper;
import com.redsun.service.entities.Versions;

/**
 * Versions DAO implementation 
 */
@Repository
public class VersionsDaoImpl implements VersionsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private VersionsRowMapper versionsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_product int4
			java.sql.Types.INTEGER, //id_contact int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.DATE, //initial_eis_date date
			java.sql.Types.DATE, //planned_eis_date date
			java.sql.Types.DATE, //real_eis_date date
			java.sql.Types.DATE, //initial_end_date date
			java.sql.Types.DATE, //planned_end_date date
			java.sql.Types.DATE, //real_end_date date
			java.sql.Types.INTEGER, //is_eis bit
			java.sql.Types.VARCHAR, //scope varchar(100)
			java.sql.Types.VARCHAR, //version_number varchar(100)
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.INTEGER, //id_version_type int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into versions (id_product, id_contact, id_resource, name, description, creation_date, idle, initial_eis_date, planned_eis_date, real_eis_date, initial_end_date, planned_end_date, real_end_date, is_eis, scope, version_number, id_user, id_version_type, client_id) values (?, ?, ?, ?, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update versions set id_product = ?, id_contact = ?, id_resource = ?, name = ?, description = ?, creation_date = ?, idle = ?::bit, initial_eis_date = ?, planned_eis_date = ?, real_eis_date = ?, initial_end_date = ?, planned_end_date = ?, real_end_date = ?, is_eis = ?::bit, scope = ?, version_number = ?, id_user = ?, id_version_type = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from versions where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from versions";

	private final static String SQL_COUNT = 
		"select count(*) from versions where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_product, id_contact, id_resource, name, description, creation_date, idle, initial_eis_date, planned_eis_date, real_eis_date, initial_end_date, planned_end_date, real_end_date, is_eis, scope, version_number, id_user, id_version_type, client_id, 0 as ext_col_count from versions where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, id_product, id_contact, id_resource, name, description, creation_date, idle, initial_eis_date, planned_eis_date, real_eis_date, initial_end_date, planned_end_date, real_end_date, is_eis, scope, version_number, id_user, id_version_type, client_id, 1 as ext_col_count, count(*) over() as total_count from versions where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Versions versions) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(versions));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Versions versions)  {
		return new Object[] {
			versions.getIdProduct(), 			
			versions.getIdContact(), 			
			versions.getIdResource(), 			
			versions.getName(), 			
			versions.getDescription(), 			
			versions.getCreationDate(), 			
			versions.getIdle(), 			
			versions.getInitialEisDate(), 			
			versions.getPlannedEisDate(), 			
			versions.getRealEisDate(), 			
			versions.getInitialEndDate(), 			
			versions.getPlannedEndDate(), 			
			versions.getRealEndDate(), 			
			versions.getIsEis(), 			
			versions.getScope(), 			
			versions.getVersionNumber(), 			
			versions.getIdUser(), 			
			versions.getIdVersionType(), 			
			versions.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Versions versions) {
		return new Object[] {		
			versions.getIdProduct(),
			versions.getIdContact(),
			versions.getIdResource(),
			versions.getName(),
			versions.getDescription(),
			versions.getCreationDate(),
			versions.getIdle(),
			versions.getInitialEisDate(),
			versions.getPlannedEisDate(),
			versions.getRealEisDate(),
			versions.getInitialEndDate(),
			versions.getPlannedEndDate(),
			versions.getRealEndDate(),
			versions.getIsEis(),
			versions.getScope(),
			versions.getVersionNumber(),
			versions.getIdUser(),
			versions.getIdVersionType(),
			versions.getClientId(),
			versions.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Versions versions)  {
		return new Object[] {
			versions.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Versions versions) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(versions), keyHolder);

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

    // Update.
	@Override
	public int update(final Versions versions) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(versions));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return versions.getId();
	}	

    // Delete.
	@Override
	public int delete(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		int result = jdbcTemplate.update(SQL_DELETE, primaryKey);
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after DELETE : " + result + " (0 or 1 expected) ");
		}
		return id ;
	}

	// Get By Id.
	@Override
	public List<Versions> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Versions> result = jdbcTemplate.query(SQL_SELECT, primaryKey, versionsMapper);
		return result;
	}

    // Exists.
	@Override
	public boolean exists(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		long count = jdbcTemplate.queryForObject(SQL_COUNT, primaryKey, Long.class);
		return count > 0 ;
	}

    // Count.
	@Override
	public long count() {
		return jdbcTemplate.queryForObject(SQL_COUNT_ALL, Long.class);
	}

	// ToString.
	protected String toString(final Object[] objects) {
		if (objects != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			int i = 0 ;
			for (Object o : objects ) {
				if ( i > 0 ) {
					sb.append(", ");
				}
				sb.append(o.toString());
				i++;
			}
			sb.append(")");
			return sb.toString();
		}
		else {
			return "null";
		}
	}

	// List for page and filter.
	public List<Versions> listVersionsForPageAndFilter(final int itemsPerPage, final int pageNo, final Versions versions) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(versions.getId() != null) {
			filterName = versions.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_product.
		if(versions.getIdProduct() != null) {
			filterName = versions.getIdProduct().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_product) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_contact.
		if(versions.getIdContact() != null) {
			filterName = versions.getIdContact().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_contact) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(versions.getIdResource() != null) {
			filterName = versions.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(versions.getName() != null) {
			filterName = versions.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(versions.getDescription() != null) {
			filterName = versions.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(versions.getCreationDate() != null) {
			filterName = versions.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(versions.getIdle() != null) {
			filterName = versions.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_eis_date.
		if(versions.getInitialEisDate() != null) {
			filterName = versions.getInitialEisDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_eis_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by planned_eis_date.
		if(versions.getPlannedEisDate() != null) {
			filterName = versions.getPlannedEisDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(planned_eis_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by real_eis_date.
		if(versions.getRealEisDate() != null) {
			filterName = versions.getRealEisDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(real_eis_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by initial_end_date.
		if(versions.getInitialEndDate() != null) {
			filterName = versions.getInitialEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(initial_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by planned_end_date.
		if(versions.getPlannedEndDate() != null) {
			filterName = versions.getPlannedEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(planned_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by real_end_date.
		if(versions.getRealEndDate() != null) {
			filterName = versions.getRealEndDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(real_end_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by is_eis.
		if(versions.getIsEis() != null) {
			filterName = versions.getIsEis().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(is_eis) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by scope.
		if(versions.getScope() != null) {
			filterName = versions.getScope().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(scope) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by version_number.
		if(versions.getVersionNumber() != null) {
			filterName = versions.getVersionNumber().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(version_number) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(versions.getIdUser() != null) {
			filterName = versions.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_version_type.
		if(versions.getIdVersionType() != null) {
			filterName = versions.getIdVersionType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_version_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(versions.getClientId() != null) {
			filterName = versions.getClientId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(client_id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		
		if(filterSql.equals("") == false) {
			sql += " and (false" + filterSql + ")";
		}
		
		// Paging.
		sql += " ORDER BY id ASC limit ? offset ?";
		params.add(itemsPerPage);
		params.add(offset);
		
		List<Versions> result = jdbcTemplate.query(sql, params.toArray(), versionsMapper);
		return result;
	}

}
