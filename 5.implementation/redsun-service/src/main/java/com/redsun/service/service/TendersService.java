package com.redsun.service.service;

import com.redsun.service.entities.Tenders;
import com.redsun.service.entities.Result;

import java.io.IOException;

/**
 * Tenders Service interface
 */
public interface TendersService {
	
	Result insert(final Tenders tenders) ;
	Result update(final Tenders tenders) ;
	Result delete(final Integer id, final String refType) throws IOException;
	Result getById(final Integer id) ;
	Result exists(final Integer id) ;
	Result count() ;
	Result listTendersForPageAndFilter(final int itemsPerPage, final int pageNo, final Tenders tenders);

}
