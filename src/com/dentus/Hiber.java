package com.dentus;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Hiber
{
	
	private SessionFactory sessionFactory;
	
	public void start()
	{
		setUp();
		load();
	}
	public void setUp()
	{
		
		Configuration configuration = new Configuration();
	    configuration.configure();
	   configuration.buildMappings();
	   ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    //sessionFactory = configuration.buildSessionFactory(new ServiceRegistryBuilder().buildServiceRegistry()); //configuration.buildSessionFactory(serviceRegistry);
	    

	    
	    
	    //ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    
	  
	    
	    
	    
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    
	    Rekord rek= new Rekord("Alicja", "Szypułka-Hiber");
	    Historia hist=new Historia();
	    hist.setPraca("Szpital Wojskowy");
	    hist.setSzkola("Akademia medyczna");
	    List<Zdarzenie> zdarzenia= new ArrayList<Zdarzenie>();
	    Zdarzenie zd = new Zdarzenie();
	    zd.setData("20.02.2012");
	    zd.setZapis("PAcjent miał ekstrakcję zęba");
	    zdarzenia.add(zd);
	    Zdarzenie zd2 = new Zdarzenie();
	    zd2.setData("12.04.2011");
	    zd2.setZapis("Pacjent sperdzielił i sie nie zjawił");
	    zdarzenia.add(zd2);
	    rek.setHistoria(hist);
	    rek.setZdarzenia(zdarzenia);
	    
	    
	    Rekord rek2= new Rekord("Tomasz", "Szymczyszyn");
	    Historia hist2=new Historia();
	    hist2.setPraca("Google");
	    hist2.setSzkola("Politechnika Wrocławska");
	    List<Zdarzenie> zdarzenia2= new ArrayList<Zdarzenie>();
	    Zdarzenie zd3 = new Zdarzenie();
	    zd3.setData("20.02.2010");
	    zd3.setZapis("Nic sie nie stało!!!u Tomka");
	    zdarzenia2.add(zd3);
	    Zdarzenie zd4 = new Zdarzenie();
	    zd4.setData("01.05.2011");
	    zd4.setZapis("Teraz to działa!!!");
	    zdarzenia2.add(zd4);
	    rek2.setHistoria(hist2);
	    rek2.setZdarzenia(zdarzenia2);
	   
	    session.save(rek);
	   session.save(rek2);
	    session.getTransaction().commit();
	    
	    
	    session.close();
	    
	}
	public void load()
	{
		Rekord rek=new Rekord();
		int licznik=1;
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		while (true)
		{
			
			rek=(Rekord)session.get(Rekord.class,new Long(licznik));
			if(rek==null)
			break;
			else
			System.out.println("Wartość odczytana z bazy to: "+ rek.getImie()+" "+rek.getNazwisko()+ "Z historii: "+rek.getHistoria().getPraca()+" "+rek.getHistoria().getSzkola());
			System.out.println("Zdarzenia: "+rek.getZdarzenia().get(1).getZapis() );
			licznik++;
			
		}
		tx.commit();
		session.close();
	}

}
