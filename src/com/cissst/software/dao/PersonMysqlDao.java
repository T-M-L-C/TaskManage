package com.cissst.software.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.cissst.software.model.PagePerson;
import com.cissst.software.model.Person;
import com.cissst.software.session.HibernateSessionFactory;


public class PersonMysqlDao {

	
	@Test
	public void testMethod01(){
		getPersonAll(1,2);
	}
	
	/**
	 * 查询人员信息
	 * @param currpage当前页号
	 * @param rows要显示的行数
	 * @return
	 */
	public PagePerson getPersonAll(int currpage,int rows){
		Session session = HibernateSessionFactory.getSession();
		String hql ="from Person";
		List<Person> persons = session.createQuery(hql)
				.setFirstResult((currpage-1)*10)
				.setMaxResults(rows)
				.list();
		
		hql = "select count(*) from Person";
		rows = Integer.parseInt(session.createQuery(hql).uniqueResult().toString());
		PagePerson pageperson = new PagePerson();
		pageperson.setPersons(persons);
		pageperson.setTotal(rows);
		HibernateSessionFactory.closeSession();
		return pageperson;
	
	}
	
	/**
	 * 按条件查询
	 * @param currpage
	 * @param rows
	 * @param name
	 * @return
	 */
	public PagePerson getPersonAll(int currpage, int rows,String name){
		Session session = HibernateSessionFactory.getSession();
		String hql ="from Person p where p.name='"+name+"'";
		List<Person> persons = session.createQuery(hql)
				.setFirstResult((currpage-1)*10)
				.setMaxResults(rows)
				.list();
		
		hql = "select count(*) from Person";
		rows = Integer.parseInt(session.createQuery(hql).uniqueResult().toString());
		PagePerson pageperson = new PagePerson();
		pageperson.setPersons(persons);
		pageperson.setTotal(rows);
		HibernateSessionFactory.closeSession();
		return pageperson;
	}
	
	
	@Test
	public void testMethod02(){
		List<Map> ls = addInputPerson();
		for(Map ma: ls){
			//ma.keySet() 是讲每个ma中的key取出来
			Set set = ma.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()){
				String key = it.next().toString();
				System.out.println(key+":"+ma.get(key));
			}
		}	
		
	}
	
	
	/**
	 * 查询添加项目时的可加人员信息
	 * @return pid ：人员的编号 ,name ：人员的姓名 
	 */
	public List<Map> addInputPerson(){
		List<Map> results = new ArrayList<Map>();
		//设置Map为空
		Map map = null;
		Session session = HibernateSessionFactory.getSession();
		String hql="select pid ,name from Person";
		List<Object[]> datas = session.createQuery(hql).list();
		for(Object[] obs:datas){
			//这里new一新Map空间
			map = new HashMap();
			map.put("pid" , obs[0]);
			map.put("name", obs[1]);
			results.add(map);
		}
		HibernateSessionFactory.closeSession();
		return results;
	}
	
	/**
	 * 增加人员信息
	 * @param person
	 * @return
	 */
	public boolean addPerson(Person person){
		boolean flag = false;
		Transaction t =null;
		try {
			Session session = HibernateSessionFactory.getSession();	
			t = session.beginTransaction();
			session.save(person);
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
	
	/**
	 * 修改人员信息
	 * @param person
	 * @return
	 */
	public boolean updatePerson(Person person){
		boolean flag = false;
		Transaction t =null;
		try {
			Session session = HibernateSessionFactory.getSession();	
			t = session.beginTransaction();
			//person=(Person) session.load(Person.class, person.getPid());
			System.out.println("person的名字"+person.getName());
			session.clear();
			session.update(person);
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
	
	/** 
	 * 删除人员信息
	 * @param person
	 * @return
	 */
	public boolean deletePerson(Person person){
		boolean flag = false;
		Transaction t =null;
		try {
			Session session = HibernateSessionFactory.getSession();	
			t = session.beginTransaction();
			session.clear();
			session.delete(person);
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
	
	/**
	 * 按条件查询
	 */
	public List<Person> queryPerson(String loginName){
		Session session=HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		List<Person> list = c.add(Restrictions.eq("loginName", loginName)).list();
		return list;
	}
	
	/**
	 * 查询所有的人
	 * */
	@Test
	public void query1() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		
		//分页
		c.setFirstResult(0);
		c.setMaxResults(5);
		
		//排序
		c.addOrder(Order.desc("id"));
		
		List<Person> list = c.list();
		for(Person p : list) {
			//System.out.println(p.getName() + ", " + p.getId());
		}
		
		session.close();
	}
	
	/**
	 * 按条件查询
	 * */
	@Test
	public void query2() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		List<Person> list = c.add(Restrictions.gt("age", 20)).list();
		for(Person p : list) {
			System.out.println(p.getName());
		}
		
		session.close();
	}
	
	/**
	 * 按条件查询
	 * */
	@Test
	public void query3() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		List<Person> list = c.add(Restrictions.ge("age", 20))
							.add(Restrictions.like("name", "李%", MatchMode.EXACT))
							.list();
		for(Person p : list) {
			System.out.println(p.getName());
		}
		
		session.close();
	}
	
	/**
	 * 按条件查询
	 * */
	@Test
	public void query4() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		List<Person> list = c.add(Restrictions.or(Restrictions.ge("age", 20), Restrictions.like("name", "张%")))
							.list();
		for(Person p : list) {
			System.out.println(p.getName());
		}
		
		session.close();
	}
	
	/**
	 * 关联查询：
	 * 	不能直接使用这种方式：Restrictions.eq("card.sno", "10000")
	 * */
	@Test
	public void query5() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		c.add(Restrictions.ge("age", 20));
		
		Criteria c2 = c.createCriteria("card");
		c2.add(Restrictions.eq("sno", "10000"));
		
		Criteria c3 = c.createCriteria("houses");
		c3.add(Restrictions.eq("sno", "0001"));
		
		//查询身份证号为10000的用户
		Person p = (Person)c3.uniqueResult();
		
		System.out.println(p.getName());
		
		session.close();
	}
	
	@Test
	public void query6() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Person where age>=20 and card.sno=10000 and houses.sno=0001";
		//查询身份证号为10000的用户
		Person p = (Person)session.createQuery(hql).uniqueResult();
		
		System.out.println(p.getName());
		
		session.close();
	}
	
	/**
	 * 查询结果，一般为Object, 或集合
	 * 可以将结果集变成Map对象
	 * */
	@Test
	public void query7() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		c.add(Restrictions.ge("age", 20));
		
		Criteria c2 = c.createCriteria("card");
		c2.add(Restrictions.eq("sno", "10000"));
		
		Criteria c3 = c.createCriteria("houses");
		c3.add(Restrictions.eq("sno", "0001"));
		
		
		//查询身份证号为10000的用户
		c.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List list = c.list();
		System.out.println(list);
		
		session.close();
	}
	
	/**
	 * 投影、聚合、分组
	 * */
	@Test
	public void query8() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		//获得总纪录数
		c.setProjection(Projections.rowCount());
		
		Integer cnt = (Integer)c.uniqueResult();
		System.out.println(cnt);
		
		session.close();
	}
	
	/**
	 * 投影、聚合、分组
	 * */
	@Test
	public void query9() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		//获得总纪录数
		c.setProjection(Projections.projectionList()
				.add(Projections.rowCount())
				.add(Projections.avg("age"))
				.add(Projections.max("age"))
				.add(Projections.min("age"))
				.add(Projections.sum("age")));
		
		Object[] arr = (Object[])c.uniqueResult();
		System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + ", " + arr[4]);
		
		session.close();
	}
	
	/**
	 * 投影、聚合、分组
	 * */
	@Test
	public void query10() {
		Session session = HibernateSessionFactory.getSession();
		Criteria c = session.createCriteria(Person.class);
		//获得总纪录数
		c.setProjection(Projections.projectionList()
				.add(Projections.rowCount())
				.add(Projections.avg("age"))
				.add(Projections.max("age"))
				.add(Projections.min("age"))
				.add(Projections.sum("age"))
				.add(Projections.groupProperty("age")));
		
		List<Object[]> list = (List<Object[]>)c.list();
		for(Object[] arr : list) {
			System.out.println(Arrays.toString(arr));
		}
		
		session.close();
	}
	
}
