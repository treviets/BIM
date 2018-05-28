package com.redsun.service.service;

import com.redsun.service.entities.Result;

public interface CategoryService {
	Result getAllCategories();

	Result getCategoryData(int id);
}
