package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Milestones;

/**
 * Milestones Mapper
 */
@Component
public class MilestonesRowMapper implements RowMapper<Milestones> {

	public Milestones mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Milestones milestones = new Milestones();
		milestones.setId(rs.getInt("id"));
		if(rs.wasNull()) { milestones.setId(null); };
		milestones.setIdProject(rs.getInt("id_project"));
		if(rs.wasNull()) { milestones.setIdProject(null); };
		milestones.setName(rs.getString("name"));
		milestones.setDescription(rs.getString("description"));
		milestones.setCreationDate(rs.getDate("creation_date"));
		milestones.setIdUser(rs.getInt("id_user"));
		if(rs.wasNull()) { milestones.setIdUser(null); };
		milestones.setIdStatus(rs.getInt("id_status"));
		if(rs.wasNull()) { milestones.setIdStatus(null); };
		milestones.setIdResource(rs.getInt("id_resource"));
		if(rs.wasNull()) { milestones.setIdResource(null); };
		milestones.setResult(rs.getString("result"));
		milestones.setComment(rs.getString("comment"));
		milestones.setIdle(rs.getInt("idle"));
		milestones.setIdMilestoneType(rs.getInt("id_milestone_type"));
		if(rs.wasNull()) { milestones.setIdMilestoneType(null); };
		milestones.setIdActivity(rs.getInt("id_activity"));
		if(rs.wasNull()) { milestones.setIdActivity(null); };
		milestones.setDone(rs.getInt("done"));
		milestones.setIdleDate(rs.getDate("idle_date"));
		milestones.setDoneDate(rs.getDate("done_date"));
		milestones.setHandled(rs.getInt("handled"));
		milestones.setHandledDate(rs.getDate("handled_date"));
		milestones.setIdVersion(rs.getInt("id_version"));
		if(rs.wasNull()) { milestones.setIdVersion(null); };
		milestones.setReference(rs.getString("reference"));
		milestones.setExternalReference(rs.getString("external_reference"));
		milestones.setCancelled(rs.getInt("cancelled"));
		milestones.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { milestones.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			milestones.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return milestones;
	}
}

