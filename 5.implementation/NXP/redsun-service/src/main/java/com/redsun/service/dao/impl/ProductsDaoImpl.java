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

import com.redsun.service.dao.ProductsDao;
import com.redsun.service.dao.mapper.ProductsRowMapper;
import com.redsun.service.entities.Products;

/**
 * Products DAO implementation 
 */
@Repository
public class ProductsDaoImpl implements ProductsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Autowired
    private ProductsRowMapper productsMapper;

	private final static String AUTO_INCREMENTED_COLUMN = "id";

	private final static int[] SQL_INSERT_TYPES = new int[] {
			java.sql.Types.VARCHAR, //name varchar(100)
			java.sql.Types.INTEGER, //id_client int4
			java.sql.Types.INTEGER, //id_contact int4
			java.sql.Types.VARCHAR, //description text(2147483647)
			java.sql.Types.DATE, //creation_date date
			java.sql.Types.INTEGER, //idle bit
			java.sql.Types.INTEGER, //id_product int4
			java.sql.Types.VARCHAR, //designation varchar(100)
			java.sql.Types.VARCHAR, //scope varchar(100)
			java.sql.Types.INTEGER, //id_product_type int4
			java.sql.Types.INTEGER, //id_component_type int4
			java.sql.Types.INTEGER, //id_resource int4
			java.sql.Types.INTEGER, //id_user int4
			java.sql.Types.INTEGER //client_id int4
	};

	private final static String SQL_INSERT = 
		"insert into products (name, id_client, id_contact, description, creation_date, idle, id_product, designation, scope, id_product_type, id_component_type, id_resource, id_user, client_id) values (?, ?, ?, ?, ?, ?::bit, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_UPDATE = 
		"update products set name = ?, id_client = ?, id_contact = ?, description = ?, creation_date = ?, idle = ?::bit, id_product = ?, designation = ?, scope = ?, id_product_type = ?, id_component_type = ?, id_resource = ?, id_user = ?, client_id = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from products where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from products";

	private final static String SQL_COUNT = 
		"select count(*) from products where id = ?";
	
	private final static String SQL_SELECT = 
		"select id, name, id_client, id_contact, description, creation_date, idle, id_product, designation, scope, id_product_type, id_component_type, id_resource, id_user, client_id, 0 as ext_col_count from products where id = ?";
	
	private final static String SQL_SELECT_PAGING = 
		"select id, name, id_client, id_contact, description, creation_date, idle, id_product, designation, scope, id_product_type, id_component_type, id_resource, id_user, client_id, 1 as ext_col_count, count(*) over() as total_count from products where true";

	
	// Get Prepared Statement Creator.
	private PreparedStatementCreator getPreparedStatementCreator(final Products products) {
		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(SQL_INSERT, SQL_INSERT_TYPES) ;
		
		factory.setGeneratedKeysColumnNames(new String[]{AUTO_INCREMENTED_COLUMN});
		PreparedStatementCreator psc = factory.newPreparedStatementCreator(getValuesForInsert(products));
		return psc ;
	}
	
	// Get Values For Insert.
	protected Object[] getValuesForInsert(final Products products)  {
		return new Object[] {
			products.getName(), 			
			products.getIdClient(), 			
			products.getIdContact(), 			
			products.getDescription(), 			
			products.getCreationDate(), 			
			products.getIdle(), 			
			products.getIdProduct(), 			
			products.getDesignation(), 			
			products.getScope(), 			
			products.getIdProductType(), 			
			products.getIdComponentType(), 			
			products.getIdResource(), 			
			products.getIdUser(), 			
			products.getClientId()			
		};
	}
	
    // Get Values For Update.
	protected Object[] getValuesForUpdate(final Products products) {
		return new Object[] {		
			products.getName(),
			products.getIdClient(),
			products.getIdContact(),
			products.getDescription(),
			products.getCreationDate(),
			products.getIdle(),
			products.getIdProduct(),
			products.getDesignation(),
			products.getScope(),
			products.getIdProductType(),
			products.getIdComponentType(),
			products.getIdResource(),
			products.getIdUser(),
			products.getClientId(),
			products.getId()			
		};
	}
	
	//getValuesForPrimaryKey
	protected Object[] getValuesForPrimaryKey(final Products products)  {
		return new Object[] {
			products.getId() // id: java.lang.Integer
		};
	}
	
    // Insert.
	@Override
	public Integer insert(final Products products) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		
		int result = jdbcTemplate.update(getPreparedStatementCreator(products), keyHolder);

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
	public int update(final Products products) {
		int result = jdbcTemplate.update(SQL_UPDATE, getValuesForUpdate(products));
		if (result != 0 && result != 1) {
			throw new RuntimeException("Unexpected return value after UPDATE : " + result + " (0 or 1 expected) ");
		}
		return products.getId();
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
	public List<Products> getById(final Integer id) {
		Object[] primaryKey = new Object[] { id };
		List<Products> result = jdbcTemplate.query(SQL_SELECT, primaryKey, productsMapper);
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
	public List<Products> listProductsForPageAndFilter(final int itemsPerPage, final int pageNo, final Products products) {
		int offset = (pageNo - 1) * itemsPerPage;
		String sql = SQL_SELECT_PAGING;
		List<Object> params = new ArrayList<Object>();
		
		String filterSql = "";
		String filterName = "";

		// Filter by id.
		if(products.getId() != null) {
			filterName = products.getId().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by name.
		if(products.getName() != null) {
			filterName = products.getName().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(name) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_client.
		if(products.getIdClient() != null) {
			filterName = products.getIdClient().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_client) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_contact.
		if(products.getIdContact() != null) {
			filterName = products.getIdContact().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_contact) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by description.
		if(products.getDescription() != null) {
			filterName = products.getDescription().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(description) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by creation_date.
		if(products.getCreationDate() != null) {
			filterName = products.getCreationDate().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(creation_date) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by idle.
		if(products.getIdle() != null) {
			filterName = products.getIdle().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(idle) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_product.
		if(products.getIdProduct() != null) {
			filterName = products.getIdProduct().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_product) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by designation.
		if(products.getDesignation() != null) {
			filterName = products.getDesignation().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(designation) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by scope.
		if(products.getScope() != null) {
			filterName = products.getScope().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(scope) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_product_type.
		if(products.getIdProductType() != null) {
			filterName = products.getIdProductType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_product_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_component_type.
		if(products.getIdComponentType() != null) {
			filterName = products.getIdComponentType().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_component_type) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_resource.
		if(products.getIdResource() != null) {
			filterName = products.getIdResource().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_resource) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by id_user.
		if(products.getIdUser() != null) {
			filterName = products.getIdUser().toString();
			if(filterName.equals("") == false) {
				filterSql += " or UPPER(id_user) like UPPER(?)";
				params.add("%" + filterName + "%");
			}
		}

		// Filter by client_id.
		if(products.getClientId() != null) {
			filterName = products.getClientId().toString();
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
		
		List<Products> result = jdbcTemplate.query(sql, params.toArray(), productsMapper);
		return result;
	}

}
