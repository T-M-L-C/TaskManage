package com.cissst.software.service;


import com.cissst.software.model.PageTask;
import com.cissst.software.model.Task;

public interface ITaskService {
	
	public boolean updateTask(Task task);
	
    public boolean addTask(Task task);
	
	public boolean deleteTask(Task task);


	/**
	 * 按条件查询
	 * @param name
	 * @return
	 */
	public PageTask allquery(int currpage, int rows,int proid);
	/**
	 * 查询所有
	 * @param currpage
	 * @param rows
	 * @return
	 */
	public PageTask allquery(int currpage,int rows);
	
	/**
	 * 查询
	 */
	public Task getTaskById(int taskid);
}
