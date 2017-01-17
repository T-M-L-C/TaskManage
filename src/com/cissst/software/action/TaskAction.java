package com.cissst.software.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.optix.common.app.BaseAction;

import org.apache.struts2.ServletActionContext;

import com.cissst.software.model.PageTask;
import com.cissst.software.model.Person;
import com.cissst.software.model.Project;
import com.cissst.software.model.Task;
import com.cissst.software.service.ITaskService;
import com.cissst.software.serviceimpl.TaskServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ModelDriven;

public class TaskAction  extends BaseAction implements ModelDriven<Task>{
    private Task task=null;
    private HttpServletResponse response=ServletActionContext.getResponse();
    private ITaskService taskService=new TaskServiceImpl();
    private HttpServletRequest request=ServletActionContext.getRequest();
    private String array;
    private Person person=new Person();
    private Project project=new Project();
    private String proid;
    /**
     * 添加任务
     * @throws ParseException 
     * @throws IOException 
     */
    public void addTask() throws ParseException, IOException{
    	List<String> list=new ArrayList<String>();
    	array=array.substring(1,array.length()-1);
    	String []arr=array.split(",");
    	for(String item :arr){
    		item=item.replace("\"", "");
    		list.add(item);
    	}
    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
    	task.setTaskname(list.get(0));
    	task.setTasktype(list.get(1));
    	task.setTaskstatus(list.get(2));
    	task.setBegintime(sdf.parse(list.get(3)));
    	task.setEndtime(sdf.parse(list.get(4)));
    	task.setPraticaltime(sdf.parse(list.get(5)));
    	person.setPid(Integer.valueOf(list.get(6)));
    	project.setProjectId(Integer.parseInt(list.get(7)));
    	task.setPerson(person);
    	task.setProject(project);
    	 boolean flag=taskService.addTask(task);
    	 PrintWriter pw=response.getWriter();
    	 if(flag==true){
    		 pw.write("{\"result\":\"success\"}");
    	 }else{
    		 pw.write("{\"result\":\"error\"}");
    	 }
    }
    /**
     * 任务展示
     * @throws IOException 
     */
    public void displayTask() throws IOException{
    	response.setCharacterEncoding("UTF-8");
		int currpage=Integer.parseInt(request.getParameter("page"));
		if(currpage<=0)
			currpage=1;
	  int rows=Integer.parseInt(request.getParameter("rows"));
	  PageTask pageTask=null;
	  if(proid!=null && proid.trim().length()>0){
		     pageTask=taskService.allquery(currpage,rows,Integer.parseInt(proid));
	  }else 
      pageTask=taskService.allquery(currpage,rows);
      Map<String, Object> maps=new HashMap<String, Object>();
      maps.put("rows", pageTask.getTasks());
      maps.put("total",pageTask.getTotal());
      ObjectMapper objectMapper=new ObjectMapper();
      PrintWriter out=response.getWriter();
      objectMapper.writeValue(out, maps);
      out.flush();
      out.close();
    }
    /**
     * 更新任务״̬
     * @throws IOException 
     */
    public void updateTask() throws IOException{
    	task=taskService.getTaskById(Integer.parseInt(request.getParameter("taskid")));
    	//task.setTaskid(Integer.parseInt(request.getParameter("taskid")));
    	task.setTaskname(request.getParameter("taskname"));
    	task.setTaskstatus(request.getParameter("taskstatus"));
    	boolean flag=taskService.updateTask(task);
    	if(flag==true)
    		response.getWriter().write("{\"result\":\"success\"}");
    	else
    		response.getWriter().write("{\"result\":\"error\"}");
    } 
    /**
     * 删除任务
     * @throws IOException 
     */
    public void deleteTask() throws IOException{
    	task.setTaskid(Integer.parseInt(request.getParameter("taskid")));
    	boolean flag=taskService.deleteTask(task);
    	if(flag==true)
    		response.getWriter().println("{\"result\":\"success\"}");
    	else
    		response.getWriter().println("{\"result\":\"error\"}");
    }
	public Task getModel() {
		if(task==null)
			task=new Task();
		return task;
	}
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}
	public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}

    
}
