package com.redsun.whm.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.whm.entities.DocumentStep;

/**
 * DocumentStep Mapper
 */
@Component
public class DocumentStepRowMapper implements RowMapper<DocumentStep> {

	public DocumentStep mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		DocumentStep documentStep = new DocumentStep();
		
		documentStep.setId(rs.getInt("id"));
		if(rs.wasNull()) { 
			documentStep.setId(null); 
		};
		
		documentStep.setIdProject(rs.getInt("project_id"));
		documentStep.setIdStep(rs.getString("step_id"));
		documentStep.setDocumentName(rs.getString("document_name"));
		documentStep.setDocumentDescription(rs.getString("document_description"));

		return documentStep;
	}
}

