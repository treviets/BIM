package com.redsun.service.dao;

import java.util.List;
import java.util.Map;

import com.redsun.service.entities.CallForTenders;

public interface CallForTendersDao {

	List<Map<Object, Object>> getAll(final Integer clientId, final Integer pageNo, final Integer itemsPerPage);
	List<CallForTenders> getById(final Integer clientId, final Integer id);
	Integer insert(final CallForTenders callForTenders);
	Integer update(final CallForTenders callForTenders);
	Integer delete(final Integer id);
}
