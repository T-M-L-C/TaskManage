package com.cissst.software.model;

import java.util.List;
import java.util.Map;

public class PageTask {
	/**
	 * 每页显示的记录内容
	 */
	private List<Map<String,Object>> tasks;
  /**
	 * 要查询表信息的总记录数
	 */
	 private int total;

public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public List<Map<String, Object>> getTasks() {
	return tasks;
}
public void setTasks(List<Map<String, Object>> tasks) {
	this.tasks = tasks;
}

	 
}
