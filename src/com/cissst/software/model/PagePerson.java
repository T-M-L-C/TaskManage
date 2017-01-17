package com.cissst.software.model;

import java.util.List;

public class PagePerson {
	/**
	 * 每页显示的记录内容
	 */
	private List<Person> persons;
	
	/**
	 * 要查询表信息的总记录数
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
