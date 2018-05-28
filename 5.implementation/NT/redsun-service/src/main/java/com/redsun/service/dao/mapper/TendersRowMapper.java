package com.redsun.service.dao.mapper;

import com.redsun.service.entities.Tenders;
import com.redsun.service.utils.RedSunColumnNames;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Tenders Mapper
 */
@Component
public class TendersRowMapper implements RowMapper<Tenders> {

    public Tenders mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Tenders tenders = new Tenders();
        tenders.setId(rs.getInt(RedSunColumnNames.tenders_id));
        if (rs.wasNull()) {
            tenders.setId(null);
        }
        tenders.setReference(rs.getString(RedSunColumnNames.tenders_reference));
        tenders.setName(rs.getString(RedSunColumnNames.tenders_name));
        tenders.setIdTenderType(rs.getInt(RedSunColumnNames.tenders_id_tender_type));
        if (rs.wasNull()) {
            tenders.setIdTenderType(null);
        }
        tenders.setIdProject(rs.getInt(RedSunColumnNames.tenders_id_project));
        if (rs.wasNull()) {
            tenders.setIdProject(null);
        }
        tenders.setIdCallForTender(rs.getInt(RedSunColumnNames.tenders_id_call_for_tender));
        if (rs.wasNull()) {
            tenders.setIdCallForTender(null);
        }
        tenders.setIdUser(rs.getInt(RedSunColumnNames.tenders_id_user));
        if (rs.wasNull()) {
            tenders.setIdUser(null);
        }
        tenders.setCreationDate(rs.getDate(RedSunColumnNames.tenders_creation_date));
        tenders.setIdProvider(rs.getInt(RedSunColumnNames.tenders_id_provider));
        if (rs.wasNull()) {
            tenders.setIdProvider(null);
        }
        tenders.setExternalReference(rs.getString(RedSunColumnNames.tenders_external_reference));
        tenders.setDescription(rs.getString(RedSunColumnNames.tenders_description));
        tenders.setIdStatus(rs.getInt(RedSunColumnNames.tenders_id_status));
        if (rs.wasNull()) {
            tenders.setIdStatus(null);
        }
        tenders.setIdResource(rs.getInt(RedSunColumnNames.tenders_id_resource));
        if (rs.wasNull()) {
            tenders.setIdResource(null);
        }
        tenders.setIdContact(rs.getInt(RedSunColumnNames.tenders_id_contact));
        if (rs.wasNull()) {
            tenders.setIdContact(null);
        }
        tenders.setRequestDateTime(rs.getDate(RedSunColumnNames.tenders_request_date_time));
        tenders.setExpectedTenderDateTime(rs.getDate(RedSunColumnNames.tenders_expected_tender_date_time));
        tenders.setReceptionDateTime(rs.getDate(RedSunColumnNames.tenders_reception_date_time));
        tenders.setEvaluationValue(rs.getBigDecimal(RedSunColumnNames.tenders_evaluation_value));
        if (rs.wasNull()) {
            tenders.setEvaluationValue(null);
        }
        tenders.setEvaluationRank(rs.getInt(RedSunColumnNames.tenders_evaluation_rank));
        if (rs.wasNull()) {
            tenders.setEvaluationRank(null);
        }
        tenders.setOfferValidityEndDate(rs.getDate(RedSunColumnNames.tenders_offer_validity_end_date));
        tenders.setPlannedAmount(rs.getBigDecimal(RedSunColumnNames.tenders_planned_amount));
        if (rs.wasNull()) {
            tenders.setPlannedAmount(null);
        }
        tenders.setTaxPct(rs.getBigDecimal(RedSunColumnNames.tenders_tax_pct));
        if (rs.wasNull()) {
            tenders.setTaxPct(null);
        }
        tenders.setPlannedTaxAmount(rs.getBigDecimal(RedSunColumnNames.tenders_planned_tax_amount));
        if (rs.wasNull()) {
            tenders.setPlannedTaxAmount(null);
        }
        tenders.setPlannedFullAmount(rs.getBigDecimal(RedSunColumnNames.tenders_planned_full_amount));
        if (rs.wasNull()) {
            tenders.setPlannedFullAmount(null);
        }
        tenders.setInitialAmount(rs.getBigDecimal(RedSunColumnNames.tenders_initial_amount));
        if (rs.wasNull()) {
            tenders.setInitialAmount(null);
        }
        tenders.setInitialTaxAmount(rs.getBigDecimal(RedSunColumnNames.tenders_initial_tax_amount));
        if (rs.wasNull()) {
            tenders.setInitialTaxAmount(null);
        }
        tenders.setInitialFullAmount(rs.getBigDecimal(RedSunColumnNames.tenders_initial_full_amount));
        if (rs.wasNull()) {
            tenders.setInitialFullAmount(null);
        }
        tenders.setDeliveryDelay(rs.getString(RedSunColumnNames.tenders_delivery_delay));
        tenders.setDeliveryDate(rs.getDate(RedSunColumnNames.tenders_delivery_date));
        tenders.setPaymentCondition(rs.getString(RedSunColumnNames.tenders_payment_condition));
        tenders.setEvaluation(rs.getBigDecimal(RedSunColumnNames.tenders_evaluation));
        if (rs.wasNull()) {
            tenders.setEvaluation(null);
        }
        tenders.setResult(rs.getString(RedSunColumnNames.tenders_result));
        tenders.setHandled(rs.getInt(RedSunColumnNames.tenders_handled));
        tenders.setDone(rs.getInt(RedSunColumnNames.tenders_done));
        tenders.setIdle(rs.getInt(RedSunColumnNames.tenders_idle));
        tenders.setCancelled(rs.getInt(RedSunColumnNames.tenders_cancelled));
        tenders.setHandledDate(rs.getDate(RedSunColumnNames.tenders_handled_date));
        tenders.setDoneDate(rs.getDate(RedSunColumnNames.tenders_done_date));
        tenders.setIdleDate(rs.getDate(RedSunColumnNames.tenders_idle_date));
        tenders.setClientId(rs.getInt(RedSunColumnNames.tenders_client_id));
        if (rs.wasNull()) {
            tenders.setClientId(null);
        }
        // Get extend properties.
        int ext_col_count = rs.getInt(RedSunColumnNames.tenders_ext_col_count);
        if (ext_col_count > 0) {
            int ext_col_count_index = rs.findColumn(RedSunColumnNames.tenders_ext_col_count);
            tenders.setTotalCount(rs.getInt(ext_col_count_index + 1));
        }

        return tenders;
    }
}

