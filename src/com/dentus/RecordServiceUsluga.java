package com.dentus;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class RecordServiceUsluga
{
	RecordServiceUsluga()
	{
		HibernateUtil.beginTransaction();
	}
	public void addUsluga(Usluga usluga) 
	{
		Session session =HibernateUtil.getSession();
		
		session.save(usluga);		 
	}
	@SuppressWarnings("unchecked")
	public List<Usluga> readUslugi() 
	 {
		Session session =HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Usluga.class);
		
		return (List<Usluga>)criteria.list();
		 
	 }
	
	public void update(Usluga usluga) 
	{
		Session session =HibernateUtil.getSession();
		
		session.update(usluga);
		
		session.flush();
			
	}
	public void delete(Usluga usluga) throws IOException
	{
		Session session =HibernateUtil.getSession();
		session.delete(usluga);
	}
	
}


