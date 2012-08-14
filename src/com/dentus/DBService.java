package com.dentus;



import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@ManagedBean(name="dbService")
@SessionScoped
public class DBService
{
	public void activate()
	{
		
		
		/*
		Pojazd pojazd = new Pojazd();
		Pojazd pojazd2 = new Pojazd();
		Kolor kolor = new Kolor();
		Kolor kolor2 = new Kolor();
		Kolor kolor3 = new Kolor();
		Owner owner = new Owner();
		Owner owner2 = new Owner();
		Owner owner3 = new Owner();
		kolor.setNazwa("biały");
		kolor2.setNazwa("czerwony");
		kolor3.setNazwa("czarny");
		owner.setNazwisko("Klitkowski");
		owner2.setNazwisko("Bombalski");
		owner3.setNazwisko("Rymbaba");
		pojazd.setKlient(owner);
		pojazd.setKolor(kolor);
		pojazd.setMarka("Mercedes");
		
		pojazd2.setKlient(owner2);
		pojazd2.setKolor(kolor2);
		pojazd2.setMarka("Syrenka 105");
		
			Session session =HibernateUtil.getSession();
			session.save(kolor);
			session.save(kolor2);
			session.save(kolor3);
			session.save(owner);
			session.save(owner2);
			session.save(owner3);
			session.save(pojazd);
			session.save(pojazd2);
			session.flush();
			
			Session session2 =HibernateUtil.getSession();
			
			List list = (session2.createCriteria(Pojazd.class).createCriteria("klient")
				    .add( Restrictions.like("nazwisko", "Bombalski")).list());
			System.out.println(list.toString());
			*/
		Session session =HibernateUtil.getSession();
		List list =(session.createCriteria(GOI.class).createCriteria("pacjent").add(Restrictions.like("id",new Long(1)))).list();
		
		session.delete((GOI)list.get(0));
		session.flush();
		//System.out.println(((GOI)list.get(0)).getPacjent());
		
	}
}
