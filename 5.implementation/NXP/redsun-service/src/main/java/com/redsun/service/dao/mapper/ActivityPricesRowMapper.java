package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.ActivityPrices;

/**
 * ActivityPrices Mapper
 */
@Component
public class ActivityPricesRowMapper implements RowMapper<ActivityPrices> {

	public ActivityPrices mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		ActivityPrices activityPrices = new ActivityPrices();
		activityPrices.setId(rs.getInt("id"));
		if(rs.wasNull()) { activityPrices.setId(null); };
		activityPrices.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { activityPrices.setIdProject(null); };
		activityPrices.setIdActivityType(rs.getInt("id_activity_type"));
		if(rs.wasNull()) { activityPrices.setIdActivityType(null); };
		activityPrices.setName(rs.getString("name"));
		activityPrices.setPriceCost(rs.getBigDecimal("price_cost"));
		if(rs.wasNull()) { activityPrices.setPriceCost(null); };
		activityPrices.setSubcontractor(rs.getInt("subcontractor"));
		if(rs.wasNull()) { activityPrices.setSubcontractor(null); };
		activityPrices.setSortOrder(rs.getInt("sort_order"));
		if(rs.wasNull()) { activityPrices.setSortOrder(null); };
		activityPrices.setIdle(rs.getInt("idle"));
		activityPrices.setSubcontractorCost(rs.getBigDecimal("subcontractor_cost"));
		if(rs.wasNull()) { activityPrices.setSubcontractorCost(null); };
		activityPrices.setIdTeam(rs.getInt("id_team"));
		if(rs.wasNull()) { activityPrices.setIdTeam(null); };
		activityPrices.setCommissionCost(rs.getBigDecimal("commission_cost"));
		if(rs.wasNull()) { activityPrices.setCommissionCost(null); };
		activityPrices.setIsRef(rs.getInt("is_ref"));
		if(rs.wasNull()) { activityPrices.setIsRef(null); };
		activityPrices.setPct(rs.getInt("pct"));
		if(rs.wasNull()) { activityPrices.setPct(null); };
		activityPrices.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { activityPrices.setIdUser(null); };
		activityPrices.setCreationDate(rs.getDate("creation_date"));
		activityPrices.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { activityPrices.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			activityPrices.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return activityPrices;
	}
}

