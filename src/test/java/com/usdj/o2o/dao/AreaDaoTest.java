/**
 * 
 */
package com.usdj.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.usdj.o2o.BaseTest;
import com.usdj.o2o.entity.Area;

/**
 * @author gerrydeng
 *
 */
public class AreaDaoTest extends BaseTest {
	@Autowired
	private AreaDao areaDao;

	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		assertEquals(2, areaList.size());
	}
	
	@Test
	public void testInsertArea() {
		Area area = new Area();
		area.setAreaName("区域1");
		area.setAreaDesc("区域1");
		area.setPriority(11);
		area.setCreateTime(new Date());
		area.setLastEditTime(new Date());
		int effectedNum = areaDao.insertArea(area);
		assertEquals(1, effectedNum);
	}
	
	@Test
	public void testUpdateArea() {
		Area area = new Area();
		area.setAreaId(1L);
		area.setAreaName("深圳");
		area.setLastEditTime(new Date());
		int effectedNum = areaDao.updateArea(area);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testDeleteArea() {
		int effetedNum = areaDao.deleteArea(3);
		assertEquals(1, effetedNum);
	}
	
	@Test
	public void testBatchDeleteArea() {
		long areaId = -1;
		List<Area> areaList = areaDao.queryArea();
		List<Long> areaIdList = new ArrayList<>();
		Integer x = 11;
		for(Area myArea : areaList) {
			if(x.equals(myArea.getPriority())) {
				areaId = myArea.getAreaId();
				areaIdList.add(areaId);
			}
		}
		int effectedNum = areaDao.batchDeleteArea(areaIdList);
		assertEquals(1, effectedNum);
	}
}
