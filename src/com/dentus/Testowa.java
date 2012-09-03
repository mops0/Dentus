package com.dentus;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
@ManagedBean(name="testowa")
@SessionScoped
public class Testowa
{
	
	public Testowa()
	{
		
	}
	public void init()
	{
		//HibernateUtil.beginTransaction();
		Session session =HibernateUtil.getSession();
		Pacjent pacjent3 = (Pacjent) session.load(Pacjent.class, 1L);
		HistoriaWpis hwp = new HistoriaWpis();
		hwp.setKomentarz("bla");
		pacjent3.dodajHistoriaWpis(hwp);
		session.saveOrUpdate(pacjent3);
		session.flush();
	}
	public void dodajWpis()
	{
		
	}
	public void usun()
	{
		Session session =HibernateUtil.getSession();
		Pacjent pacjent3 = (Pacjent) session.load(Pacjent.class, 1L);
		HistoriaWpis hwp=pacjent3.getHistoria().get(0);
		pacjent3.getHistoria().remove(hwp);
		session.saveOrUpdate(pacjent3);
		session.flush();
	}
}
