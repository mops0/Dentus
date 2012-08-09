package com.dentus;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public Map<Long, Usluga> generujMape()
	{
		List<Usluga> lista =readUslugi();
		Map<Long, Usluga> mapa = new LinkedHashMap<Long, Usluga>();
		for (int i=0;i<lista.size();i++)
		{
			mapa.put(new Long(lista.get(i).getId()), lista.get(i));
		}
		return mapa;
		
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
		session.flush();
	}
	public Usluga findById(long id)
	{
		Session session =HibernateUtil.getSession();
		Usluga usluga = (Usluga) session.get(Usluga.class, id);
		return usluga;
		
	}
	
}


