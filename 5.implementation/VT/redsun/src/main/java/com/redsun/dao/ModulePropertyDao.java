package com.redsun.dao;

import java.util.List;

import com.redsun.entities.ModuleProperty;

public interface ModulePropertyDao {
	public List<ModuleProperty> gets(String moduleName) throws Exception;

	
}
