package com.cissst.software.dao;

import org.hibernate.Session;
import org.junit.Test;

import com.cissst.software.session.HibernateSessionFactory;

public class LoginMysqlDao {

	@Test
	public void testMethod01(){
		boolean flag = loginUser("admin1","999","系统管理员");
		System.out.println("flag :"+ flag);
	}
	
	public boolean loginUser(String loginname,String loginpwd,String authority){
		boolean flag =false;
		Session session=HibernateSessionFactory.getSession();
		String hql="select count(*) from Person where  loginname=? and pwd=? and authority=?";
		int count=((Long)session.createQuery(hql)
				.setString(0, loginname)
                                .setString(1,loginpwd)
				.setString(2, authority)
				.uniqueResult()).intValue();
		if(count > 0)
                     flag = true;
		
	  //关闭对象；
	  HibernateSessionFactory.closeSession();
	  return flag;
	}

}
