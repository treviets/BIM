package com.redsun.hrm.dao;

import java.util.List;

import com.redsun.hrm.entities.Positions;


public interface PositionDao {
	 List<Positions> getPositionByStaff(int staffId, int clientId);
	 List<Positions> getAllPositions(int clientId);
}
