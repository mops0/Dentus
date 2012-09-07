package com.dentus;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import security.Authorities;

import security.PillUser2;

@ManagedBean(name="testowa")
@SessionScoped
public class Testowa
{
	
	public Testowa()
	{
		
	}
	public void init()
	{
		
		Session session=HibernateUtil.getSession();
		PillUser2 puser = new PillUser2();
		puser.setUsername("Tomek");
		puser.setPassword("wiosna12");
		Authorities auth= new Authorities();
		auth.setAuthority("POOOO");
		puser.addAuthority(auth);
		
		session.saveOrUpdate(puser);
		session.flush();
	
	}
	public void dodajWpis()
	{
		
	}
	public void usun()
	{
		
	}
}
