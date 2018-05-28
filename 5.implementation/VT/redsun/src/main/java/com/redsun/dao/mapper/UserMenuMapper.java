package com.redsun.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.entities.Menu;

public class UserMenuMapper implements RowMapper<Menu> {

	public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {

		Menu menu = null;
		menu = new Menu();
		menu.setId(rs.getInt("id"));
		menu.setName(rs.getString("name"));
		menu.setDescription(rs.getString("description"));
		menu.setUrl(rs.getString("url"));
		menu.setIconClass(rs.getString("iconclass"));
		menu.setPosition(rs.getInt("position"));
		menu.setPermission(rs.getString("permission"));
		menu.setCreateBy(rs.getString("create_by"));

		return menu;
	}

}
