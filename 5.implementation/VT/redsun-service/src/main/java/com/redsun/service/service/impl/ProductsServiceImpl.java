package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ProductsDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Products;
import com.redsun.service.service.ProductsService;

/**
 * Products Service implementation 
 */
@Service
public class ProductsServiceImpl implements ProductsService {
	
    @Autowired
    private ProductsDao productsDao;
	
    // Insert.
	@Override
	public Result insert(final Products products) {
		// Call DAO.
		final int data = productsDao.insert(products);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Products products) {
		// Call DAO.
		final int data = productsDao.update(products);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer id) {
		// Call DAO.
		final int data = productsDao.delete(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Products> data = productsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("products", data);
		// Return.
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = productsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = productsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listProductsForPageAndFilter(final int itemsPerPage, final int pageNo, final Products products) {
		// Call DAO.
		final List<Products> data = productsDao.listProductsForPageAndFilter(itemsPerPage, pageNo, products);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("products", data);
		// Return.
		return new Result(result, true);
	}
}
