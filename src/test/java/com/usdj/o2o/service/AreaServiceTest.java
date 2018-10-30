/**
 * 
 */
package com.usdj.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.usdj.o2o.BaseTest;
import com.usdj.o2o.entity.Area;

/**
 * @author gerrydeng
 *
 */
public class AreaServiceTest extends BaseTest {
	@Autowired
	private AreaService areaService;
	@Test
	public void testGetAreaList() throws JsonParseException, IOException {
		List<Area> areaList= areaService.getAreaList();
		assertEquals("东莞",areaList.get(0).getAreaName());
	}
}
