package com.redsun.service.service;

import com.redsun.service.entities.CallForTenders;
import com.redsun.service.entities.Result;

import java.io.IOException;

public interface CallForTendersService {

	Result getAll(final Integer clientId, final Integer pageNo, final Integer itemsPerPage);
	Result getById(final Integer clientId, final Integer tenderId);
	Result delete(final String refType, final Integer id) throws IOException;
	Result insert(final CallForTenders callForTenders);
	Result update(final CallForTenders callForTenders);
}
