package com.cissst.software.serviceimpl;

import com.cissst.software.dao.PersonProjectMysqlDao;
import com.cissst.software.model.PagePersonProject;
import com.cissst.software.model.PersonProject;
import com.cissst.software.service.IPersonProjectService;

public class PersonProjectServiceImpl implements IPersonProjectService {
    private PersonProjectMysqlDao personProjectMysqlDao=new PersonProjectMysqlDao();
	/**
	 * ��ѯ��Ŀ��Ա��Ϣ
	 */
	public PagePersonProject displayPersonProject(int currpage, int rows) {
		
		return personProjectMysqlDao.displayPersonProject(currpage, rows);
	}
	
	/**
	 *���һ����Ŀ��Ա		
	 */
	public boolean addPersonProject(PersonProject personProject){
		return personProjectMysqlDao.addPersonProject(personProject);
	}
    /**
     * ɾ��һ����Ŀ���Ա
     */
	public boolean deletePersonProject(PersonProject personProject){
		return personProjectMysqlDao.deletePersonProject(personProject);
	}
	/**
	 * ����һ����Ŀ���Ա
	 */
	public boolean updatePersonProject(PersonProject personProject){
		return personProjectMysqlDao.updatePersonProject(personProject);
	}
   
}
