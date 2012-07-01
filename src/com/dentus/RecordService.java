package com.dentus;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class RecordService
{
	
	RecordService()
	{
		HibernateUtil.beginTransaction();
	}
	public void dodajRekord(Pacjent pacjent) 
	{
		Session session =HibernateUtil.getSession();
		session.save(pacjent);		 
	}
	@SuppressWarnings("unchecked")
	public List<Pacjent> odczytajRekordy() 
	 {
		Session session =HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Pacjent.class);
		
		return (List<Pacjent>)criteria.list();
		 
	 }
	
	public void zastapRekord(Pacjent nowyP,Pacjent staryP) 
	{
		Session session =HibernateUtil.getSession();
		long stareId=staryP.getId();
		nowyP.setId(stareId);
		session.update(nowyP);
		
		
			
	}
	public void usunRekord(Pacjent pacjentDel) throws IOException
	{
		Session session =HibernateUtil.getSession();
		session.delete(pacjentDel);
	}
	public Pacjent findById(long id)
	{
		Session session =HibernateUtil.getSession();
		Pacjent pacjent = (Pacjent) session.get(Pacjent.class, id);
		return pacjent;
		
	}
	
}
