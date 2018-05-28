package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.redsun.service.utils.RedSunColumnNames;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.CallForTenders;

/**
 * CallForTenders Mapper
 */
@Component
public class CallForTendersRowMapper implements RowMapper<CallForTenders> {

    public CallForTenders mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final CallForTenders callForTenders = new CallForTenders();
        callForTenders.setId(rs.getInt(RedSunColumnNames.call_for_tenders_id));
        if (rs.wasNull()) {
            callForTenders.setId(null);
        }
        callForTenders.setReference(rs.getString(RedSunColumnNames.call_for_tenders_reference));
        callForTenders.setName(rs.getString(RedSunColumnNames.call_for_tenders_name));
        callForTenders.setIdCallForTenderType(rs.getInt(RedSunColumnNames.call_for_tenders_id_call_for_tender_type));
        if (rs.wasNull()) {
            callForTenders.setIdCallForTenderType(null);
        }
        callForTenders.setIdProject(rs.getInt(RedSunColumnNames.call_for_tenders_id_project));
        if (rs.wasNull()) {
            callForTenders.setIdProject(null);
        }
        callForTenders.setIdUser(rs.getInt(RedSunColumnNames.call_for_tenders_id_user));
        if (rs.wasNull()) {
            callForTenders.setIdUser(null);
        }
        callForTenders.setDescription(rs.getString(RedSunColumnNames.call_for_tenders_description));
        callForTenders.setTechnicalRequirements(rs.getString(RedSunColumnNames.call_for_tenders_technical_requirements));
        callForTenders.setBusinessRequirements(rs.getString(RedSunColumnNames.call_for_tenders_business_requirements));
        callForTenders.setOtherRequirements(rs.getString(RedSunColumnNames.call_for_tenders_other_requirements));
        callForTenders.setCreationDate(rs.getDate(RedSunColumnNames.call_for_tenders_creation_date));
        callForTenders.setIdStatus(rs.getInt(RedSunColumnNames.call_for_tenders_id_status));
        if (rs.wasNull()) {
            callForTenders.setIdStatus(null);
        }
        callForTenders.setIdResource(rs.getInt(RedSunColumnNames.call_for_tenders_id_resource));
        if (rs.wasNull()) {
            callForTenders.setIdResource(null);
        }
        callForTenders.setSendDateTime(rs.getDate(RedSunColumnNames.call_for_tenders_send_date_time));
        callForTenders.setExpectedTenderDateTime(rs.getDate(RedSunColumnNames.call_for_tenders_expected_tender_date_time));
        callForTenders.setMaxAmount(rs.getBigDecimal(RedSunColumnNames.call_for_tenders_max_amount));
        if (rs.wasNull()) {
            callForTenders.setMaxAmount(null);
        }
        callForTenders.setDeliveryDate(rs.getDate(RedSunColumnNames.call_for_tenders_delivery_date));
        callForTenders.setEvaluationMaxValue(rs.getBigDecimal(RedSunColumnNames.call_for_tenders_evaluation_max_value));
        if (rs.wasNull()) {
            callForTenders.setEvaluationMaxValue(null);
        }
        callForTenders.setFixValue(rs.getInt(RedSunColumnNames.call_for_tenders_fix_value));
        callForTenders.setIdProduct(rs.getInt(RedSunColumnNames.call_for_tenders_id_product));
        if (rs.wasNull()) {
            callForTenders.setIdProduct(null);
        }
        callForTenders.setIdProductVersion(rs.getInt(RedSunColumnNames.call_for_tenders_id_product_version));
        if (rs.wasNull()) {
            callForTenders.setIdProductVersion(null);
        }
        callForTenders.setIdComponent(rs.getInt(RedSunColumnNames.call_for_tenders_id_component));
        if (rs.wasNull()) {
            callForTenders.setIdComponent(null);
        }
        callForTenders.setIdComponentVersion(rs.getInt(RedSunColumnNames.call_for_tenders_id_component_version));
        if (rs.wasNull()) {
            callForTenders.setIdComponentVersion(null);
        }
        callForTenders.setResult(rs.getString(RedSunColumnNames.call_for_tenders_result));
        callForTenders.setHandled(rs.getInt(RedSunColumnNames.call_for_tenders_handled));
        callForTenders.setDone(rs.getInt(RedSunColumnNames.call_for_tenders_done));
        callForTenders.setIdle(rs.getInt(RedSunColumnNames.call_for_tenders_idle));
        callForTenders.setCancelled(rs.getInt(RedSunColumnNames.call_for_tenders_cancelled));
        callForTenders.setHandledDate(rs.getDate(RedSunColumnNames.call_for_tenders_handled_date));
        callForTenders.setDoneDate(rs.getDate(RedSunColumnNames.call_for_tenders_done_date));
        callForTenders.setIdleDate(rs.getDate(RedSunColumnNames.call_for_tenders_idle_date));
        callForTenders.setClientId(rs.getInt(RedSunColumnNames.call_for_tenders_client_id));
        if (rs.wasNull()) {
            callForTenders.setClientId(null);
        }
        int ext_col_count = rs.getInt(RedSunColumnNames.call_for_tenders_ext_col_count);
        if (ext_col_count > 0) {
            int ext_col_count_index = rs.findColumn(RedSunColumnNames.call_for_tenders_ext_col_count);
            callForTenders.setTotalCount(rs.getInt(ext_col_count_index + 1));
        }
        return callForTenders;
    }
}

