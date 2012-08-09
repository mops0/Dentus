package com.dentus;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
public class HibernateUtil
{
	private static final SessionFactory sessionFactory;
	private static final ThreadLocal threadSession= new ThreadLocal();
	private static final ThreadLocal threadTransaction= new ThreadLocal();
	static{		
		Configuration configuration = new Configuration();
	    configuration.configure();
	   configuration.buildMappings();
	   ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        

	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    
		}
	
		public static Session getSession()
		{
			Session s =(Session) threadSession.get();
			
				if(s==null){
					s=sessionFactory.openSession();
					threadSession.set(s);
				}
				return s;
			
		}
		public static void closeSession()
		{
			Session s=(Session) threadSession.get();
			threadSession.set(null);
			if (s!=null && s.isOpen())
				s.close();
		}
		public static void beginTransaction()
		{
			Transaction tx =(Transaction) threadTransaction.get();
			if (tx==null)
			{
				tx=getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		}
		public static void commitTransaction()
		{
			Transaction tx =(Transaction) threadTransaction.get();
			try{
				if(tx!=null && !tx.wasCommitted() && !tx.wasRolledBack())
					tx.commit();
				threadTransaction.set(null);
			}catch (HibernateException ex) {
				rollbackTransaction();
			}
		}
		public static void rollbackTransaction()
		{
			Transaction tx =(Transaction) threadTransaction.get();
			threadTransaction.set(null);
			if(tx !=null && !tx.wasCommitted() && !tx.wasRolledBack())
				tx.rollback();
			closeSession();
		}
		
	
}
