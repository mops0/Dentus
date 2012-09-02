package com.dentus;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;



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
	public Map<Long, Pacjent> generujMape()
	{
		List<Pacjent> lista =odczytajRekordy();
		Map<Long, Pacjent> mapa = new LinkedHashMap<Long, Pacjent>();
		for (int i=0;i<lista.size();i++)
		{
			mapa.put(new Long(lista.get(i).getId()), lista.get(i));
		}
		return mapa;
		
	}
	public void updateRecord(Pacjent pacjent) 
	{
		
		Session session =HibernateUtil.getSession();
		
		
		session.update(pacjent);
		
		
		
		
			
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
	public boolean isUsedbySchedule(Pacjent pacjent)
	{
		Session session =HibernateUtil.getSession();
		@SuppressWarnings("rawtypes")
		List list =(session.createCriteria(GOI.class).createCriteria("pacjent").add(Restrictions.like("id",pacjent.getId()))).list();
		
		return !list.isEmpty();
	}
	
}
