package com.redsun.hrm.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.redsun.hrm.dao.InformationDao;
import com.redsun.hrm.entities.Information;

@Service
public class InformationUtil {
	@Autowired
	InformationDao infDao;
	
	public List<Information> getInformation(String userName, int clientId) throws Exception{
		List<Information> inf = infDao.getInformation(userName, clientId);
		return inf;
	}
	
	public int getUserId(String userName, int clientId) throws Exception{
		List<Information> inf = infDao.getInformation(userName, clientId);
		return inf.get(0).getUserId();
	}
	
	public int getCompanyId(String userName, int clientId) throws Exception{
		List<Information> inf = infDao.getInformation(userName, clientId);
		return inf.get(0).getCompanyId();
	}
}
