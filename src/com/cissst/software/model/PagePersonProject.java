package com.cissst.software.model;

import java.util.List;
import java.util.Map;

public class PagePersonProject {

	private List<Map<String,Object>> personprojects;
	private int total;
	
	

	public List<Map<String, Object>> getPersonprojects() {
		return personprojects;
	}
	public void setPersonprojects(List<Map<String, Object>> personprojects) {
		this.personprojects = personprojects;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
