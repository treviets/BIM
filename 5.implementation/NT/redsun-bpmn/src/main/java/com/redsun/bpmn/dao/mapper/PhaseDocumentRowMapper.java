package com.redsun.bpmn.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.bpmn.entities.PhaseDocument;

/**
 * PhaseDocument Mapper
 */
@Component
public class PhaseDocumentRowMapper implements RowMapper<PhaseDocument> {

	public PhaseDocument mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		PhaseDocument phase = new PhaseDocument();
		
		phase.setId(rs.getInt("id"));
		if(rs.wasNull()) { 
			phase.setId(null); 
		};
		
		phase.setIdProject(rs.getInt("project_id"));
		phase.setActiveStep(rs.getString("active_step"));
		phase.setUploadBy(rs.getString("upload_by"));
		phase.setUploadDate(rs.getString("upload_date"));
		phase.setUpdateBy(rs.getString("update_by"));
		phase.setUpdateDate(rs.getString("update_date"));
		phase.setDocumentName(rs.getString("doc_name"));
		phase.setDocumentUrl(rs.getString("url"));
		
		return phase;
	}
}

