package com.cissst.software.model;

import java.util.List;

public class PagePerson {
	/**
	 * ÿҳ��ʾ�ļ�¼����
	 */
	private List<Person> persons;
	
	/**
	 * Ҫ��ѯ����Ϣ���ܼ�¼��
	 */
	private int total;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
