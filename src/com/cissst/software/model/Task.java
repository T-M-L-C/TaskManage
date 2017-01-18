package com.cissst.software.model;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Task {
	private int taskid;//Id
	private Person person;//ִ����
	private Project project;//������Ŀ
	private String taskname;//��������
	private String tasktype;//��������
	private String taskstatus;//����״̬
	private Date begintime;//����Ԥ�ƿ�ʼʱ��
	private Date endtime;//����Ԥ�ƽ���ʱ��
	private Date praticaltime;//����ʵ�ʽ���ʱ��
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
