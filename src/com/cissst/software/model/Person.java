package com.cissst.software.model;

import java.util.HashSet;
import java.util.Set;


/**
 * 人员表
 * @author 王小鸽
 * @date 2016
 */
public class Person {

	private int pid;//编号
	private String loginName;//登录名
	private String name;//用户名称
	private String gender;//性别
	private int age;//年龄
	//0:普通用户  1：系统管理员
	private String authority;//权限
	private String pwd;//密码
	
	private Set<PersonProject> personProjects =new HashSet<PersonProject>();
		
	private Set<Project> projects=new HashSet<Project>();
	
	private Set<Task> tasks=new HashSet<Task>();
	
	public Set<PersonProject> getPersonProjects() {
		return personProjects;
	}
	public void setPersonProjects(Set<PersonProject> personProjects) {
		this.personProjects = personProjects;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	public Person() {
	}
	public Person( String loginName, String name, String gender,
			int age, String authority, String pwd) {
		this.loginName = loginName;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.authority = authority;
		this.pwd = pwd;
		
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	
	
}
