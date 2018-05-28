package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Attachments;

/**
 * Attachments DAO interface
 */
public interface AttachmentsDao {
	
	Integer insert(final Attachments attachments) ;
	int update(final Attachments attachments) ;
	int delete(final Integer id) ;
	List<Attachments> getById(final Integer id) ;
	boolean exists(final Integer id) ;
	long count() ;
	List<Attachments> listAttachmentsForPageAndFilter(final int itemsPerPage, final int pageNo, final Attachments attachments);
	int deleteByRefId(final Integer refId);
	List<Attachments> listAttachmentsByRefId(final Integer refId);
	List<Attachments> listAttachmentsByRef(final String refType, final Integer refId);
}
