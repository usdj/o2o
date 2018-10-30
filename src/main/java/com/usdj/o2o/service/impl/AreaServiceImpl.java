/**
 * 
 */
package com.usdj.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usdj.o2o.cache.JedisUtil;
import com.usdj.o2o.dao.AreaDao;
import com.usdj.o2o.dto.AreaExecution;
import com.usdj.o2o.entity.Area;
import com.usdj.o2o.enums.AreaStateEnum;
import com.usdj.o2o.service.AreaService;

/**
 * @author gerrydeng
 *
 */
@Service
public class AreaServiceImpl implements AreaService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.dao.AreaDao#queryArea()
	 */
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	@Autowired
	private JedisUtil.Keys jedisKeys;


	private static String AREALISTKEY = "arealist";

	@Override
	public List<Area> getAreaList() throws IOException {
		String key = AREALISTKEY;
		List<Area> areaList;
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {
			areaList = areaDao.queryArea();
			String jsonString = mapper.writeValueAsString(areaList);
			jedisStrings.set(key, jsonString);
		} else {
			String jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			areaList = mapper.readValue(jsonString, javaType);
		}
		return areaList;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.AreaService#addArea(com.usdj.o2o.entity.Area)
	 */

	private AreaExecution getAreaExecution(Area area, int effectedNum) {
		if (effectedNum > 0) {
			String key = AREALISTKEY;
			if (jedisKeys.exists(key)) {
				jedisKeys.del(key);
			}
			return new AreaExecution(AreaStateEnum.SUCCESS, area);
		} else {
			return new AreaExecution(AreaStateEnum.INNER_ERROR);
		}
	}

	private AreaExecution getAreaExecution(int effectedNum) {
		if (effectedNum > 0) {
			String key = AREALISTKEY;
			if (jedisKeys.exists(key)) {
				jedisKeys.del(key);
			}
			return new AreaExecution(AreaStateEnum.SUCCESS);
		} else {
			return new AreaExecution(AreaStateEnum.INNER_ERROR);
		}
	}

	@Override
	@Transactional
	public AreaExecution addArea(Area area) {
		if (area.getAreaName() != null && "".equals(area.getAreaName())) {
			area.setCreateTime(new Date());
			area.setLastEditTime(new Date());
			try {
				int effectedNum = areaDao.insertArea(area);
				return getAreaExecution(area, effectedNum);
			} catch (Exception e) {
				throw new RuntimeException("添加区域信息失败：" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.AreaService#modifyArea(com.usdj.o2o.entity.Area)
	 */
	@Override
	@Transactional
	public AreaExecution modifyArea(Area area) {
		if (area.getAreaId() != null && area.getAreaId() > 0) {
			area.setLastEditTime(new Date());
			try {
				int effectedNum = areaDao.updateArea(area);
				return getAreaExecution(area, effectedNum);
			} catch (Exception e) {
				throw new RuntimeException("更新区域信息失败:" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.AreaService#removeArea(com.usdj.o2o.entity.Area)
	 */
	@Override
	@Transactional
	public AreaExecution removeArea(long areaId) {
		if (areaId > 0) {
			try {
				int effectedNum = areaDao.deleteArea(areaId);
				return getAreaExecution(effectedNum);
			} catch (Exception e) {
				return new AreaExecution(AreaStateEnum.EMPTY);
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.usdj.o2o.service.AreaService#removeAreaList(java.util.List)
	 */
	@Override
	@Transactional
	public AreaExecution removeAreaList(List<Long> areaIdList) {
		if (areaIdList != null && areaIdList.size() > 0) {
			try {
				int effectedNum = areaDao.batchDeleteArea(areaIdList);
				return getAreaExecution(effectedNum);
			} catch (Exception e) {
				throw new RuntimeException("删除区域信息失败：" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

}
