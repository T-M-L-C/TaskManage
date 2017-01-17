package com.cissst.software.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.cissst.software.model.PageProject;
import com.cissst.software.model.Person;
import com.cissst.software.model.Project;
import com.cissst.software.session.HibernateSessionFactory;

public class ProjectMysqlDao {

	@Test
	public void testMethod01(){
		getProjectAll(1,1);
	}
	
	/**
	 * ��ҳ��ѯ��Ϣ
	 * @param currpage ��ǰҳ
	 * @param rows ��ʾ���ܼ�¼��
	 * @return
	 */
	public PageProject getProjectAll(int currpage,int rows){	
		
		PageProject pageproject = new PageProject();
		Session session = HibernateSessionFactory.getSession();
		String hql="select r.projectId,r.projectName,r.projectCode,r.status,r.beginTime,r.endTime,p.name "+
				"from Project r left  join r.person p";
		List<Object []> records = session.createQuery(hql)
				.setFirstResult((currpage-1)*10)
				.setMaxResults(rows)
				.list();
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		
		for(Object[] ob : records){	
			Map<String, Object>maps = new HashMap<String, Object>();
			maps.put("projectId",ob[0]);
			maps.put("projectName",ob[1]);
			maps.put("projectCode",ob[2]);
			maps.put("status",ob[3]);
			maps.put("beginTime",ob[4].toString());
			maps.put("endTime",ob[5].toString());
			maps.put("name",ob[6]);
			res.add(maps);
		}
		hql="select count(*) from Project";
		int totals = ((Long)session.createQuery(hql).uniqueResult()).intValue();
		
		pageproject.setDatas(res);
		pageproject.setTotal(totals);
		
		HibernateSessionFactory.closeSession();
		return pageproject;
	}
	
	/**
	 * �����Ŀ
	 * @param project
	 * @return
	 */
	public boolean addProject(Project project){
		boolean flag = false;
		Transaction t =null;
		try {
			Session session = HibernateSessionFactory.getSession();	
			t = session.beginTransaction();
			session.clear();
			session.save(project);
			t.commit();
			flag=true;
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		}finally{
		HibernateSessionFactory.closeSession();
		}
		return flag;	
	}
	
	@Test
	public void testMethod1(){
		getProjectAll(1,1);
		
		Project project = new Project();
		Person person = new Person();
		person.setPid(1);
		project.setProjectId(1);
		project.setProjectName("www");
		project.setPerson(person);
		
		updateProject(project);
	}
	
	/**
	 * �޸���Ŀ
	 * @param project
	 * @return
	 */
	public boolean updateProject(Project project){
		boolean flag = false;
		Transaction t =null;
		try {
			Session session = HibernateSessionFactory.getSession();	
			t = session.beginTransaction();
			session.clear();
			session.update(project);
			t.commit();
			//session.evict(project);ע��evict��������������ж��󣬶�clear�����������ж��󵫲��������ڲ����Ķ���
			flag=true;
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		}finally{
		HibernateSessionFactory.closeSession();
		}
		return flag;	
	}
	
	@Test
	public void testMethod03(){
		Project project = new Project();
		project.setProjectId(2);
		
		boolean flag = deleteProject(project);
		System.out.println(flag);
	}
	
	/**
	 * ɾ����Ŀ
	 * @param project
	 * @return
	 */
	public boolean deleteProject(Project project){
		boolean flag = false;
		Transaction t =null;
		try {
			Session session = HibernateSessionFactory.getSession();	
			t = session.beginTransaction();		
			session.clear();
			session.delete(project);
			t.commit();
			//session.evict(project);
			flag=true;
		} catch (HibernateException e) {
			t.rollback();
			e.printStackTrace();
		}finally{
		HibernateSessionFactory.closeSession();
		}
		return flag;	
	
	}
	/**
	 * ��ѯ�����Ŀ��Աʱ�ɼ���Ŀ��Ϣ
	 * @return projectid ����Ŀ��� ,projectname ����Ŀ���
	 */
	public List<Map> addInputProject(){
		List<Map> results = new ArrayList<Map>();
		//����MapΪ��
		Map<String, Object> map = null;
		Session session = HibernateSessionFactory.getSession();
		String hql="select p.projectId,p.projectName from Project p";
		List<Object[]> datas = session.createQuery(hql).list();
		for(Object[] obs:datas){
			//����newһ��Map�ռ�
			map = new HashMap<String, Object>();
			map.put("proid" , obs[0]);
			map.put("projectname", obs[1]);
			results.add(map);
		}
		HibernateSessionFactory.closeSession();
		return results;
	}
	
}
