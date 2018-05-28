package com.redsun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.dao.MenuDao;
import com.redsun.entities.Menu;
import com.redsun.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuDao menuDao;

	@Override
	public List<Menu> getAll() throws Exception {
		return menuDao.getAll();
	}

	@Override
	public int addMenu(Menu menu) throws Exception {
		return menuDao.add(menu);
		
	}

	@Override
	public int editMenu(Menu menu) throws Exception {
		return menuDao.edit(menu);
	}

	@Override
	public int deleteMenu(int menuId) throws Exception {
		return menuDao.delete(menuId);
	}

	@Override
	public Menu get(int menuId) throws Exception {
		return menuDao.get(menuId);
	}

}
