package com.cissst.software.serviceimpl;

import java.util.List;
import java.util.Map;

import com.cissst.software.dao.ProjectMysqlDao;
import com.cissst.software.model.PageProject;
import com.cissst.software.model.Project;
import com.cissst.software.service.IProjectService;

public class ProjectServiceImpl implements IProjectService {

	ProjectMysqlDao projectmysqldao = new ProjectMysqlDao();
	public PageProject allquery(int currpage, int rows) {	
		return projectmysqldao.getProjectAll(currpage, rows);
	}
	public boolean addProject(Project project) {
		
		return projectmysqldao.addProject(project);
	}
	public boolean updateProject(Project project) {
		return projectmysqldao.updateProject(project);
	}
	public boolean deleteProject(Project project) {
		return projectmysqldao.deleteProject(project);
	}

	public List<Map> addInputProject() {
		// TODO Auto-generated method stub
		return projectmysqldao.addInputProject();
	}

	
}
