package com.cissst.software.service;

import java.util.List;
import java.util.Map;

import com.cissst.software.model.PagePerson;
import com.cissst.software.model.Person;

public interface IPersonService {
	
	public boolean addPerson(Person person);

	
	public boolean updatePerson(Person person);
	

	public boolean deletePerson(Person person);

	/**
	 * 查询人员所有信息
	 * @return
	 */
	/*
	public List<Person> allquery();
	
	*/
	/**
	 * 按条件查询
	 * @param name
	 * @return
	 */
	public PagePerson allquery(int currpage, int rows,String name);
	
	/**
	 * 分页查询
	 * @param currpage
	 * @param rows
	 * @return
	 */
	public PagePerson allquery(int currpage, int rows);
		
	public List<Map> addInputPerson();
	
	public List<Person> queryInstance(String loginName);

}

