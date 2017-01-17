package com.cissst.software.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cissst.software.model.PageTask;
import com.cissst.software.model.Task;
import com.cissst.software.session.HibernateSessionFactory;

public class TaskMySqlDao {
	public boolean updateTask(Task task) {
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=null;
		boolean  flag=false;
		try {
			transaction=session.beginTransaction();
			session.clear();
			session.update(task);
			transaction.commit();
			flag=true;
		} catch (Exception e) {
		    transaction.rollback();
		    e.printStackTrace();
		}finally
		{
			HibernateSessionFactory.closeSession();
		}
		return flag;
	}

	public boolean addTask(Task task) {
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=null;
		boolean flag=false;
		try {
			transaction=session.beginTransaction();
			session.clear();
			session.save(task);
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

	public boolean deleteTask(Task task) {
		Session session=HibernateSessionFactory.getSession();
		Transaction transaction=null;
		boolean flag=false;
		try {
			transaction=session.beginTransaction();
			session.clear();
			session.delete(task);
			transaction.commit();
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		    transaction.rollback();
		}finally{
			HibernateSessionFactory.closeSession();
		}
		return flag;
	}

	public PageTask allquery(int currpage, int rows, int  proid) {
		
		PageTask pageTask=new PageTask();
		Session session=HibernateSessionFactory.getSession();
		String hql="select o.taskid,o.tasktype,o.taskname,o.taskstatus,o.begintime,o.endtime,o.praticaltime,r.projectName,p.name" +
				"  from Task o left join o.person p" +
				" left join o.project r where r.projectId="+proid+"";
		List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
		List<Object[]> records=session.createQuery(hql).setFirstResult((currpage-1)*10).setMaxResults(rows).list();
		for(Object []ob:records){
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("taskid", ob[0]);
			m.put("tasktype",ob[1]);
			m.put("taskname", ob[2]);
			m.put("taskstatus", ob[3]);
			m.put("begintime", ob[4].toString());
			m.put("endtime", ob[5].toString());
			m.put("praticaltime", ob[6].toString());
			m.put("projectName", ob[7]);
			m.put("name", ob[8]);
			maps.add(m);
		}
		
		hql="select count(*) from Task";
		rows=Integer.parseInt(session.createQuery(hql).uniqueResult().toString());
	    pageTask.setTasks(maps);
	    pageTask.setTotal(rows);
	    HibernateSessionFactory.closeSession();
		return pageTask;
	}
	
	public PageTask allquery(int currpage,int rows){
		PageTask pageTask=new PageTask();
		Session session=HibernateSessionFactory.getSession();
		String hql="select o.taskid,o.tasktype,o.taskname,o.taskstatus,o.begintime,o.endtime,o.praticaltime,r.projectName,p.name" +
				"   from Task o left join o.person p" +
				" left join o.project r ";
		List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
		List<Object[]> records=session.createQuery(hql).setFirstResult((currpage-1)*10).setMaxResults(rows).list();
		for(Object []ob:records){
			Map<String, Object>m=new HashMap<String,Object>();
			m.put("taskid", ob[0]);
			m.put("tasktype",ob[1]);
			m.put("taskname", ob[2]);
			m.put("taskstatus", ob[3]);
	    	m.put("begintime", ob[4].toString());
			m.put("endtime", ob[5].toString());
			m.put("praticaltime", ob[6].toString());
			m.put("projectName", ob[7]);
			m.put("name", ob[8]);
		    maps.add(m);
		}
		hql="select count(*) from Task";
		rows=Integer.parseInt(session.createQuery(hql).uniqueResult().toString());
	    pageTask.setTasks(maps);
	    pageTask.setTotal(rows);
	    HibernateSessionFactory.closeSession();
		return pageTask;
	}
	
	public Task getTaskById(int taskid){
		String hql="from Task where taskid='"+taskid+"'";
		Session session=HibernateSessionFactory.getSession();
		List<Task> list= session.createQuery(hql).list();
		HibernateSessionFactory.closeSession();
	   return list.get(0);
	}
 }
