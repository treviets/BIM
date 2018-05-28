package com.redsun.service.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.OrganizationDao;
import com.redsun.service.dao.mapper.OrganizationMapper;
import com.redsun.service.entities.Organization;


//only done listAllProjects to test for Risks.

@Repository
public class OrganizationImpl implements OrganizationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private static Logger log = Logger.getLogger(OrganizationImpl.class);
	private static String  SELECT_ALL_ORGANIZATION ="SELECT id, name, description, idle, id_user, id_resource, sort_order," 
       +"id_organization_type, id_organization, creation_date, last_update_date_time," 
       +"client_id"
       +" FROM public.organizations WHERE client_id = ?";

	public List<Organization> listAll(int clientId){
		List<Organization> listOrganization = null;
		try {
			listOrganization = jdbcTemplate.query(SELECT_ALL_ORGANIZATION, new Object[] { clientId }, new OrganizationMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listOrganization;
	}
}
