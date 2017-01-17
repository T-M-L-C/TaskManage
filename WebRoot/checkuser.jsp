<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
	//防止未登陆的用户访问主页面
	Object object =session.getAttribute("username");
	if(object==null ){
		//重定向到登陆页面
		response.sendRedirect(request.getContextPath() +"/login.jsp");
	}
%>
