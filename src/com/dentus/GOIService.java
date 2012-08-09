package com.dentus;

import java.io.IOException;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class GOIService
{
	GOIService()
	{
		HibernateUtil.beginTransaction();
	}
	public void addGOI(GOI goi) 
	{
		Session session =HibernateUtil.getSession();
		session.save(goi);		 
	}
	@SuppressWarnings("unchecked")
	public ArrayList<GOI> pobierzListeGOI() 
	 {
		Session session =HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(GOI.class);
		
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
	
	

}
