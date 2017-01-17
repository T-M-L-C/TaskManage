package com.cissst.software.service;

import com.cissst.software.model.PagePersonProject;
import com.cissst.software.model.PersonProject;

public interface IPersonProjectService {

	public PagePersonProject displayPersonProject(int currpage, int rows);
	
	public boolean addPersonProject(PersonProject personProject);
	
	public boolean deletePersonProject(PersonProject personProject);
	
	public boolean updatePersonProject(PersonProject personProject);
}

