package com.cissst.software.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.optix.common.app.BaseAction;

import org.apache.struts2.ServletActionContext;

import com.cissst.software.model.Person;
import com.cissst.software.service.ILoginService;
import com.cissst.software.serviceimpl.LoginServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction  implements ModelDriven<Person> {

	private Person person;
	private PrintWriter pw=null;
	private ILoginService ILoginService = new LoginServiceImpl();
	private Map request =(Map) ActionContext.getContext().get("request");
	private Map session=ActionContext.getContext().getSession();
	private Map application=ActionContext.getContext().getApplication();
	private HttpServletResponse response=ServletActionContext.getResponse();
	private HttpServletRequest req=ServletActionContext.getRequest();
    
	
	/**
	 *验证登录
	 * @return
	 * @throws IOException 
	 */
	public String validateUser() throws IOException{
		//Domain Model
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw=response.getWriter();
	    
	    System.out.println(person.getLoginName()+","+person.getPwd()+","+person.getAuthority());
		boolean flag = ILoginService.LoginUser(person.getLoginName(),person.getPwd(),person.getAuthority());
	    System.out.println(flag);
		if(flag){
			pw.print("登录成功");
			req.getSession().setAttribute("username", person.getLoginName());
		}else{
			pw.print("登录失败");
		}
		pw.flush();
		pw.close();
		return success;
	}
	
	


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public ILoginService getILoginService() {
		return ILoginService;
	}

	public void setILoginService(ILoginService iLoginService) {
		ILoginService = iLoginService;
	}

	public Person getModel() {
		if(person==null)
			person=new Person();
		return person;
	}

	

}
