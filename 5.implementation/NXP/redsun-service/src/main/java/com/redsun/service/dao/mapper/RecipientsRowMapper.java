package com.redsun.service.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.redsun.service.entities.Recipients;

/**
 * Recipients Mapper
 */
@Component
public class RecipientsRowMapper implements RowMapper<Recipients> {

	public Recipients mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		Recipients recipients = new Recipients();
		recipients.setId(rs.getInt("id"));
		if(rs.wasNull()) { recipients.setId(null); };
		recipients.setName(rs.getString("name"));
		recipients.setCompanyNumber(rs.getString("company_number"));
		recipients.setNumTax(rs.getString("num_tax"));
		recipients.setBankName(rs.getString("bank_name"));
		recipients.setIbanCountry(rs.getString("iban_country"));
		recipients.setIbanKey(rs.getString("iban_key"));
		recipients.setIbanBban(rs.getString("iban_bban"));
		recipients.setDesignation(rs.getString("designation"));
		recipients.setStreet(rs.getString("street"));
		recipients.setComplement(rs.getString("complement"));
		recipients.setZip(rs.getString("zip"));
		recipients.setCity(rs.getString("city"));
		recipients.setState(rs.getString("state"));
		recipients.setCountry(rs.getString("country"));
		recipients.setTaxFree(rs.getInt("tax_free"));
		recipients.setIdle(rs.getInt("idle"));
		recipients.setLegalNotice(rs.getString("legal_notice"));
		recipients.setContactName(rs.getString("contact_name"));
		recipients.setContactEmail(rs.getString("contact_email"));
		recipients.setContactPhone(rs.getString("contact_phone"));
		recipients.setContactMobile(rs.getString("contact_mobile"));
		recipients.setBankNationalAccountNumber(rs.getString("bank_national_account_number"));
		recipients.setBankInternationalAccountNumber(rs.getString("bank_international_account_number"));
		recipients.setBankIdentificationCode(rs.getString("bank_identification_code"));
		recipients.setClientId(rs.getInt("client_id"));
		if(rs.wasNull()) { recipients.setClientId(null); };
		// Get extend properties.
		int ext_col_count = rs.getInt("ext_col_count");
		if(ext_col_count > 0) {
			int ext_col_count_index = rs.findColumn("ext_col_count");
			recipients.setTotalCount(rs.getInt(ext_col_count_index + 1));
		}

		return recipients;
	}
}

