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

import com.redsun.service.dao.TendersDao;
import com.redsun.service.entities.Result;
import com.redsun.service.entities.Tenders;
import com.redsun.service.service.TendersService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tenders Service implementation 
 */
@Service
public class TendersServiceImpl implements TendersService {
	
    @Autowired
    private TendersDao tendersDao;
    @Autowired
	private Environment env;
	
    @Transactional
	@Override
	public Result insert(final Tenders tenders) {
		final int data = tendersDao.insert(tenders);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	@Transactional
	@Override
	public Result update(final Tenders tenders) {
		final int data = tendersDao.update(tenders);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}	

	@Transactional
	@Override
	public Result delete(final Integer id, final String refType) throws IOException {
		final int data = tendersDao.delete(id);
		final String pathToFile = env.getProperty("attachments.path").replace('\\', File.separatorChar) + refType + File.separatorChar + id;
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
		final List<Tenders> data = tendersDao.getById(id);
		final Map<String, Object> result = new HashMap<>();
		result.put("tenders", data);
		return new Result(result, true);
	}

	@Override
	public Result exists(final Integer id) {
		final boolean data = tendersDao.exists(id);
		final Map<String, Object> result = new HashMap<>();
		result.put("exists", data);
		return new Result(result, true);
	}

	@Override
	public Result count() {
		final long data = tendersDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}

	@Override
	public Result listTendersForPageAndFilter(final int itemsPerPage, final int pageNo, final Tenders tenders) {
		final List<Map<Object, Object>> data = tendersDao.listTendersForPageAndFilter(itemsPerPage, pageNo, tenders);
		final Map<String, Object> result = new HashMap<>();
		result.put("tenders", data);
		return new Result(result, true);
	}
}
