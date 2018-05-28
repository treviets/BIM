package com.redsun.social.dao;

import java.util.List;

import com.redsun.social.entities.FileSharing;

public interface FileSharingDao {
	public int createFileSharing(FileSharing fileSharing);
	public List<FileSharing> getFileSharingByGroup(int projectId, int groupId, int clientId);
	public int deleteFileSharing(int fileSharingId);
	public FileSharing getById(int fileId);
	
}
