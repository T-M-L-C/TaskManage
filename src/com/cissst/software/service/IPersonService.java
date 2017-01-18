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
	 * ��ѯ��Ա������Ϣ
	 * @return
	 */
	/*
	public List<Person> allquery();
	
	*/
	/**
	 * ��������ѯ
	 * @param name
	 * @return
	 */
	public PagePerson allquery(int currpage, int rows,String name);
	
	/**
	 * ��ҳ��ѯ
	 * @param currpage
	 * @param rows
	 * @return
	 */
	public PagePerson allquery(int currpage, int rows);
		
	public List<Map> addInputPerson();
	
	public List<Person> queryInstance(String loginName);

}

