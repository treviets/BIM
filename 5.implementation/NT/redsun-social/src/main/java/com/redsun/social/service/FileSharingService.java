package com.redsun.social.service;

import com.redsun.social.entities.FileSharing;
import com.redsun.social.entities.Result;

public interface FileSharingService {
	public Object createFileSharing(FileSharing fileSharing);
	public Object getFileSharingByGroup(int projectId, int groupId, int clientId);
	public Object deleteFileSharing(int fileSharingId);
	Result download(final Integer id);
	public Result getById(final int fileId);
}
