package com.dentus;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



public class RecordService
{
	private String principalName;
	RecordService()
	{
		initializePrincipalName();
	}
	
	public String getPrincipalName()
	{
		return principalName;
	}

	public void setPrincipalName(String principalName)
	{
		this.principalName = principalName;
	}

	public void dodajRekord(Pacjent pacjent) 
	{
		pacjent.setPrincipalName(principalName);
		Session session =HibernateUtil.getSession();
		session.save(pacjent);	
		session.flush();
	}
	@SuppressWarnings("unchecked")
	/*
	public List<Pacjent> odczytajRekordy() 
	 {
		Session session =HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Pacjent.class);
	
		return (List<Pacjent>)criteria.list();
		
	 }
	 */
	public List<Pacjent> odczytajRekordy()
	{
		Session session =HibernateUtil.getSession();
		@SuppressWarnings("unchecked")
		List<Pacjent> list =(session.createCriteria(Pacjent.class).add(Restrictions.like("principalName",principalName))).list();
		
		return list;
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
	
		session.saveOrUpdate(pacjent);
		session.flush();
		
		
		
			
	}
	public void usunRekord(Pacjent pacjentDel) throws IOException
	{
		Session session =HibernateUtil.getSession();
		session.delete(pacjentDel);
		session.flush();
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
	public void initializePrincipalName()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		  principalName=((UserDetails)principal).getUsername();
		} else {
		 principalName=principal.toString();
		}
		
	}
}
