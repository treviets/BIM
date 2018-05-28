package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.AttachmentsDao;
import com.redsun.service.dao.QuotationsDao;
import com.redsun.service.entities.Attachments;
import com.redsun.service.entities.Quotations;
import com.redsun.service.entities.Result;
import com.redsun.service.service.QuotationsService;
import com.redsun.service.utils.FileUtil;
import com.redsun.service.utils.ReferencablesConstants;

/**
 * Quotations Service implementation 
 */
@Service
public class QuotationsServiceImpl implements QuotationsService {
	
    @Autowired
    private QuotationsDao quotationsDao;
    
    @Autowired
    private AttachmentsDao attachmentsDao;
	
	@Autowired
	private ServletContext servletContext;

    @Autowired
    private Environment env;
	
    // Insert.‰
	@Override
	public Result insert(final Quotations quotations) {
		// Call DAO.
		final int quotationId = quotationsDao.insert(quotations);
		
		// Get attachments.
		List<Attachments> attachments = quotations.getAttachments();
		if(null != attachments) {
			// Insert new attachments.
			for(Attachments attachment : attachments) {
				attachment.setClientId(quotations.getClientId());
				attachment.setRefType(ReferencablesConstants.QUOTATION_TYPE);
				attachment.setRefId(quotationId);
				attachment.setSubDirectory(env.getProperty("attachment.path.quotations") + quotationId + "\\");
				attachment.setId(attachmentsDao.insert(attachment));
			}
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", quotationId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Quotations quotations) {
		// Call DAO.
		final int quotationId = quotationsDao.update(quotations);
		
		//String absolutePath = FileUtil.attachmentsPath + "quotations\\";
		// Get attachments in a current quotations.
		List<Attachments> attachments = quotations.getAttachments();
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.QUOTATION_TYPE, quotationId);
		
		// Insert/Update attachments.
		if(null != attachments) {
			for(Attachments attachment : attachments) {
				// Add new.
				if(attachment.getId() == null) {
					attachment.setClientId(quotations.getClientId());
					attachment.setRefType(ReferencablesConstants.QUOTATION_TYPE);
					attachment.setRefId(quotationId);
					attachment.setSubDirectory(env.getProperty("attachment.path.quotations") + quotationId + "\\");
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
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.quotations") + quotationId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", quotationId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer quotationId) {
		// Call DAO.
		final int data = quotationsDao.delete(quotationId);
		
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.QUOTATION_TYPE, quotationId);

		// Delete old attachments by quotationId.
		attachmentsDao.deleteByRefId(quotationId);
		// Delete file.
		for(Attachments oriAttachment : oriAttachments) {
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.quotations") + quotationId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer quotationId) {
		// Call DAO.
		final List<Quotations> data = quotationsDao.getById(quotationId);
		
		// Get attachments of quotations.
		for(Quotations quotations : data) {
			List<Attachments> attachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.QUOTATION_TYPE, quotations.getId());
			quotations.setAttachments(attachments);
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("quotations", data);
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer quotationId) {
		// Call DAO.
		final boolean data = quotationsDao.exists(quotationId);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = quotationsDao.count();

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listQuotationsForPageAndFilter(final int itemsPerPage, final int pageNo, final Quotations quotations) {
		// Call DAO.
		final List<Quotations> data = quotationsDao.listQuotationsForPageAndFilter(itemsPerPage, pageNo, quotations);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("quotations", data);
		return new Result(result, true);
	}

	// List extend for page and filter.
	public Result listQuotationsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
		// Call DAO.
		final List<Map<Object, Object>> data = quotationsDao.listQuotationsExtendForPageAndFilter(itemsPerPage, pageNo, filter);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("quotations", data);
		return new Result(result, true);
		
	}

}
