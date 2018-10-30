/**
 * 
 */
package com.usdj.o2o.dto;

import java.util.List;

import com.usdj.o2o.entity.Area;
import com.usdj.o2o.enums.AreaStateEnum;

/**
 * @author gerrydeng
 *
 */
public class AreaExecution {
	private int state;
	private String stateInfo;
	private int count;
	private Area area;
	private List<Area> areaList;
	
	/**
	 * 
	 */
	public AreaExecution() {
		// TODO Auto-generated constructor stub
	}
	
	public AreaExecution(AreaStateEnum areaStateEnum) {
		this.state = areaStateEnum.getState();
		this.stateInfo = areaStateEnum.getStateInfo();
	}
	
	public AreaExecution(AreaStateEnum areaStateEnum, Area area) {
		this.state = areaStateEnum.getState();
		this.stateInfo = areaStateEnum.getStateInfo();
		this.area = area;
	}
	
	public AreaExecution(AreaStateEnum areaStateEnum, List<Area> areaList) {
		this.state = areaStateEnum.getState();
		this.stateInfo = areaStateEnum.getStateInfo();
		this.areaList = areaList;
	}
	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
