package com.cissst.software.serviceimpl;

import com.cissst.software.dao.PersonProjectMysqlDao;
import com.cissst.software.model.PagePersonProject;
import com.cissst.software.model.PersonProject;
import com.cissst.software.service.IPersonProjectService;

public class PersonProjectServiceImpl implements IPersonProjectService {
    private PersonProjectMysqlDao personProjectMysqlDao=new PersonProjectMysqlDao();
	/**
	 * 查询项目成员信息
	 */
	public PagePersonProject displayPersonProject(int currpage, int rows) {
		
		return personProjectMysqlDao.displayPersonProject(currpage, rows);
	}
	
	/**
	 *添加一个项目成员		
	 */
	public boolean addPersonProject(PersonProject personProject){
		return personProjectMysqlDao.addPersonProject(personProject);
	}
    /**
     * 删除一个项目组成员
     */
	public boolean deletePersonProject(PersonProject personProject){
		return personProjectMysqlDao.deletePersonProject(personProject);
	}
	/**
	 * 更新一个项目组成员
	 */
	public boolean updatePersonProject(PersonProject personProject){
		return personProjectMysqlDao.updatePersonProject(personProject);
	}
   
}
