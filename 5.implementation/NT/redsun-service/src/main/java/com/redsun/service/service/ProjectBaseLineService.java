package com.redsun.service.service;

import com.redsun.service.entities.BaseLines;
import com.redsun.service.entities.Result;

public interface ProjectBaseLineService {
	// insert
	Result insertLaborBaseline(int projectId, int clientId, BaseLines baseline);
	Result insertMaterialBaseline(int projectId, int clientId, BaseLines baseline);
	Result insertEquipmentBaseline(int projectId, int clientId, BaseLines baseline);
	Result insertTaskBaseline(int projectId, int clientId, BaseLines baseline);
	Result insertTaskResourceBaseline(int projectId, int clientId, BaseLines baseline);
	Result insertTaskMaterialBaseline(int projectId, int clientId, BaseLines baseline);
	Result insertTaskEquipmentBaseline(int projectId, int clientId, BaseLines baseline);
	Result getLaborBaseline(int projectId, int clientId, int baselineId);
	Result getBaseLineByType(int projectId, int type, int clientId);

}
