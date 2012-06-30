package com.dentus;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class RecordService
{
	Session session;
	RecordService()
	{
		SessionFactory factory=HibernateUtil.getSessionFactory();
		this.session=factory.getCurrentSession();
	}
	public void dodajRekord(Pacjent pacjent) 
	{
		session.save(pacjent);		 
	}
	@SuppressWarnings("unchecked")
	public List<Pacjent> odczytajRekordy() 
	 {
		Criteria criteria = session.createCriteria(Pacjent.class);
		
		return (List<Pacjent>)criteria.list();
		 
	 }
	
	public void zastapRekord(Pacjent nowyP,Pacjent staryP) 
	{
		long stareId=staryP.getId();
		nowyP.setId(stareId);
		session.update(nowyP);
		
		
			
	}
	public void usunRekord(Pacjent pacjentDel) throws IOException
	{
		session.delete(pacjentDel);
	}
	public Pacjent findById(long id)
	{
		Pacjent pacjent = (Pacjent) session.get(Pacjent.class, id);
		return pacjent;
		
	}
	
}
