package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Commands;

/**
 * Commands Mapper
 */
@Component
public class CommandsRowMapper implements RowMapper<Commands> {

	public Commands mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Commands commands = new Commands();
		commands.setId(rs.getInt("id"));
		if(rs.wasNull()) { commands.setId(null); };
		commands.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { commands.setIdProject(null); };
		commands.setIdCommandType(rs.getInt("id_command_type"));
		if(rs.wasNull()) { commands.setIdCommandType(null); };
		commands.setName(rs.getString("name"));
		commands.setDescription(rs.getString("description"));
		commands.setCreationDate(rs.getDate("creation_date"));
		commands.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { commands.setIdUser(null); };
		commands.setIdStatus(rs.getInt("id_status"));
		if(rs.wasNull()) { commands.setIdStatus(null); };
		commands.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { commands.setIdResource(null); };
		commands.setAdditionalInfo(rs.getString("additional_info"));
		commands.setExternalReference(rs.getString("external_reference"));
		commands.setIdActivity(rs.getInt("id_activity"));
		if(rs.wasNull()) { commands.setIdActivity(null); };
		commands.setInitialStartDate(rs.getDate("initial_start_date"));
		commands.setInitialEndDate(rs.getDate("initial_end_date"));
		commands.setValidatedEndDate(rs.getDate("validated_end_date"));
		commands.setInitialWork(rs.getBigDecimal("initial_work"));
		if(rs.wasNull()) { commands.setInitialWork(null); };
		commands.setInitialPricePerDayAmount(rs.getBigDecimal("initial_price_per_day_amount"));
		if(rs.wasNull()) { commands.setInitialPricePerDayAmount(null); };
		commands.setUntaxedAmount(rs.getBigDecimal("untaxed_amount"));
		if(rs.wasNull()) { commands.setUntaxedAmount(null); };
		commands.setAddWork(rs.getBigDecimal("add_work"));
		if(rs.wasNull()) { commands.setAddWork(null); };
		commands.setAddPricePerDayAmount(rs.getBigDecimal("add_price_per_day_amount"));
		if(rs.wasNull()) { commands.setAddPricePerDayAmount(null); };
		commands.setAddUntaxedAmount(rs.getBigDecimal("add_untaxed_amount"));
		if(rs.wasNull()) { commands.setAddUntaxedAmount(null); };
		commands.setValidatedWork(rs.getBigDecimal("validated_work"));
		if(rs.wasNull()) { commands.setValidatedWork(null); };
		commands.setValidatedPricePerDayAmount(rs.getBigDecimal("validated_price_per_day_amount"));
		if(rs.wasNull()) { commands.setValidatedPricePerDayAmount(null); };
		commands.setTotalUntaxedAmount(rs.getBigDecimal("total_untaxed_amount"));
		if(rs.wasNull()) { commands.setTotalUntaxedAmount(null); };
		commands.setComment(rs.getString("comment"));
		commands.setIdle(rs.getInt("idle"));
		commands.setDone(rs.getInt("done"));
		commands.setCancelled(rs.getInt("cancelled"));
		commands.setIdleDate(rs.getDate("idle_date"));
		commands.setDoneDate(rs.getDate("done_date"));
		commands.setHandled(rs.getInt("handled"));
		commands.setHandledDate(rs.getDate("handled_date"));
		commands.setReference(rs.getString("reference"));
		commands.setValidatedStartDate(rs.getDate("validated_start_date"));
		commands.setIdActivityType(rs.getInt("id_activity_type"));
		if(rs.wasNull()) { commands.setIdActivityType(null); };
		commands.setIdClient(rs.getInt("id_client"));
		if(rs.wasNull()) { commands.setIdClient(null); };
		commands.setIdContact(rs.getInt("id_contact"));
		if(rs.wasNull()) { commands.setIdContact(null); };
		commands.setIdPaymentDelay(rs.getInt("id_payment_delay"));
		if(rs.wasNull()) { commands.setIdPaymentDelay(null); };
		commands.setTax(rs.getBigDecimal("tax"));
		if(rs.wasNull()) { commands.setTax(null); };
		commands.setFullAmount(rs.getBigDecimal("full_amount"));
		if(rs.wasNull()) { commands.setFullAmount(null); };
		commands.setAddFullAmount(rs.getBigDecimal("add_full_amount"));
		if(rs.wasNull()) { commands.setAddFullAmount(null); };
		commands.setTotalFullAmount(rs.getBigDecimal("total_full_amount"));
		if(rs.wasNull()) { commands.setTotalFullAmount(null); };
		commands.setIdDeliveryMode(rs.getInt("id_delivery_mode"));
		if(rs.wasNull()) { commands.setIdDeliveryMode(null); };
		commands.setReceptionDate(rs.getDate("reception_date"));
		commands.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { commands.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			commands.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return commands;
	}
}

