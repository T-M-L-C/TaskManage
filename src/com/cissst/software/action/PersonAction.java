package com.cissst.software.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.optix.common.app.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.codehaus.jackson.map.ObjectMapper;

import com.cissst.software.model.PagePerson;
import com.cissst.software.model.Person;
import com.cissst.software.service.IPersonService;
import com.cissst.software.serviceimpl.PersonServiceImpl;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 员工
 * @author cissst
 * @date 2015-8-29
 */
public class PersonAction extends BaseAction implements ModelDriven<Person>,RequestAware,ApplicationAware{

	private IPersonService personService = new PersonServiceImpl();
	private String page;
	private String rows;
	private String select;
	private Person person;
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	private HttpServletResponse response=ServletActionContext.getResponse();

	/**
	 * 添加人员信息
	 * @throws IOException 
	 */
	public String addPerson() throws IOException{
		PrintWriter out =response.getWriter();
		List<Person> list=personService.queryInstance(person.getLoginName());
		if(list.size()>0){
			out.print("{\"success\":\"again\"}");
		}else {
			person=new Person(person.getLoginName(),person.getName(),person.getGender(),person.getAge(),person.getAuthority(),person.getPwd());

			boolean flag = false;

			flag=personService.addPerson(person);

			System.out.println(flag);
			if(flag){
				out.print("{\"success\":\"true\"}");			
			}else{
				out.print("{\"success\":\"false\"}");
			}

		}
		out.flush();
		out.close();
		return success;
	}

	/**
	 * 更新人员信息
	 * @throws IOException
	 */
	public void updatePerson() throws IOException{

		int pid =this.person.getPid();
		String name = this.person.getName();
		String loginName = this.person.getLoginName();
		String pwd = this.person.getPwd();
		String gender = this.person.getGender();
		int age = this.person.getAge();
		String authority = this.person.getAuthority();


		Person person = new Person();
		person.setPid(pid);
		person.setName(name);
		person.setLoginName(loginName);
		person.setPwd(pwd);
		person.setGender(gender);
		person.setAge(age);
		person.setAuthority(authority);
        System.out.println(person.getName());
		boolean flag =personService.updatePerson(person);
		PrintWriter out =response.getWriter();
		if(flag){
			out.print("{\"success\":\"true\"}");			
		}else{
			out.print("{\"success\":\"false\"}");
		}
		out.flush();
		out.close();

	}

	/**
	 * 删除人员信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void  deletePerson() throws IOException{
		/*System.out.println("----------------------------------");*/
		Person person = new Person();
		person.setPid(this.person.getPid());
		boolean flag = personService.deletePerson(person);
		PrintWriter out =response.getWriter();
		if(flag){
			out.print("{\"success\":\"true\"}");			
		}else{
			out.print("{\"success\":\"false\"}");
		}
		out.flush();
		out.close();

	}

	/**
	 *人员展示
	 * @throws IOException 
	 */
	public void displayPerson() throws IOException{

		List<Person> persons=null;
		int total=0;
		if(select!=null&&select.trim().length()>0){
			PagePerson pageperson  = personService.allquery(Integer.parseInt(page),Integer.parseInt(rows),select);
			persons = pageperson.getPersons();
			total = pageperson.getTotal();
		}else{
			PagePerson pageperson = personService.allquery(Integer.parseInt(page),Integer.parseInt(rows));
			persons = pageperson.getPersons();
			total = pageperson.getTotal();
		}
		Map<String, Object> maps = new HashMap<String, Object>();
		List<Map<String,Object>> res=new ArrayList<Map<String, Object>>();
		
		for(Person person:persons){
			Map<String, Object> m=new HashMap<String,Object>();
			m.put("pid", person.getPid());
			m.put("name", person.getName());
			m.put("loginName", person.getLoginName());
			m.put("pwd", person.getPwd());
			m.put("gender", person.getGender());
			m.put("age", person.getAge());
			m.put("authority", person.getAuthority());
			res.add(m);
		}
		maps.put("rows", res);
		// ��ʾ��ݵ��ܼ�¼��
		maps.put("total", total);
		// �����ת��Ϊjson��ʽ,�������ҳ��ǰ��
		response.setCharacterEncoding("utf-8");
		// ��ȡ���������
		//String data = new GsonBuilder().create().toJson(maps);
		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out, maps);
		for(Map.Entry<String, Object> entry:maps.entrySet()){
			System.out.println("key--->"+entry.getKey()+","+"Value-->"+entry.getValue());
		}
		out.flush();
		out.close();

	}


	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}



	
	public void setApplication(Map<String, Object> application) {
		this.application=application;

	}

	public void setRequest(Map<String, Object> request) {
		this.request=request;

	}

	public void setSession(Map<String, Object> session){
		this.session=session;
	}

	
	public Person getModel() {
		if(person==null)
			person=new Person();
		return person;
	}


}
