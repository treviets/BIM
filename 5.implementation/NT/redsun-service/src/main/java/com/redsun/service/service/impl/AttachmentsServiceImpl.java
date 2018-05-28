package com.redsun.service.service.impl;

import com.redsun.service.dao.AttachmentsDao;
import com.redsun.service.entities.Attachments;
import com.redsun.service.entities.Result;
import com.redsun.service.service.AttachmentsService;
import com.redsun.service.utils.RedSunColumnNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Attachments Service implementation 
 */
@Service
public class AttachmentsServiceImpl implements AttachmentsService {
	
    @Autowired
    private AttachmentsDao attachmentsDao;
    @Autowired
    private Environment env;

	@Override
	public Result insert(final Attachments attachments) {

		final int data = attachmentsDao.insert(attachments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	@Override
	public Result update(final Attachments attachments) {
		// Call DAO.
		final int data = attachmentsDao.update(attachments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}	

	@Transactional
	@Override
	public Result delete(final Integer id) throws IOException {
		final Map<String, Object> result = new HashMap<>();
		final List<Attachments> attachment = attachmentsDao.getById(id);
		if(!attachment.isEmpty()){
			final Attachments entity = attachment.get(0);
			final String pathToFile = env.getProperty("attachments.path").replace('\\', File.separatorChar) + entity.getRefType() + File.separatorChar + entity.getRefId() + File.separatorChar + entity.getFileName();
			final Path path = Paths.get(pathToFile);
			if(Files.exists(path)){
				Files.delete(path);
			}
			final int data = attachmentsDao.delete(id);
			result.put(RedSunColumnNames.attachments_id, data);
		}
		return new Result(result, true);
	}

	@Override
	public Result getById(final Integer id) {
		// Call DAO.
		final List<Attachments> data = attachmentsDao.getById(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("attachments", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result exists(final Integer id) {
		// Call DAO.
		final boolean data = attachmentsDao.exists(id);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result count() {
		// Call DAO.
		final long data = attachmentsDao.count();
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result listAttachmentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Attachments attachments) {
		final List<Attachments> data = attachmentsDao.listAttachmentsForPageAndFilter(itemsPerPage, pageNo, attachments);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("attachments", data);
		return new Result(result, true);
	}

	@Override
	public Result getByRefId(final String refType, final Integer refId) {
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("attachments", attachmentsDao.listAttachmentsByRef(refType, refId));
		return new Result(result, true);
	}

	@Override
	@Transactional
	public Result upload(final String refType, final Integer refId, final MultipartFile file) throws IOException {
		final String pathToFile = env.getProperty("attachments.path").replace('\\', File.separatorChar) + refType + File.separatorChar + refId + File.separatorChar;
		final File dir = new File(pathToFile);
		if (!dir.exists())
			dir.mkdirs();
		final String fileName = pathToFile + file.getOriginalFilename().split("%&%&%")[0];
		// TODO check same file name before create new file
		final Path path = Files.createFile(Paths.get(fileName));
		file.transferTo(new File(path.toUri()));
		return new Result(new HashMap<String, Object>(), true);
	}

	@Override
	public Result download(final Integer id) {
		final List<Attachments> attachments = attachmentsDao.getById(id);
		final Map<String, Object> result = new HashMap<>();
		if(!attachments.isEmpty()){
			final Attachments attach = attachments.get(0);
			final String pathToFile = env.getProperty("attachments.path").replace('\\', File.separatorChar) + attach.getRefType() + File.separatorChar + attach.getRefId() + File.separatorChar + attach.getFileName();
			final File file =  new File(pathToFile);
			result.put("file", file.exists()?file:null);
			result.put("fileType", new File(attach.getMimeType()));
		}
		return new Result(result, true);
	}

}
