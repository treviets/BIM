package com.redsun.service.dao.impl;

import com.redsun.service.dao.AttachmentsDao;
import com.redsun.service.dao.mapper.AttachmentsRowMapper;
import com.redsun.service.entities.Attachments;
import com.redsun.service.exceptions.CustomSQLException;
import com.redsun.service.utils.RedSunColumnNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Attachments DAO implementation 
 */
@Repository
public class AttachmentsDaoImpl implements AttachmentsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private AttachmentsRowMapper attachmentsMapper;

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //ref_type varchar(100)
			java.sql.Types.INTEGER, //ref_id int4
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.TIMESTAMP, //creation_date timestamp
			java.sql.Types.VARCHAR, //file_name varchar(1024)
			java.sql.Types.VARCHAR, //description varchar(4000)
			java.sql.Types.VARCHAR, //sub_directory varchar(100)
			java.sql.Types.VARCHAR, //mime_type varchar(100)
			java.sql.Types.INTEGER, //file_size int4
			java.sql.Types.VARCHAR, //link varchar(1024)
			java.sql.Types.VARCHAR, //type varchar(10)
			java.sql.Types.INTEGER, //id_privacy int4
			java.sql.Types.INTEGER, //id_team int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into attachments (ref_type, ref_id, id_user, creation_date, file_name, description, sub_directory, mime_type, file_size, link, type, id_privacy, id_team, client_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update attachments set ref_type = ?, ref_id = ?, id_user = ?, creation_date = ?, file_name = ?, description = ?, sub_directory = ?, mime_type = ?, file_size = ?, link = ?, type = ?, id_privacy = ?, id_team = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from attachments where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from attachments";

	private final static String SQL_COUNT = 
		"select count(*) from attachments where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, ref_type, ref_id, id_user, creation_date, file_name, description, sub_directory, mime_type, file_size, link, type, id_privacy, id_team, client_id, 0 as ext_col_count from attachments where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, ref_type, ref_id, id_user, creation_date, file_name, description, sub_directory, mime_type, file_size, link, type, id_privacy, id_team, client_id, 1 as ext_col_count, count(*) over() as total_count from attachments where true";

	
	private final static String SQL_SELECT_BY_REFERENCE_ID =
		"select id, ref_type, ref_id, id_user, creation_date, file_name, description, sub_directory, mime_type, file_size, link, type, id_privacy, id_team, client_id, 1 as ext_col_count, count(*) over() as total_count from attachments where ref_id = ?";

	private final static String SQL_SELECT_BY_REFERENCE =
			"select id, ref_type, ref_id, id_user, creation_date, file_name, description, sub_directory, mime_type, file_size, link, type, id_privacy, id_team, client_id, 1 as ext_col_count, count(*) over() as total_count from attachments where ref_type = ? AND ref_id = ?";


	private final static String SQL_DELETE_BY_REFERENCE_ID = 
		"delete from attachments where ref_id = ?";

	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Attachments attachments) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{RedSunColumnNames.attachments_id});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(attachments));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Attachments attachments)  {
		return new Object[] {
			attachments.getRefType(), 			
			attachments.getRefId(), 			
			attachments.getIdUser(), 			
			attachments.getCreationDate(), 			
			attachments.getFileName(), 			
			attachments.getDescription(), 			
			attachments.getSubDirectory(), 			
			attachments.getMimeType(), 			
			attachments.getFileSize(), 			
			attachments.getLink(), 			
			attachments.getType(), 			
			attachments.getIdPrivacy(), 			
			attachments.getIdTeam(), 			
			attachments.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Attachments attachments) {
		return new Object[] {		
			attachments.getRefType(),
			attachments.getRefId(),
			attachments.getIdUser(),
			attachments.getCreationDate(),
			attachments.getFileName(),
			attachments.getDescription(),
			attachments.getSubDirectory(),
			attachments.getMimeType(),
			attachments.getFileSize(),
			attachments.getLink(),
			attachments.getType(),
			attachments.getIdPrivacy(),
			attachments.getIdTeam(),
			attachments.getClientId(),
			attachments.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Attachments attachments)  {
		return new Object[] {
			attachments.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Attachments attachments) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(attachments), keyHolder);

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
	public int update(final Attachments attachments) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(attachments));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return attachments.getId();
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
	public List<Attachments> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Attachments> result = jdbcTemplate.query(SQL_SELECT, primaryKey, attachmentsMapper);
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
	public List<Attachments> listAttachmentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Attachments attachments) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(attachments.getId() != null) {
			filterName = attachments.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by ref_type.
		if(attachments.getRefType() != null) {
			filterName = attachments.getRefType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(ref_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by ref_id.
		if(attachments.getRefId() != null) {
			filterName = attachments.getRefId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(ref_id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(attachments.getIdUser() != null) {
			filterName = attachments.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(attachments.getCreationDate() != null) {
			filterName = attachments.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by file_name.
		if(attachments.getFileName() != null) {
			filterName = attachments.getFileName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(file_name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(attachments.getDescription() != null) {
			filterName = attachments.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by sub_directory.
		if(attachments.getSubDirectory() != null) {
			filterName = attachments.getSubDirectory().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(sub_directory) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by mime_type.
		if(attachments.getMimeType() != null) {
			filterName = attachments.getMimeType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(mime_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by file_size.
		if(attachments.getFileSize() != null) {
			filterName = attachments.getFileSize().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(file_size) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by link.
		if(attachments.getLink() != null) {
			filterName = attachments.getLink().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(link) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by type.
		if(attachments.getType() != null) {
			filterName = attachments.getType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_privacy.
		if(attachments.getIdPrivacy() != null) {
			filterName = attachments.getIdPrivacy().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_privacy) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_team.
		if(attachments.getIdTeam() != null) {
			filterName = attachments.getIdTeam().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_team) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(attachments.getClientId() != null) {
			filterName = attachments.getClientId().toString();
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
		
		List<Attachments> result = jdbcTemplate.query(sql, params.toArray(), attachmentsMapper);
		return result;
	}


	@Override
	public int deleteByRefId(final Integer refId) {
		final int result = jdbcTemplate.update(SQL_DELETE_BY_REFERENCE_ID, new Object[] { refId });
		if (result != 0 && result != 1 && result != 2) {
			throw new CustomSQLException("Unexpected return value after DELETE : " + result + " (0 or 1 or 2 expected) ");
		}
		return result ;
	}

	@Override
	public List<Attachments> listAttachmentsByRefId(final Integer refId) {
		return jdbcTemplate.query(SQL_SELECT_BY_REFERENCE_ID, new Object[] { refId }, attachmentsMapper);
	}

	@Override
	public List<Attachments> listAttachmentsByRef(final String refType, final Integer refId) {
		return jdbcTemplate.query(SQL_SELECT_BY_REFERENCE, new Object[] { refType, refId }, attachmentsMapper);
	}

}
