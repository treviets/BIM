package com.redsun.hrm.dao;

import java.util.List;
import com.redsun.hrm.entities.Information;


public interface InformationDao {
	 List<Information> getInformation(String userName, int clientId);
}
