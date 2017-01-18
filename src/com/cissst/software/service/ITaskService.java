package com.cissst.software.service;


import com.cissst.software.model.PageTask;
import com.cissst.software.model.Task;

public interface ITaskService {
	
	public boolean updateTask(Task task);
	
    public boolean addTask(Task task);
	
	public boolean deleteTask(Task task);


	/**
	 * ��������ѯ
	 * @param name
	 * @return
	 */
	public PageTask allquery(int currpage, int rows,int proid);
	/**
	 * ��ѯ����
	 * @param currpage
	 * @param rows
	 * @return
	 */
	public PageTask allquery(int currpage,int rows);
	
	/**
	 * ��ѯ
	 */
	public Task getTaskById(int taskid);
}
