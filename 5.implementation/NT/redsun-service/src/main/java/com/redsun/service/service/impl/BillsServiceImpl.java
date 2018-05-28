package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.AttachmentsDao;
import com.redsun.service.dao.BillsDao;
import com.redsun.service.entities.Attachments;
import com.redsun.service.entities.Bills;
import com.redsun.service.entities.Result;
import com.redsun.service.service.BillsService;
import com.redsun.service.utils.FileUtil;
import com.redsun.service.utils.ReferencablesConstants;

/**
 * Bills Service implementation 
 */
@Service
public class BillsServiceImpl implements BillsService {
	
    @Autowired
    private BillsDao billsDao;
    
    @Autowired
    private AttachmentsDao attachmentsDao;
	
	@Autowired
	private ServletContext servletContext;

    @Autowired
    private Environment env;
	
    // Insert.
	@Override
	public Result insert(final Bills bills) {
		// Call DAO.
		final int billId = billsDao.insert(bills);
		
		// Get attachments.
		List<Attachments> attachments = bills.getAttachments();
		if(null != attachments) {
			// Insert new attachments.
			for(Attachments attachment : attachments) {
				attachment.setClientId(bills.getClientId());
				attachment.setRefType(ReferencablesConstants.BILL_TYPE);
				attachment.setRefId(billId);
				attachment.setSubDirectory(env.getProperty("attachment.path.bills") + billId + "\\");
				attachment.setId(attachmentsDao.insert(attachment));
			}
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", billId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Bills bills) {
		// Call DAO.
		final int billId = billsDao.update(bills);
		
		//String absolutePath = FileUtil.attachmentsPath + "bills\\";
		// Get attachments in a current bills.
		List<Attachments> attachments = bills.getAttachments();
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.BILL_TYPE, billId);
		
		// Insert/Update attachments.
		if(null != attachments) {
			for(Attachments attachment : attachments) {
				// Add new.
				if(attachment.getId() == null) {
					attachment.setClientId(bills.getClientId());
					attachment.setRefType(ReferencablesConstants.BILL_TYPE);
					attachment.setRefId(billId);
					attachment.setSubDirectory(env.getProperty("attachment.path.bill") + billId + "\\");
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
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.bill") + billId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", billId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer billId) {
		// Call DAO.
		final int data = billsDao.delete(billId);
		
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.BILL_TYPE, billId);

		// Delete old attachments by billId.
		attachmentsDao.deleteByRefId(billId);
		// Delete file.
		for(Attachments oriAttachment : oriAttachments) {
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.bill") + billId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer billId) {
		// Call DAO.
		final List<Bills> data = billsDao.getById(billId);
		
		// Get attachments of bills.
		for(Bills bills : data) {
			List<Attachments> attachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.BILL_TYPE, bills.getId());
			bills.setAttachments(attachments);
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("bills", data);
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer billId) {
		// Call DAO.
		final boolean data = billsDao.exists(billId);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = billsDao.count();

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listBillsForPageAndFilter(final int itemsPerPage, final int pageNo, final Bills bills) {
		// Call DAO.
		final List<Bills> data = billsDao.listBillsForPageAndFilter(itemsPerPage, pageNo, bills);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("bills", data);
		return new Result(result, true);
	}

	// List extend for page and filter.
	public Result listBillsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
		// Call DAO.
		final List<Map<Object, Object>> data = billsDao.listBillsExtendForPageAndFilter(itemsPerPage, pageNo, filter);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("bills", data);
		return new Result(result, true);
		
	}

}
