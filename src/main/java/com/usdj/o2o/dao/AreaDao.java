package com.usdj.o2o.dao;

import java.util.List;

import com.usdj.o2o.entity.Area;

public interface AreaDao {
	/**
	 * List Area list
	 * @return areaList
	 */
	List<Area> queryArea();
	
	int insertArea(Area area);
	
	int updateArea(Area area);
	
	int deleteArea(long areaId);
	
	int batchDeleteArea(List<Long> areaIdList);
}
