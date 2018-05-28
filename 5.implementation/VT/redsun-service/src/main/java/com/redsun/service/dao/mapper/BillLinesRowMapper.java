package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.BillLines;

/**
 * BillLines Mapper
 */
@Component
public class BillLinesRowMapper implements RowMapper<BillLines> {

	public BillLines mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		BillLines billLines = new BillLines();
		billLines.setId(rs.getInt("id"));
		if(rs.wasNull()) { billLines.setId(null); };
		billLines.setLine(rs.getInt("line"));
		if(rs.wasNull()) { billLines.setLine(null); };
		billLines.setQuantity(rs.getBigDecimal("quantity"));
		if(rs.wasNull()) { billLines.setQuantity(null); };
		billLines.setDescription(rs.getString("description"));
		billLines.setDetail(rs.getString("detail"));
		billLines.setPrice(rs.getBigDecimal("price"));
		if(rs.wasNull()) { billLines.setPrice(null); };
		billLines.setAmount(rs.getBigDecimal("amount"));
		if(rs.wasNull()) { billLines.setAmount(null); };
		billLines.setRefType(rs.getString("ref_type"));
		billLines.setRefId(rs.getInt("ref_id"));
		if(rs.wasNull()) { billLines.setRefId(null); };
		billLines.setIdTerm(rs.getInt("id_term"));
		if(rs.wasNull()) { billLines.setIdTerm(null); };
		billLines.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { billLines.setIdResource(null); };
		billLines.setIdActivityPrice(rs.getInt("id_activity_price"));
		if(rs.wasNull()) { billLines.setIdActivityPrice(null); };
		billLines.setStartDate(rs.getDate("start_date"));
		billLines.setEndDate(rs.getDate("end_date"));
		billLines.setIdMeasureUnit(rs.getInt("id_measure_unit"));
		if(rs.wasNull()) { billLines.setIdMeasureUnit(null); };
		billLines.setExtra(rs.getInt("extra"));
		billLines.setBillingType(rs.getString("billing_type"));
		billLines.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { billLines.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			billLines.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return billLines;
	}
}

