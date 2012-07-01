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
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		try{
			chain.doFilter(request, response);
			HibernateUtil.commitTransaction();
			
		}finally {
			HibernateUtil.closeSession();
		}
		
		
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		
		
	}

}
