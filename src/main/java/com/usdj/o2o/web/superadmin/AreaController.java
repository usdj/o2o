/**
 * 
 */
package com.usdj.o2o.web.superadmin;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usdj.o2o.dto.AreaExecution;
import com.usdj.o2o.entity.Area;
import com.usdj.o2o.entity.ConstantForSuperAdmin;
import com.usdj.o2o.enums.AreaStateEnum;
import com.usdj.o2o.service.AreaService;

/**
 * @author gerrydeng
 *
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger = LoggerFactory.getLogger(AreaController.class);
	long startTime = System.currentTimeMillis();
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/listarea", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listArea() {
		logger.info("=== start ===");
		Map<String, Object> modelMap = new HashMap<>();
		List<Area> list = new ArrayList<Area>();
		try {
			list = areaService.getAreaList();
			modelMap.put(ConstantForSuperAdmin.PAGE_SIZE, list);
			modelMap.put(ConstantForSuperAdmin.TOTAL, list.size());
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
		}
		logger.error("test error!");
		long endTime = System.currentTimeMillis();
		logger.debug("costTime:[{}ms]", endTime - startTime);
		logger.info("=== end ==");
		return modelMap;
	}

	@RequestMapping(value = "/addarea", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addArea(String areaStr, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		Area area = null;
		try {
			area = mapper.readValue(areaStr, Area.class);
			area.setAreaName((area.getAreaName() == null) ? null : URLDecoder.decode(area.getAreaName(), "UTF-8"));
			area.setAreaDesc((area.getAreaDesc() == null) ? null : URLDecoder.decode(area.getAreaName(), "UTF-8"));
		} catch (Exception e) {
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
			return modelMap;
		}
		if (area != null && area.getAreaName() != null) {
			try {
				AreaExecution areaExecution = areaService.addArea(area);
				if(areaExecution.getState() == AreaStateEnum.SUCCESS.getState()) {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, true);
				} else {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
					modelMap.put(ConstantForSuperAdmin.ERROR_MSG, areaExecution.getState());
				}
			} catch (RuntimeException e) {
				modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
				modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
				return modelMap;
			}
		} else {
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, "请输入区域信息");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyarea", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyArea(String areaStr, HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		Area area = null;
		try {
			area = mapper.readValue(areaStr, Area.class);
			area.setAreaName((area.getAreaName() == null) ? null : URLDecoder.decode(area.getAreaName(), "UTF-8"));
			area.setAreaDesc((area.getAreaName() == null) ? null : URLDecoder.decode(area.getAreaDesc(), "UTF-8"));
		} catch (Exception e) {
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
			return modelMap;
		}
		if (area != null && area.getAreaId() != null) {
			try {
				AreaExecution areaExecution = areaService.modifyArea(area);
				if (areaExecution.getState() == AreaStateEnum.SUCCESS.getState()) {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, true);
				} else {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
					modelMap.put(ConstantForSuperAdmin.ERROR_MSG, areaExecution.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
				modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
				return modelMap;
			}
		} else {
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, "请输入区域信息:");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/removearea", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removearea(Long areaId) {
		Map<String, Object> modelMap = new HashMap<>();
		if (areaId != null && areaId >0) {
			try {
				AreaExecution areaExecution = areaService.removeArea(areaId);
				if(areaExecution.getState() == AreaStateEnum.SUCCESS.getState()) {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, true);
				} else {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
					modelMap.put(ConstantForSuperAdmin.ERROR_MSG, areaExecution.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
				modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
				return modelMap;
			}
		} else {
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, "请输入区域信息");
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/removeareas", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeareas(String areaIdListStr){
		Map<String, Object> modelMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Long.class);
		List<Long> areaIdList = null;
		try {
			areaIdList = mapper.readValue(areaIdListStr, javaType);
		} catch (Exception e) {
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
		}
		if (areaIdList != null && areaIdList.size() > 0 ) {
			try {
				AreaExecution areaExecution = areaService.removeAreaList(areaIdList);
				if(areaExecution.getState() == AreaStateEnum.SUCCESS.getState()) {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, true);
				} else {
					modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
					modelMap.put(ConstantForSuperAdmin.ERROR_MSG, areaExecution.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
				modelMap.put(ConstantForSuperAdmin.ERROR_MSG, e.toString());
				return modelMap;
			}
		} else {
			modelMap.put(ConstantForSuperAdmin.SUCCESS, false);
			modelMap.put(ConstantForSuperAdmin.ERROR_MSG, "请输入区域信息");
		}
		return modelMap;
	}
}
