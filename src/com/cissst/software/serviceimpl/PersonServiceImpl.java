package com.cissst.software.serviceimpl;

import java.util.List;
import java.util.Map;

import com.cissst.software.dao.PersonMysqlDao;
import com.cissst.software.model.PagePerson;
import com.cissst.software.model.Person;
import com.cissst.software.service.IPersonService;

public class PersonServiceImpl implements IPersonService {

	//创建员工数据访问层对象
	private PersonMysqlDao personMysqlDao = new PersonMysqlDao();

	public boolean addPerson(Person person) {
		// TODO Auto-generated method stub
		return personMysqlDao.addPerson(person);
	}
	
	public boolean updatePerson(Person person) {
		// TODO Auto-generated method stub
		return personMysqlDao.updatePerson(person);
	}

	public boolean deletePerson(Person person) {
		// TODO Auto-generated method stub
		return personMysqlDao.deletePerson(person);
	}
/*	
	@Override
	public List<Person> allquery(){
		return personMysqlDao.getPersonAll();
		

	}
*/
	public PagePerson allquery(int currpage, int rows,String name) {
		// TODO Auto-generated method stub
		return personMysqlDao.getPersonAll(currpage,rows,name);
	}

	public PagePerson allquery(int currpage, int rows) {
		// TODO Auto-generated method stub
		return personMysqlDao.getPersonAll(currpage, rows);
	}

	public List<Map> addInputPerson() {
		
		return personMysqlDao.addInputPerson();
	}


	public List<Person> queryInstance(String loginName) {
		
		return personMysqlDao.queryPerson(loginName);
	}

	
}
