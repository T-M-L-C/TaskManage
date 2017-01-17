package com.cissst.software.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Project {

	private int projectId;//项目编号
	private String projectName;//项目名称
	private String projectCode;//项目编码
	private String status;//项目状态 立项 进行 结项
	private Date beginTime;//项目开始时间
	private Date endTime;//项目结束时间
	private Person person;//项目负责人
	
	private Set<PersonProject> projects = new HashSet<PersonProject>();
	
	private Set<Task>tasks=new HashSet<Task>();
	
	public Set<PersonProject> getProjects() {
		return projects;
	}
	public void setProjects(Set<PersonProject> projects) {
		this.projects = projects;
	}
	public Project() {
		
	}
	
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Project(int projectId, String projectName, String projectCode,
			String status, Date beginTime, Date endTime, Person person,
			Set<PersonProject> projects) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectCode = projectCode;
		this.status = status;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.person = person;
		this.projects = projects;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}
