package com.redsun.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.service.dao.CalendarsDao;
import com.redsun.service.dao.mapper.CalendarsRowMapper;
import com.redsun.service.entities.Calendars;

@Repository
public class CalendarsDaoImpl implements CalendarsDao {

	@Autowired
	JdbcTemplate jdbcTemplate;


	private static Logger log = Logger.getLogger(CalendarsDaoImpl.class);



/* 
	  static private String SELECT_ALL_CALENDAR="SELECT c.id, c.id_project, c.calendarname, "
	  +"c.startday, c.endday, c.description, c.document "
	  +"FROM calendars as c " +
	  "INNER JOIN projects as p ON c.id_project = p.id";*/
	 

	private static String ADD_CALENDAR = "INSERT INTO public.calendars("
			+ "calendar_name,  description, attach_file, start_date, end_date, project_id, client_id)" + " VALUES (?, ?, ?, ?::date, ?::date,?,?)";

	//static private String DELETE_CALENDARS = "DELETE FROM public.calendars WHERE id= ?";
	
	// Get Prepared Statement Creator.
	
	@Override
	public int insert(final Calendars calendar) {
		int result = jdbcTemplate.update(ADD_CALENDAR, new Object[]{
				
				calendar.getTitle(),
				calendar.getDescription(),
				calendar.getAttachFile(),
				calendar.getStart(),
				calendar.getEnd(),
				calendar.getProjectId(),
				calendar.getClientId()
		});
		
		return result;
		
	}
	
	@Override
	public List<Calendars> listCalendars(int clientId) {
		List<Calendars> listCalendar = null;
		
		String SELECT_CALENDAR="select c.id, c.calendar_name, c.description, "
				+ "c.attach_file, c.start_date, c.end_date, c.project_id, c.client_id,"
				+ "p.name as project_name "
				+ "from calendars as c "
				+ "inner join projects as p on p.id = c.project_id "
				+ "where c.client_id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		
		try{
			listCalendar = jdbcTemplate.query(SELECT_CALENDAR, params.toArray(), new CalendarsRowMapper());
			return listCalendar;
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return listCalendar;
	}


	/*@Override
	public int update(Calendars calendar) {
		int result = 0;
		try {
			String UPDATE_calendar = "UPDATE public.calendars "
					+"SET calendarname=?, description=?, startdate=?::date, enddate=?::date, " 
					+"id_project=?, documentname=?, client_id=?" + " WHERE id = " + calendar.getId();

			result = jdbcTemplate.update(UPDATE_calendar,
					new Object[] {
							calendar.getCalendarName(),
							calendar.getDescription(),
						
							calendar.getStartDate(),
							calendar.getEndDate(),
						
							
							calendar.getProjectId(),
							calendar.getDocumentName(),
							calendar.getClientId()
			});
							
							
			if(result > 0){
				String INSERT_COMMENT = "INSERT INTO COMMENTS (id_calendar, id_author, content, create_date_time )"
						+ " VALUES (?, ?, ?, 'now'::date)";
				result = jdbcTemplate.update(INSERT_COMMENT, new Object[]{
						calendar.getId(),
						calendar.getClientId(),
						calendar.getComment()
				});
				}
				
				
			
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}*/

	/*@Override
	public int delete(int id) {
		int result = jdbcTemplate.update(DELETE_CALENDARS, new Object[] { id });
		return result;
	}*/

	


	/*@Override
	public Calendars getById(int id) {
		Object[] primaryKey = new Object[] { id };
		String SELECT_GETBYID = "select c.id, c.calendarname, c.description "
				+ "c.startday, c.endday, c.id_project, c.client_id,"
				+ "p.name as project_name "
				+ "from calendars as c "
				+ "inner join projects as p on c.id_project = p.id "
				+ "where c.id=? ";
		try {
			return jdbcTemplate.queryForObject(SELECT_GETBYID, primaryKey, new CalendarsRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
*/
	/*@Override
	public List<Calendars> listCalendarOfOneProject(int clientId, int projectId) {
		List<Calendars> listcalendars = null;
		String SELECT_CALENDARS_ONE_PROJECT = "select c.id, c.calendarname, c.description "
				+ "c.startday, c.endday, c.id_project, c.client_id,"
				+ "p.name as project_name "
				+ "from calendars as c "
				+ "inner join projects as p on c.id_project = p.id "
				+ "where c.client_id=? and c.id_project=?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(clientId);
		params.add(projectId);
		
		try {
			listcalendars = jdbcTemplate.query(SELECT_CALENDARS_ONE_PROJECT, params.toArray(), new CalendarsRowMapper());
			return listcalendars;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return listcalendars;

	}
	*/
}

