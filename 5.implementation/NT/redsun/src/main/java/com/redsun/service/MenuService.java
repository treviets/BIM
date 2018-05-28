package com.redsun.service;

import java.util.List;

import com.redsun.entities.Menu;

public interface MenuService{
	public List<Menu> getAll() throws Exception;
	public Menu get(int menuId) throws Exception;
	public int addMenu(Menu menu) throws Exception;
	public int editMenu(Menu menu) throws Exception;
	public int deleteMenu(int menuId) throws Exception;
}