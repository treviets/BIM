package com.redsun.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.redsun.dao.MenuDao;
import com.redsun.dao.mapper.MenuRowMapper;
import com.redsun.entities.Menu;
import com.redsun.utils.UserUtil;

@Repository
public class MenuDaoImpl implements MenuDao {

	private static Logger log = Logger.getLogger(MenuDaoImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	private String GET_ALL_MENU = "select id, name, description, url, iconclass, position, status from Menus where status =?::bit order by position asc";
	private String EDIT_MENU = "Update Menus set name = ?, description = ?, url = ?, position = ?, iconclass = ?, status = ? where id = ?";
	private String ADD_MENU = "insert into Menus(name, description,url,position,iconclass, status) values(?,?,?,?,?,?::bit)";
	private String DELETE_MENU = "update Menus set status=?::bit where id=?";
	private String GET_MENU = "select id, name, description, url, iconclass, position, status from Menus where id=?";
	@Override
	public List<Menu> getAll() throws Exception {
		try {
			return jdbcTemplate.query(GET_ALL_MENU, new Object[]{UserUtil.ENABLED_USER},new MenuRowMapper());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Menu get(int menuId) throws Exception {
		try{
			List<Menu> list = null;
			
			list = jdbcTemplate.query(GET_MENU, new Object[]{menuId}, new MenuRowMapper());
			if(list.size()>0){
				return list.get(0);
			}
			return null;
		}catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Menu getMenuByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Menu menu) throws Exception {
		try {
			int result = jdbcTemplate.update(ADD_MENU, new Object[] { menu.getName(), menu.getDescription(),
					menu.getUrl(), menu.getPosition(), menu.getIconClass(), UserUtil.ENABLED_USER});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int edit(Menu menu) throws Exception {
		try {
			int result = jdbcTemplate.update(EDIT_MENU, new Object[] { menu.getName(), menu.getDescription(),
					menu.getUrl(), menu.getPosition(), menu.getIconClass(), menu.getStatus(), menu.getId()});
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public int delete(int menuId) throws Exception {
		try {
			return jdbcTemplate.update(DELETE_MENU, new Object[] { UserUtil.DISABLED_USER, menuId });
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

}
