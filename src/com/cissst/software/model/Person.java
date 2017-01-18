package com.cissst.software.model;

import java.util.HashSet;
import java.util.Set;


/**
 * ��Ա��
 * @author ��С��
 * @date 2016
 */
public class Person {

	private int pid;//���
	private String loginName;//��¼��
	private String name;//�û�����
	private String gender;//�Ա�
	private int age;//����
	//0:��ͨ�û�  1��ϵͳ����Ա
	private String authority;//Ȩ��
	private String pwd;//����
	
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
