package com.redsun.dao;

import java.util.List;

import com.redsun.entities.Menu;

public interface MenuDao {
	public List<Menu> getAll() throws Exception;
	public Menu get(int menuId) throws Exception;
	public Menu getMenuByName(String name) throws Exception;
	public int add(Menu menu) throws Exception;
	public int edit(Menu menu) throws Exception;
	public int delete(int menuId) throws Exception;
	
}
