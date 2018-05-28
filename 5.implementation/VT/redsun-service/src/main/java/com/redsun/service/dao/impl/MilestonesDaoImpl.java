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

import com.redsun.service.dao.MilestonesDao;
import com.redsun.service.dao.mapper.MilestonesRowMapper;
import com.redsun.service.entities.Milestones;

/**
 * Milestones DAO implementation 
 */
@Repository
public class MilestonesDaoImpl implements MilestonesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private MilestonesRowMapper milestonesMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.INTEGER, //id_project int4
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.INTEGER, //id_status int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.VARCHAR, //result text(2147483647)
			java.sql.Types.VARCHAR, //comment varchar(4000)
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //id_milestone_type int4
			java.sql.Types.INTEGER, //id_activity int4
			java.sql.Types.INTEGER, //done bit
			java.sql.Types.DATE, //idle_date date
			java.sql.Types.DATE, //done_date date
			java.sql.Types.INTEGER, //handled bit
			java.sql.Types.DATE, //handled_date date
			java.sql.Types.INTEGER, //id_version int4
			java.sql.Types.VARCHAR, //reference varchar(100)
			java.sql.Types.VARCHAR, //external_reference varchar(100)
			java.sql.Types.INTEGER, //cancelled bit
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into milestones (id_project, name, description, creation_date, id_user, id_status, id_resource, result, comment, idle, id_milestone_type, id_activity, done, idle_date, done_date, handled, handled_date, id_version, reference, external_reference, cancelled, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?::bit, ?, ?, ?::bit, ?, ?, ?::bit, ?, ?, ?, ?, ?::bit, ?)";

	private final static String SQL_UPDATE = 
		"update milestones set id_project = ?, name = ?, description = ?, creation_date = ?, id_user = ?, id_status = ?, id_resource = ?, result = ?, comment = ?, idle = ?::bit, id_milestone_type = ?, id_activity = ?, done = ?::bit, idle_date = ?, done_date = ?, handled = ?::bit, handled_date = ?, id_version = ?, reference = ?, external_reference = ?, cancelled = ?::bit, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from milestones where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from milestones";

	private final static String SQL_COUNT = 
		"select count(*) from milestones where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, id_project, name, description, creation_date, id_user, id_status, id_resource, result, comment, idle, id_milestone_type, id_activity, done, idle_date, done_date, handled, handled_date, id_version, reference, external_reference, cancelled, client_id, 0 as ext_col_count from milestones where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, id_project, name, description, creation_date, id_user, id_status, id_resource, result, comment, idle, id_milestone_type, id_activity, done, idle_date, done_date, handled, handled_date, id_version, reference, external_reference, cancelled, client_id, 1 as ext_col_count, count(*) over() as total_count from milestones where true";

	private final static String SQL_SELECT_BY_PROJECTID = 
		"select id, id_project, name, description, creation_date, id_user, id_status, id_resource, result, comment, idle, id_milestone_type, id_activity, done, idle_date, done_date, handled, handled_date, id_version, reference, external_reference, cancelled, client_id, 0 as ext_col_count from milestones where id_project = ?";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Milestones milestones) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(milestones));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Milestones milestones)  {
		return new Object[] {
			milestones.getIdProject(), 			
			milestones.getName(), 			
			milestones.getDescription(), 			
			milestones.getCreationDate(), 			
			milestones.getIdUser(), 			
			milestones.getIdStatus(), 			
			milestones.getIdResource(), 			
			milestones.getResult(), 			
			milestones.getComment(), 			
			milestones.getIdle(), 			
			milestones.getIdMilestoneType(), 			
			milestones.getIdActivity(), 			
			milestones.getDone(), 			
			milestones.getIdleDate(), 			
			milestones.getDoneDate(), 			
			milestones.getHandled(), 			
			milestones.getHandledDate(), 			
			milestones.getIdVersion(), 			
			milestones.getReference(), 			
			milestones.getExternalReference(), 			
			milestones.getCancelled(), 			
			milestones.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Milestones milestones) {
		return new Object[] {		
			milestones.getIdProject(),
			milestones.getName(),
			milestones.getDescription(),
			milestones.getCreationDate(),
			milestones.getIdUser(),
			milestones.getIdStatus(),
			milestones.getIdResource(),
			milestones.getResult(),
			milestones.getComment(),
			milestones.getIdle(),
			milestones.getIdMilestoneType(),
			milestones.getIdActivity(),
			milestones.getDone(),
			milestones.getIdleDate(),
			milestones.getDoneDate(),
			milestones.getHandled(),
			milestones.getHandledDate(),
			milestones.getIdVersion(),
			milestones.getReference(),
			milestones.getExternalReference(),
			milestones.getCancelled(),
			milestones.getClientId(),
			milestones.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Milestones milestones)  {
		return new Object[] {
			milestones.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Milestones milestones) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(milestones), keyHolder);

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
	public int update(final Milestones milestones) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(milestones));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return milestones.getId();
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
	public List<Milestones> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Milestones> result = jdbcTemplate.query(SQL_SELECT, primaryKey, milestonesMapper);
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
	public List<Milestones> listMilestonesForPageAndFilter(final int itemsPerPage, final int pageNo, final Milestones milestones) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(milestones.getId() != null) {
			filterName = milestones.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_project.
		if(milestones.getIdProject() != null) {
			filterName = milestones.getIdProject().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_project) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(milestones.getName() != null) {
			filterName = milestones.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(milestones.getDescription() != null) {
			filterName = milestones.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(milestones.getCreationDate() != null) {
			filterName = milestones.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(milestones.getIdUser() != null) {
			filterName = milestones.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_status.
		if(milestones.getIdStatus() != null) {
			filterName = milestones.getIdStatus().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_status) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(milestones.getIdResource() != null) {
			filterName = milestones.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by result.
		if(milestones.getResult() != null) {
			filterName = milestones.getResult().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(result) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by comment.
		if(milestones.getComment() != null) {
			filterName = milestones.getComment().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(comment) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(milestones.getIdle() != null) {
			filterName = milestones.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_milestone_type.
		if(milestones.getIdMilestoneType() != null) {
			filterName = milestones.getIdMilestoneType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_milestone_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_activity.
		if(milestones.getIdActivity() != null) {
			filterName = milestones.getIdActivity().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_activity) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by done.
		if(milestones.getDone() != null) {
			filterName = milestones.getDone().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(done) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle_date.
		if(milestones.getIdleDate() != null) {
			filterName = milestones.getIdleDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by done_date.
		if(milestones.getDoneDate() != null) {
			filterName = milestones.getDoneDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(done_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by handled.
		if(milestones.getHandled() != null) {
			filterName = milestones.getHandled().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(handled) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by handled_date.
		if(milestones.getHandledDate() != null) {
			filterName = milestones.getHandledDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(handled_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_version.
		if(milestones.getIdVersion() != null) {
			filterName = milestones.getIdVersion().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_version) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by reference.
		if(milestones.getReference() != null) {
			filterName = milestones.getReference().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(reference) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by external_reference.
		if(milestones.getExternalReference() != null) {
			filterName = milestones.getExternalReference().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(external_reference) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by cancelled.
		if(milestones.getCancelled() != null) {
			filterName = milestones.getCancelled().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(cancelled) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(milestones.getClientId() != null) {
			filterName = milestones.getClientId().toString();
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
		
		List<Milestones> result = jdbcTemplate.query(sql, params.toArray(), milestonesMapper);
		return result;
	}
	
	// List by projectId
	public List<Milestones> listMilestonesByProjectId(final int projectId) {
		List<Milestones> result = jdbcTemplate.query(SQL_SELECT_BY_PROJECTID, new Object[] { projectId }, milestonesMapper);
		return result;
	}
}
