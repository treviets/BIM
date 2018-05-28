package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Products;

/**
 * Products Mapper
 */
@Component
public class ProductsRowMapper implements RowMapper<Products> {

	public Products mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Products products = new Products();
		products.setId(rs.getInt("id"));
		if(rs.wasNull()) { products.setId(null); };
		products.setName(rs.getString("name"));
		products.setIdClient(rs.getInt("id_client"));
		if(rs.wasNull()) { products.setIdClient(null); };
		products.setIdContact(rs.getInt("id_contact"));
		if(rs.wasNull()) { products.setIdContact(null); };
		products.setDescription(rs.getString("description"));
		products.setCreationDate(rs.getDate("creation_date"));
		products.setIdle(rs.getInt("idle"));
		products.setIdProduct(rs.getInt("id_product"));
		if(rs.wasNull()) { products.setIdProduct(null); };
		products.setDesignation(rs.getString("designation"));
		products.setScope(rs.getString("scope"));
		products.setIdProductType(rs.getInt("id_product_type"));
		if(rs.wasNull()) { products.setIdProductType(null); };
		products.setIdComponentType(rs.getInt("id_component_type"));
		if(rs.wasNull()) { products.setIdComponentType(null); };
		products.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { products.setIdResource(null); };
		products.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { products.setIdUser(null); };
		products.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { products.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			products.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return products;
	}
}

