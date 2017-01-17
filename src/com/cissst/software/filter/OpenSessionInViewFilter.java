package com.cissst.software.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cissst.software.session.HibernateSessionFactory;



/**
 * 保证在JSP页面中Session是打开的
 * 		不能在service方法中关闭session
 * */
public class OpenSessionInViewFilter implements Filter {
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		chain.doFilter(request, response);
		//关闭当前线程中的session
		HibernateSessionFactory.closeSession();
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
