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

public class RecordServiceUsluga
{
	private String principalName;
	RecordServiceUsluga()
	{
		HibernateUtil.beginTransaction();
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

	public void addUsluga(Usluga usluga) 
	{
		Session session =HibernateUtil.getSession();
		usluga.setPrincipalName(principalName);
		session.save(usluga);		 
	}
	@SuppressWarnings("unchecked")
	public List<Usluga> readUslugi() 
	 {
		Session session =HibernateUtil.getSession();
		//Criteria criteria = session.createCriteria(Usluga.class);
		Criteria criteria =(session.createCriteria(Usluga.class).add(Restrictions.like("principalName",principalName)));
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
	public boolean isUsedbySchedule(Usluga usluga)
	{
		Session session =HibernateUtil.getSession();
		@SuppressWarnings("rawtypes")
		List list =(session.createCriteria(GOI.class).createCriteria("usluga").add(Restrictions.like("id",usluga.getId()))).list();
		
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


