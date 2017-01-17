package com.cissst.software.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.optix.common.app.BaseAction;

import com.cissst.software.model.PagePersonProject;
import com.cissst.software.model.Person;
import com.cissst.software.model.PersonProject;
import com.cissst.software.model.Project;
import com.cissst.software.service.IPersonProjectService;
import com.cissst.software.serviceimpl.PersonProjectServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ModelDriven;

public class PersonProjectAction extends BaseAction implements
		ModelDriven<PersonProject> {
	private PersonProject personProject=null;
	private Person person=new Person();
	private Project project=new Project();
	private int proid,pid,ppid;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private static IPersonProjectService ppPersonProjectService=new PersonProjectServiceImpl(); 
	private HttpServletResponse response=ServletActionContext.getResponse();
	public void displaypersonProject() throws IOException{
		int currpage=Integer.parseInt(request.getParameter("page"));
		int rows=Integer.parseInt(request.getParameter("rows"));
		 PagePersonProject ppPersonProject=ppPersonProjectService.displayPersonProject(currpage, rows);
	        Map<String, Object> maps=new HashMap<String,Object>();
	 		maps.put("rows", ppPersonProject.getPersonprojects());
	 		maps.put("total", ppPersonProject.getTotal());
	 		response.setCharacterEncoding("UTF-8");
	 		PrintWriter out=response.getWriter();
	 		ObjectMapper objectMapper=new ObjectMapper();
	 		objectMapper.writeValue(out, maps);
	 		out.flush();
	 		out.close();
	}
	
    public void updatePersonProject() throws IOException{
    	boolean flag =true;	
    	personProject.setId(ppid);
    	person.setPid(pid);
    	project.setProjectId(proid);
    	personProject.setPerson(person);
    	personProject.setProject(project);
		flag = ppPersonProjectService.updatePersonProject(personProject);	
		//System.out.println("!>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+flag);
		PrintWriter out =response.getWriter();
		if(flag){
			out.print("{\"success\":\"true\"}");			
		}else{
			out.print("{\"success\":\"false\"}");
		}
		out.flush();
		out.close();
    }
    
    public void addPersonProject() throws IOException{
    	boolean flag =true;
    	person.setPid(pid);
    	project.setProjectId(proid);
    	personProject.setPerson(person);
    	personProject.setProject(project);
		flag = ppPersonProjectService.addPersonProject(personProject);	
		PrintWriter out =response.getWriter();
		System.out.println(flag);
		if(flag){
			out.print("{\"success\":\"true\"}");			
		}else{
			out.print("{\"success\":\"false\"}");
		}
		out.flush();
		out.close();
    }
    public void deletePersonProject() throws IOException{
    	boolean flag = false;
        personProject.setId(ppid);
		flag = ppPersonProjectService.deletePersonProject(personProject);
		
		PrintWriter out =response.getWriter();
		if(flag){
			out.print("{\"success\":\"true\"}");			
		}else{
			out.print("{\"success\":\"false\"}");
		}
		out.flush();
		out.close();
    }
	public PersonProject getModel() {
		if(personProject==null)
			personProject=new PersonProject();
		return personProject;
	}

	public int getProid() {
		return proid;
	}

	public void setProid(int proid) {
		this.proid = proid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPpid() {
		return ppid;
	}

	public void setPpid(int ppid) {
		this.ppid = ppid;
	}

}
