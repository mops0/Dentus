package com.dentus;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateSessionFilter implements Filter
{
	private SessionFactory factory=null;
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		Session session=factory.getCurrentSession();
		try{
			session.beginTransaction();
			chain.doFilter(request, response);
			session.getTransaction().commit();
		}
		catch(Throwable e)
		{
			if (session.getTransaction().isActive())
			{
				session.getTransaction().rollback();
			}
		}
		
		
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		factory = HibernateUtil.getSessionFactory();
		
	}

}
