package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Quotations;

/**
 * Quotations Mapper
 */
@Component
public class QuotationsRowMapper implements RowMapper<Quotations> {
	
	@Autowired
	Quotations quotations;

	public Quotations mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		quotations.setId(rs.getInt("id"));
		if(rs.wasNull()) { quotations.setId(null); };
		quotations.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { quotations.setIdProject(null); };
		quotations.setIdQuotationType(rs.getInt("id_quotation_type"));
		if(rs.wasNull()) { quotations.setIdQuotationType(null); };
		quotations.setName(rs.getString("name"));
		quotations.setDescription(rs.getString("description"));
		quotations.setCreationDate(rs.getDate("creation_date"));
		quotations.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { quotations.setIdUser(null); };
		quotations.setIdStatus(rs.getInt("id_status"));
		if(rs.wasNull()) { quotations.setIdStatus(null); };
		quotations.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { quotations.setIdResource(null); };
		quotations.setIdClient(rs.getInt("id_client"));
		if(rs.wasNull()) { quotations.setIdClient(null); };
		quotations.setIdContact(rs.getInt("id_contact"));
		if(rs.wasNull()) { quotations.setIdContact(null); };
		quotations.setAdditionalInfo(rs.getString("additional_info"));
		quotations.setInitialEndDate(rs.getDate("initial_end_date"));
		quotations.setUntaxedAmount(rs.getBigDecimal("untaxed_amount"));
		if(rs.wasNull()) { quotations.setUntaxedAmount(null); };
		quotations.setInitialPricePerDayAmount(rs.getBigDecimal("initial_price_per_day_amount"));
		if(rs.wasNull()) { quotations.setInitialPricePerDayAmount(null); };
		quotations.setInitialAmount(rs.getBigDecimal("initial_amount"));
		if(rs.wasNull()) { quotations.setInitialAmount(null); };
		quotations.setComment(rs.getString("comment"));
		quotations.setIdle(rs.getInt("idle"));
		quotations.setDone(rs.getInt("done"));
		quotations.setCancelled(rs.getInt("cancelled"));
		quotations.setIdleDate(rs.getDate("idle_date"));
		quotations.setDoneDate(rs.getDate("done_date"));
		quotations.setHandled(rs.getInt("handled"));
		quotations.setHandledDate(rs.getDate("handled_date"));
		quotations.setReference(rs.getString("reference"));
		quotations.setSendDate(rs.getDate("send_date"));
		quotations.setValidityEndDate(rs.getDate("validity_end_date"));
		quotations.setIdActivityType(rs.getInt("id_activity_type"));
		if(rs.wasNull()) { quotations.setIdActivityType(null); };
		quotations.setResult(rs.getString("result"));
		quotations.setIdPaymentDelay(rs.getInt("id_payment_delay"));
		if(rs.wasNull()) { quotations.setIdPaymentDelay(null); };
		quotations.setTax(rs.getBigDecimal("tax"));
		if(rs.wasNull()) { quotations.setTax(null); };
		quotations.setFullAmount(rs.getBigDecimal("full_amount"));
		if(rs.wasNull()) { quotations.setFullAmount(null); };
		quotations.setIdDeliveryMode(rs.getInt("id_delivery_mode"));
		if(rs.wasNull()) { quotations.setIdDeliveryMode(null); };
		quotations.setIdLikelihood(rs.getInt("id_likelihood"));
		if(rs.wasNull()) { quotations.setIdLikelihood(null); };
		quotations.setPlannedWork(rs.getBigDecimal("planned_work"));
		if(rs.wasNull()) { quotations.setPlannedWork(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			quotations.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return quotations;
	}
}

