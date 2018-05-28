package com.redsun.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.redsun.entities.RolePermissionMenu;

public class RolePermissionMenuMapper implements RowMapper<RolePermissionMenu> {

	@Override
	public RolePermissionMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
		RolePermissionMenu per = new RolePermissionMenu();
		per.setId(rs.getInt("id"));
		per.setRoleId(rs.getInt("role_id"));
		per.setMenuId(rs.getInt("menu_id"));
		per.setMenuName(rs.getString("name"));
		per.setPermission(rs.getString("permission"));
		per.setMenuDescription(rs.getString("description"));
		return per;
	}

}
