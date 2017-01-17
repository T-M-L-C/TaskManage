package com.cissst.software.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.optix.common.app.BaseAction;

import org.apache.struts2.ServletActionContext;

import com.cissst.software.model.PageProject;
import com.cissst.software.model.Project;
import com.cissst.software.service.IPersonService;
import com.cissst.software.service.IProjectService;
import com.cissst.software.serviceimpl.PersonServiceImpl;
import com.cissst.software.serviceimpl.ProjectServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ModelDriven;

public class ProjectAction extends BaseAction implements ModelDriven<Project> {

	private Project project;
	private String page;
	private String rows;
    private HttpServletResponse response=response=ServletActionContext.getResponse();
	IProjectService ProjectService = new ProjectServiceImpl();
	IPersonService PersonService = new PersonServiceImpl();

	/**
	 *项目
	 * @throws IOException
	 * @date 2015-9-14
	 */
	public void displayProject() throws IOException {
		

		PageProject pageproject = ProjectService.allquery(
				Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> maps = new HashMap<String, Object>();
	    
		maps.put("rows", pageproject.getDatas());

		maps.put("total", pageproject.getTotal());

		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper=new ObjectMapper();
	    objectMapper.writeValue(out, maps);
		out.flush();
		out.close();
	}
	
	/**
	 * 查询人员
	 * @throws IOException 
	 */
	public void addInputPerson() throws IOException{
		List<Map> lists = PersonService.addInputPerson();

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String data = new GsonBuilder().create().toJson(lists);
		out.println(data);
		out.flush();
		out.close();
	}
	/**
	 * 
	 * @throws IOException 
	 */
	public void addInputProject() throws IOException{
		List<Map> lists=ProjectService.addInputProject();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String data=new GsonBuilder().create().toJson(lists);
		out.println(data);
		out.flush();
		out.close();
	}
	/**
	 * 添加项目
	 * @param 
	 * @throws IOException 
	 * @date 2015-9-14
	 */
	public void addProject() throws IOException{
		boolean flag =true;	
		System.out.println(flag);
		flag = ProjectService.addProject(project);	
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
	
	/**
	 * 更新项目
	 * @throws IOException 
	 */
	public void updateProject() throws IOException{
		boolean flag =true;				
		flag = ProjectService.updateProject(project);	
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
	
	public void deleteProject() throws IOException{
		boolean flag = false;
		System.out.println(flag);
		flag = ProjectService.deleteProject(project);
		
		PrintWriter out =response.getWriter();
		if(flag){
			out.print("{\"success\":\"true\"}");			
		}else{
			out.print("{\"success\":\"false\"}");
		}
		out.flush();
		out.close();
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Project getModel() {
		if (project == null) {
			project = new Project();
		}
		return project;
	}

}
