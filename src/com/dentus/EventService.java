package com.dentus;

import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class EventService
{
	EventService()
	{
		HibernateUtil.beginTransaction();
	}
	public void addEvent(Event event) 
	{
		Session session =HibernateUtil.getSession();
		System.out.println("WYsyłam event do bazy"+event.getName());
		session.save(event);		 
	}
	@SuppressWarnings("unchecked")
	public List<Event> readEvents() 
	 {
		Session session =HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Event.class);
		
		return (List<Event>)criteria.list();
		 
	 }
	
	public void update(Event event) 
	{
		Session session =HibernateUtil.getSession();
		
		session.update(event);
		
		
			
	}
	public void deleteEvent(Event event) throws IOException
	{
		Session session =HibernateUtil.getSession();
		session.delete(event);
	}
	
	
}

