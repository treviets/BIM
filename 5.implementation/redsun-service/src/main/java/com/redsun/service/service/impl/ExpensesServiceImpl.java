package com.redsun.service.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.ExpensesDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Expenses;
import com.redsun.service.service.ExpensesService;

/**
 * Expenses Service implementation 
 */
@Service
public class ExpensesServiceImpl implements ExpensesService {
	
    @Autowired
    private ExpensesDao expensesDao;
	@Autowired
	private Environment env;
	
	@Override
	public Result insert(final Expenses expenses) {
		final int data = expensesDao.insert(expenses);
		final Map<String, Object> result = new HashMap<>();
		result.put("id", data);
		return new Result(result, true);
	}

	@Override
	public Result update(final Expenses expenses) {
		final int data = expensesDao.update(expenses);
		final Map<String, Object> result = new HashMap<>();
		result.put("id", data);
		return new Result(result, true);
	}	

	@Override
	public Result delete(final String scope, final Integer id) throws IOException {
		final int data = expensesDao.delete(id);
		final String pathToFile = env.getProperty("attachments.path").replace('\\', File.separatorChar) + scope + File.separatorChar + id;
		final Path path = Paths.get(pathToFile);
		if (Files.exists(path)) {
			FileUtils.forceDelete(new File(path.toUri()));
		}
		final Map<String, Object> result = new HashMap<>();
		result.put("id", data);
		return new Result(result, true);
	}

	@Override
	public Result getById(final Integer id) {
		final List<Expenses> data = expensesDao.getById(id);
		final Map<String, Object> result = new HashMap<>();
		result.put("expenses", data);
		return new Result(result, true);
	}

	@Override
	public Result exists(final Integer id) {
		final boolean data = expensesDao.exists(id);
		final Map<String, Object> result = new HashMap<>();
		result.put("exists", data);
		return new Result(result, true);
	}

	@Override
	public Result count() {
		final long data = expensesDao.count();
		final Map<String, Object> result = new HashMap<>();
		result.put("count", data);
		return new Result(result, true);
	}

	public Result listExpensesForPageAndFilter(final Integer clientId, final String scope, final Integer pageNo, final Integer itemsPerPage) {
		final  List<Map<Object, Object>> data = expensesDao.getExpensesByType(clientId, scope, pageNo, itemsPerPage);
		final Map<String, Object> result = new HashMap<>();
		result.put("expenses", data);
		return new Result(result, true);
	}
}
