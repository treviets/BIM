package com.redsun.social.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsun.social.dao.FileSharingDao;
import com.redsun.social.entities.FileSharing;
import com.redsun.social.entities.Result;
import com.redsun.social.service.FileSharingService;
import com.redsun.social.utils.RedSunConstants;

@Service
public class FileSharingServiceImpl implements FileSharingService {

	@Autowired
	FileSharingDao fileSharingDao;

	@Override
	public Object createFileSharing(FileSharing fileSharing) {
		Result result = new Result();
		result.setDescription(RedSunConstants.STATUS_FAILED_STRING);
		int status = fileSharingDao.createFileSharing(fileSharing);
		fileSharing.setId(status);
		result.setStatus(status);
		Map<String, Object> data = new HashMap<>();
		data.put("fileSharing", fileSharing);
		result.setResult(data);
		result.setDescription(RedSunConstants.STATUS_SUCCESS_STRING);
		
		return result;
	}

	@Override
	public Object getFileSharingByGroup(int projectId, int groupId, int clientId) {
		final List<FileSharing> data = fileSharingDao.getFileSharingByGroup(projectId, groupId, clientId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("fileSharing", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Object deleteFileSharing(int fileSharingId) {
		// Call DAO.
		final int data = fileSharingDao.deleteFileSharing(fileSharingId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result getById(int fileId) {
		final FileSharing data = fileSharingDao.getById(fileId);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("filesharing", data);
		// Return.
		return new Result(result, true);
	}

	@Override
	public Result download(final Integer id) {
		final FileSharing docs = fileSharingDao.getById(id);
		final Map<String, Object> result = new HashMap<>();
		if(docs != null){
			final String pathToFile = RedSunConstants.rootDirectory.replace('\\', File.separatorChar) + docs.getFilePath();
			final File file =  new File(pathToFile);
			result.put("filesharing", file);
		}
		return new Result(result, true);
	}
}
