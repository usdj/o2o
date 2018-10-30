/**
 * 
 */
package com.usdj.o2o.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.usdj.o2o.dto.AreaExecution;
import com.usdj.o2o.entity.Area;

/**
 * @author gerrydeng
 *
 */
public interface AreaService {
	List<Area> getAreaList() throws JsonParseException, JsonParseException, IOException;
	
	
	
	/**
	 * @param area
	 * @return
	 */
	AreaExecution addArea(Area area);
	
	/**
	 * @param area
	 * @return
	 */
	AreaExecution modifyArea(Area area);
	
	/**
	 * @param area
	 * @return
	 */
	AreaExecution removeArea(long areaId);
	
	/**
	 * @param areaIdList
	 * @return
	 */
	AreaExecution removeAreaList(List<Long> areaIdList);
	
}
