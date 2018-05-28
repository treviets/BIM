package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.AttachmentsDao;
import com.redsun.service.dao.CommandsDao;
import com.redsun.service.entities.Attachments;
import com.redsun.service.entities.Commands;
import com.redsun.service.entities.Result;
import com.redsun.service.service.CommandsService;
import com.redsun.service.utils.FileUtil;
import com.redsun.service.utils.ReferencablesConstants;

/**
 * Commands Service implementation 
 */
@Service
public class CommandsServiceImpl implements CommandsService {
	
    @Autowired
    private CommandsDao commandsDao;
    
    @Autowired
    private AttachmentsDao attachmentsDao;
	
	@Autowired
	private ServletContext servletContext;

    @Autowired
    private Environment env;
	
    // Insert.
	@Override
	public Result insert(final Commands commands) {
		// Call DAO.
		final int commandId = commandsDao.insert(commands);
		
		// Get attachments.
		List<Attachments> attachments = commands.getAttachments();
		if(null != attachments) {
			// Insert new attachments.
			for(Attachments attachment : attachments) {
				attachment.setClientId(commands.getClientId());
				attachment.setRefType(ReferencablesConstants.COMMAND_TYPE);
				attachment.setRefId(commandId);
				attachment.setSubDirectory(env.getProperty("attachment.path.commands") + commandId + "\\");
				attachment.setId(attachmentsDao.insert(attachment));
			}
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", commandId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Commands commands) {
		// Call DAO.
		final int commandId = commandsDao.update(commands);
		
		//String absolutePath = FileUtil.attachmentsPath + "commands\\";
		// Get attachments in a current commands.
		List<Attachments> attachments = commands.getAttachments();
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.COMMAND_TYPE, commandId);
		
		// Insert/Update attachments.
		if(null != attachments) {
			for(Attachments attachment : attachments) {
				// Add new.
				if(attachment.getId() == null) {
					attachment.setClientId(commands.getClientId());
					attachment.setRefType(ReferencablesConstants.COMMAND_TYPE);
					attachment.setRefId(commandId);
					attachment.setSubDirectory(env.getProperty("attachment.path.commands") + commandId + "\\");
					Integer attachmentId = attachmentsDao.insert(attachment);
					attachment.setId(attachmentId);
				}
				// Update.
				else {
					for(Attachments oriAttachment : oriAttachments) {
						if(oriAttachment.getId() == attachment.getId()) {
							Attachments updateAttachment = oriAttachment;
							oriAttachments.remove(oriAttachment); // Remove
							updateAttachment.setFileName(attachment.getFileName());
							updateAttachment.setFileSize(attachment.getFileSize());
							updateAttachment.setMimeType(attachment.getMimeType());
							attachmentsDao.update(updateAttachment);
							attachment = updateAttachment;
							
							break;
						}
					}
				}
			}
		}
		// Delete remain original attachments.
		for(Attachments oriAttachment : oriAttachments) {
			attachmentsDao.delete(oriAttachment.getId());
			// Delete file.
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.commands") + commandId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", commandId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer commandId) {
		// Call DAO.
		final int data = commandsDao.delete(commandId);
		
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.COMMAND_TYPE, commandId);

		// Delete old attachments by commandId.
		attachmentsDao.deleteByRefId(commandId);
		// Delete file.
		for(Attachments oriAttachment : oriAttachments) {
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.commands") + commandId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer commandId) {
		// Call DAO.
		final List<Commands> data = commandsDao.getById(commandId);
		
		// Get attachments of commands.
		for(Commands commands : data) {
			List<Attachments> attachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.COMMAND_TYPE, commands.getId());
			commands.setAttachments(attachments);
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("commands", data);
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer commandId) {
		// Call DAO.
		final boolean data = commandsDao.exists(commandId);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = commandsDao.count();

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listCommandsForPageAndFilter(final int itemsPerPage, final int pageNo, final Commands commands) {
		// Call DAO.
		final List<Commands> data = commandsDao.listCommandsForPageAndFilter(itemsPerPage, pageNo, commands);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("commands", data);
		return new Result(result, true);
	}

	// List extend for page and filter.
	public Result listCommandsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
		// Call DAO.
		final List<Map<Object, Object>> data = commandsDao.listCommandsExtendForPageAndFilter(itemsPerPage, pageNo, filter);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("commands", data);
		return new Result(result, true);
		
	}

}
