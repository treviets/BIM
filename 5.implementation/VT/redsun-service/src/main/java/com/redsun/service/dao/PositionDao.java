package com.redsun.service.dao;

import java.util.List;

import com.redsun.service.entities.Position;

public interface PositionDao {
	 List<Position> listPositions(int clientId, String code, String name);
	 List<Position> getPositionDetails(int id);
}
