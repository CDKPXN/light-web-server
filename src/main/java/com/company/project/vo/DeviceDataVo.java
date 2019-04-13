package com.company.project.vo;

import java.util.List;

public class DeviceDataVo {
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "DeviceDataVo [ids=" + ids + "]";
	}

	
}
