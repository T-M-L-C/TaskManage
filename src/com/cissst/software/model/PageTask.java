package com.cissst.software.model;

import java.util.List;
import java.util.Map;

public class PageTask {
	/**
	 * ÿҳ��ʾ�ļ�¼����
	 */
	private List<Map<String,Object>> tasks;
  /**
	 * Ҫ��ѯ����Ϣ���ܼ�¼��
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
