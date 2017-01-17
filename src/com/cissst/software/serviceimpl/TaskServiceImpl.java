package com.cissst.software.serviceimpl;

import com.cissst.software.dao.TaskMySqlDao;
import com.cissst.software.model.PageTask;
import com.cissst.software.model.Task;
import com.cissst.software.service.ITaskService;



public class TaskServiceImpl  implements ITaskService{
  
	private TaskMySqlDao taskMySqlDao=new TaskMySqlDao();
	public boolean updateTask(Task task) {
		return taskMySqlDao.updateTask(task);
	}

	public boolean addTask(Task task) {
		return taskMySqlDao.addTask(task);
	}

	public boolean deleteTask(Task task) {
		return taskMySqlDao.deleteTask(task);
	}

	public PageTask allquery(int currpage, int rows, int proid) {

		return taskMySqlDao.allquery(currpage, rows,  proid);
	}

	public PageTask allquery(int currpage, int rows) {
		// TODO Auto-generated method stub
		return taskMySqlDao.allquery(currpage, rows);
	}

	@Override
	public Task getTaskById(int taskid) {
		// TODO Auto-generated method stub
		return taskMySqlDao.getTaskById(taskid);
	}

}
