package com.redsun.service.service;

import com.redsun.service.entities.Criticality;
import com.redsun.service.entities.Result;

public interface CriticalityService {
	Result getCriticality(int itemsPerPage, int pageNo);

	Result listAll(int clientId);

	Result insert(Criticality criticality);

	Result update(Criticality criticality);

	Result delete(Integer id);

}
