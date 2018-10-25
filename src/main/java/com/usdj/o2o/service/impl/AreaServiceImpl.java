/**
 * 
 */
package com.usdj.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usdj.o2o.dao.AreaDao;
import com.usdj.o2o.entity.Area;
import com.usdj.o2o.service.AreaService;

/**
 * @author gerrydeng
 *
 */
@Service
public class AreaServiceImpl implements AreaService {

	/* (non-Javadoc)
	 * @see com.usdj.o2o.dao.AreaDao#queryArea()
	 */
	@Autowired
	private AreaDao areaDao;
	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}

}
