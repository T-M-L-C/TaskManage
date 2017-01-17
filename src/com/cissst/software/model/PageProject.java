package com.cissst.software.model;

import java.util.List;
import java.util.Map;

public class PageProject {

	private List<Map<String,Object>> datas;
	
	private int total;




	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
