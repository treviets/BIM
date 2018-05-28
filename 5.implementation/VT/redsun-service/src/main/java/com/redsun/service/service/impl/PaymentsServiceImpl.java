package com.redsun.service.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.redsun.service.dao.AttachmentsDao;
import com.redsun.service.dao.PaymentsDao;
import com.redsun.service.entities.Attachments;
import com.redsun.service.entities.Payments;
import com.redsun.service.entities.Result;
import com.redsun.service.service.PaymentsService;
import com.redsun.service.utils.FileUtil;
import com.redsun.service.utils.ReferencablesConstants;

/**
 * Payments Service implementation 
 */
@Service
public class PaymentsServiceImpl implements PaymentsService {
	
    @Autowired
    private PaymentsDao paymentsDao;
    
    @Autowired
    private AttachmentsDao attachmentsDao;
	
	@Autowired
	private ServletContext servletContext;

    @Autowired
    private Environment env;
	
    // Insert.
	@Override
	public Result insert(final Payments payments) {
		// Call DAO.
		final int paymentId = paymentsDao.insert(payments);
		
		// Get attachments.
		List<Attachments> attachments = payments.getAttachments();
		if(null != attachments) {
			// Insert new attachments.
			for(Attachments attachment : attachments) {
				attachment.setClientId(payments.getClientId());
				attachment.setRefType(ReferencablesConstants.PAYMENT_TYPE);
				attachment.setRefId(paymentId);
				attachment.setSubDirectory(env.getProperty("attachment.path.payments") + paymentId + "\\");
				attachment.setId(attachmentsDao.insert(attachment));
			}
		}
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", paymentId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}

    // Update.
	@Override
	public Result update(final Payments payments) {
		// Call DAO.
		final int paymentId = paymentsDao.update(payments);
		
		//String absolutePath = FileUtil.attachmentsPath + "payments\\";
		// Get attachments in a current payments.
		List<Attachments> attachments = payments.getAttachments();
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.PAYMENT_TYPE, paymentId);
		
		// Insert/Update attachments.
		if(null != attachments) {
			for(Attachments attachment : attachments) {
				// Add new.
				if(attachment.getId() == null) {
					attachment.setClientId(payments.getClientId());
					attachment.setRefType(ReferencablesConstants.PAYMENT_TYPE);
					attachment.setRefId(paymentId);
					attachment.setSubDirectory(env.getProperty("attachment.path.payments") + paymentId + "\\");
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
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.payments") + paymentId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", paymentId);
		result.put("attachments", attachments);
		return new Result(result, true);
	}	

    // Delete.
	@Override
	public Result delete(final Integer paymentId) {
		// Call DAO.
		final int data = paymentsDao.delete(paymentId);
		
		// Get original attachments in db.
		List<Attachments> oriAttachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.PAYMENT_TYPE, paymentId);

		// Delete old attachments by paymentId.
		attachmentsDao.deleteByRefId(paymentId);
		// Delete file.
		for(Attachments oriAttachment : oriAttachments) {
			FileUtil.deleteFileFromLocal(servletContext.getRealPath("/") + env.getProperty("attachment.path.payments") + paymentId + "\\" + oriAttachment.getFileName());
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", data);
		return new Result(result, true);
	}

	// Get By Id.
	@Override
	public Result getById(final Integer paymentId) {
		// Call DAO.
		final List<Payments> data = paymentsDao.getById(paymentId);
		
		// Get attachments of payments.
		for(Payments payments : data) {
			List<Attachments> attachments = attachmentsDao.listAttachmentsByRef(ReferencablesConstants.PAYMENT_TYPE, payments.getId());
			payments.setAttachments(attachments);
		}
		
		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("payments", data);
		return new Result(result, true);
	}

    // Exists.
	@Override
	public Result exists(final Integer paymentId) {
		// Call DAO.
		final boolean data = paymentsDao.exists(paymentId);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("exists", data);
		return new Result(result, true);
	}

    // Count
	@Override
	public Result count() {
		// Call DAO.
		final long data = paymentsDao.count();

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("count", data);
		return new Result(result, true);
	}

	// List for page and filter.
	public Result listPaymentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Payments payments) {
		// Call DAO.
		final List<Payments> data = paymentsDao.listPaymentsForPageAndFilter(itemsPerPage, pageNo, payments);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("payments", data);
		return new Result(result, true);
	}

	// List extend for page and filter.
	public Result listPaymentsExtendForPageAndFilter(final int itemsPerPage, final int pageNo, final Map<String, Object> filter) {
		// Call DAO.
		final List<Map<Object, Object>> data = paymentsDao.listPaymentsExtendForPageAndFilter(itemsPerPage, pageNo, filter);

		// Return.
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("payments", data);
		return new Result(result, true);
		
	}

}
