package com.dentus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GOIService
{
	private String principalName;
	public String getPrincipalName()
	{
		return principalName;
	}
	public void setPrincipalName(String principalName)
	{
		this.principalName = principalName;
	}
	GOIService()
	{
		HibernateUtil.beginTransaction();
		initializePrincipalName();
	}
	public void addGOI(GOI goi) 
	{
		Session session =HibernateUtil.getSession();
		goi.setPrincipalName(principalName);
		session.save(goi);		 
	}
	@SuppressWarnings("unchecked")
	public ArrayList<GOI> pobierzListeGOI() 
	 {
		Session session =HibernateUtil.getSession();
		//Criteria criteria = session.createCriteria(GOI.class);
		Criteria criteria=(session.createCriteria(GOI.class).add(Restrictions.like("principalName",principalName)));
		return (ArrayList<GOI>)criteria.list();
		 
	 }
	
	public void update(GOI goi) 
	{
		Session session =HibernateUtil.getSession();
		System.out.println("goi.id do aktualizacji: "+ goi.getId());
		System.out.println("goi.usluga.nazwa do aktualizacji: "+ goi.getUsluga().getNazwa());
		
		session.merge(goi);
		
		session.flush();
			
	}
	public void deleteGOI(GOI goi) throws IOException
	{
		Session session =HibernateUtil.getSession();
		session.delete(goi);
		session.flush();
	}
	public List<GOI> getListofSpecificGOI(Pacjent pacjent)
	{
		Session session =HibernateUtil.getSession();
		@SuppressWarnings("unchecked")
		List<GOI> list =(session.createCriteria(GOI.class).createCriteria("pacjent").add(Restrictions.like("id",pacjent.getId()))).list();
		
		return list;
	}
	public GOI findById(long id)
	{
		Session session =HibernateUtil.getSession();
		GOI goi = (GOI) session.get(GOI.class, id);
		return goi;
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
