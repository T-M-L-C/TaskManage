package com.cissst.software.model;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Task {
	private int taskid;//Id
	private Person person;//执行人
	private Project project;//所属项目
	private String taskname;//任务名称
	private String tasktype;//任务类型
	private String taskstatus;//任务状态
	private Date begintime;//任务预计开始时间
	private Date endtime;//任务预计结束时间
	private Date praticaltime;//任务实际结束时间
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	public String getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Date getPraticaltime() {
		return praticaltime;
	}
	public void setPraticaltime(Date praticaltime) {
		this.praticaltime = praticaltime;
	}
	
}
