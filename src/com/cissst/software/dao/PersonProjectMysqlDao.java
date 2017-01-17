package com.cissst.software.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.cissst.software.model.PagePersonProject;
import com.cissst.software.model.PersonProject;
import com.cissst.software.session.HibernateSessionFactory;

public class PersonProjectMysqlDao {
	
	@Test
	public void testMysql(){
		Session session = HibernateSessionFactory.getSession();
		HibernateSessionFactory.closeSession();
	}

	/**
	 * 查询项目成员信息
	 * @param currpage
	 * @param rows
	 * @return
	 */
	public PagePersonProject displayPersonProject(int currpage,int rows){
		PagePersonProject pagepersonproject = new PagePersonProject();		
		Session session = HibernateSessionFactory.getSession();
		String hql="select o.id  ,o.remark, r.projectId ,r.projectName,"
				+ " p.pid ,p.name  "
				+ "from PersonProject o left join o.project r "
				+ " left join o.person p";
		List<Object[]> records = session.createQuery(hql)
				.setFirstResult((currpage-1)*10)
				.setMaxResults(rows)
				.list();
		
        List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		for(Object[] ob : records){	
			Map<String, Object>maps = new HashMap<String,Object>();
			maps.put("ppid",ob[0]);
			maps.put("remark",ob[1]);
			maps.put("projectid",ob[2]);
			maps.put("projectName",ob[3]);
			maps.put("pid",ob[4]);
			maps.put("name",ob[5]);
		    res.add(maps);
		}
		//显示的总记录数
		hql="select count(*) from PersonProject";
		int totals = ((Long)session.createQuery(hql).uniqueResult()).intValue();
		
		pagepersonproject.setPersonprojects(res);
		
		pagepersonproject.setTotal(totals);
		
		HibernateSessionFactory.closeSession();
		
		return pagepersonproject;
	}
	/**
	 * 添加一个项目组成员
	 * @param personProject
	 * @return
	 */
	public boolean addPersonProject(PersonProject personProject){
		boolean flag=false;
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=null;
		try {
			transaction=session.beginTransaction();
			session.clear();
			session.save(personProject);
			transaction.commit();
			flag=true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return flag;
	}
	/**
	 * 删除一个项目组成员
	 * @param personProject
	 * @return
	 */
	public boolean deletePersonProject(PersonProject personProject){
		boolean flag=false;
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=null;
		try {
			transaction=session.beginTransaction();
			session.clear();
			session.delete(personProject);
			transaction.commit();
			flag=true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally
		{
			HibernateSessionFactory.closeSession();
		}
		return flag;
		
	}
	/**
	 * 删除一个项目组成员
	 * @param personProject
	 * @return
	 */
	public boolean updatePersonProject(PersonProject personProject){
		boolean flag=false;
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=null;
		try {
			transaction=session.beginTransaction();
			session.clear();
			session.update(personProject);
			transaction.commit();
			flag=true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally{
			HibernateSessionFactory.closeSession();
		}
		return flag;
	}
}
