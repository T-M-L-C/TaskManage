package com.cissst.software.service;

import java.util.List;
import java.util.Map;

import com.cissst.software.model.PageProject;
import com.cissst.software.model.Project;

public interface IProjectService {

	/**
	 * ∑÷“≥≤È—Ø
	 * @param currpage
	 * @param rows
	 * @return
	 */
	public PageProject allquery(int currpage, int rows); 
	
	public boolean addProject(Project project);
	
	public boolean updateProject(Project project);
	
	public boolean deleteProject(Project project);
	
	public List<Map> addInputProject();
}
