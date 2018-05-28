package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.redsun.service.utils.RedSunColumnNames;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Attachments;

/**
 * Attachments Mapper
 */
@Component
public class AttachmentsRowMapper implements RowMapper<Attachments> {

    public Attachments mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Attachments attachments = new Attachments();
        attachments.setId(rs.getInt(RedSunColumnNames.attachments_id));
        if (rs.wasNull()) {
            attachments.setId(null);
        }
        attachments.setRefType(rs.getString(RedSunColumnNames.attachments_ref_type));
        attachments.setRefId(rs.getInt(RedSunColumnNames.attachments_ref_id));
        if (rs.wasNull()) {
            attachments.setRefId(null);
        }
        attachments.setIdUser(rs.getInt(RedSunColumnNames.attachments_id_user));
        if (rs.wasNull()) {
            attachments.setIdUser(null);
        }
        attachments.setCreationDate(rs.getDate(RedSunColumnNames.attachments_creation_date));
        attachments.setFileName(rs.getString(RedSunColumnNames.attachments_file_name));
        attachments.setDescription(rs.getString(RedSunColumnNames.attachments_description));
        attachments.setSubDirectory(rs.getString(RedSunColumnNames.attachments_sub_directory));
        attachments.setMimeType(rs.getString(RedSunColumnNames.attachments_mime_type));
        attachments.setFileSize(rs.getInt(RedSunColumnNames.attachments_file_size));
        if (rs.wasNull()) {
            attachments.setFileSize(null);
        }
        attachments.setLink(rs.getString(RedSunColumnNames.attachments_link));
        attachments.setType(rs.getString(RedSunColumnNames.attachments_type));
        attachments.setIdPrivacy(rs.getInt(RedSunColumnNames.attachments_id_privacy));
        if (rs.wasNull()) {
            attachments.setIdPrivacy(null);
        }
        attachments.setIdTeam(rs.getInt(RedSunColumnNames.attachments_id_team));
        if (rs.wasNull()) {
            attachments.setIdTeam(null);
        }
        attachments.setClientId(rs.getInt(RedSunColumnNames.attachments_client_id));
        if (rs.wasNull()) {
            attachments.setClientId(null);
        }
        int ext_col_count = rs.getInt(RedSunColumnNames.attachments_ext_col_count);
        if (ext_col_count > 0) {
            int ext_col_count_index = rs.findColumn(RedSunColumnNames.attachments_ext_col_count);
            attachments.setTotalCount(rs.getInt(ext_col_count_index + 1));
        }
        return attachments;
    }
}

