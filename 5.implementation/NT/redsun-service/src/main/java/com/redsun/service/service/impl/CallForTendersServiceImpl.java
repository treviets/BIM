package com.redsun.service.service.impl;

import com.redsun.service.dao.CallForTendersDao;
import com.redsun.service.entities.CallForTenders;
import com.redsun.service.entities.Result;
import com.redsun.service.service.CallForTendersService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CallForTenders Service implementation
 * Author: Hau Lam
 */
@Service
public class CallForTendersServiceImpl implements CallForTendersService {
	
    @Autowired
    private CallForTendersDao callForTendersDao;
    @Autowired
	private Environment env;

	/**
	 * get all tender by client id
	 * @param clientId
	 * @return
	 */
	@Override
	public Result getAll(final Integer clientId, final Integer pageNo, final Integer itemsPerPage) {
		final Map<Object, Object> map = new HashMap<>();
		map.put("Tenders", callForTendersDao.getAll(clientId, pageNo, itemsPerPage));
		return new Result(map, true);
	}

	/**
	 * get tender by client id vs tender id
	 * @param clientId
	 * @param tenderId
	 * @return
	 */
	@Override
	public Result getById(final Integer clientId, final Integer tenderId) {
		final List<CallForTenders> data = callForTendersDao.getById(clientId, tenderId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("Tender", data);
		return new Result(result, true);
	}

	/**
	 * delete tender
	 * @param tenderId
	 * @return
	 */
	@Override
	@Transactional
	public Result delete(final String refType, final Integer tenderId) throws IOException {
		final Integer deleteCount = callForTendersDao.delete(tenderId);
		final String pathToFile = env.getProperty("attachments.path").replace('\\', File.separatorChar) + refType + File.separatorChar + tenderId;
		final Path path = Paths.get(pathToFile);
		if (Files.exists(path)) {
			FileUtils.forceDelete(new File(path.toUri()));
		}
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("deleteCount", deleteCount);
		return new Result(result, true);
	}

	/**
	 * create new tender
	 * @param callForTenders
	 * @return
	 */
	@Override
	public Result insert(final CallForTenders callForTenders) {
		final Integer insertCount = callForTendersDao.insert(callForTenders);
		final Map<String, Object> result = new HashMap<>();
		result.put("insertCount", insertCount);
		return new Result(result, true);
	}

	/**
	 * update tender
	 * @param callForTenders
	 * @return
	 */
	@Override
	public Result update(final CallForTenders callForTenders) {
		final Integer updateCount = callForTendersDao.update(callForTenders);
		final Map<String, Object> result = new HashMap<>();
		result.put("updateCount", updateCount);
		return new Result(result, true);
	}

}
