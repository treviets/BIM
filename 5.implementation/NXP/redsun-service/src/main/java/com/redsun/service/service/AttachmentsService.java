package com.redsun.service.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.redsun.service.entities.Attachments;
import com.redsun.service.entities.Result;

/**
 * Attachments Service interface
 */
public interface AttachmentsService {
	
	Result insert(final Attachments attachments) ;
	Result update(final Attachments attachments) ;
	Result delete(final Integer id) throws IOException;
	Result getById(final Integer id) ;
	Result exists(final Integer id) ;
	Result count() ;
	Result listAttachmentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Attachments attachments);
	Result getByRefId(final String refType,final Integer refId);
	Result upload(final String refType, final Integer refId, final MultipartFile file) throws IOException;
	Result download(final Integer id);
}
