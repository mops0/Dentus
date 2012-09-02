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
		
		Session session =HibernateUtil.getSession();
		
		Pacjent pacjent = new Pacjent();
		pacjent.setNazwisko("Józek");
		pacjent.setId(1L);
		session.saveOrUpdate(pacjent);
		session.flush();
		/*
		Pacjent pacjent = (Pacjent) session.load(Pacjent.class, 1L);
		
		HistoriaWpis hwp1=new HistoriaWpis();
		hwp1.setKomentarz("PizdaNowa2");
		pacjent.dodajHistoriaWpis(hwp1);
		session.saveOrUpdate(pacjent);
		session.flush();
		*/
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
