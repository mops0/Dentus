package com.dentus;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class HistoriaWpis implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Date data=new Date();
	String rozpoznanie=new String();
	String leczenie=new String();
	String komentarz=new String();
	long id;
	HistoriaWpis()
	{
		
	}
	HistoriaWpis(Date data,String rozpoznanie,String leczenie,String komentarz,long id)
	{
		this.data=data;
		this.rozpoznanie=rozpoznanie;
		this.leczenie=leczenie;
		this.komentarz=komentarz;
		this.id=id;
	}
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public Date getData()
	{
		return data;
	}
	public void setData(Date data)
	{
		this.data = data;
	}
	public String getRozpoznanie()
	{
		return rozpoznanie;
	}
	public void setRozpoznanie(String rozpoznanie)
	{
		this.rozpoznanie = rozpoznanie;
	}
	public String getLeczenie()
	{
		return leczenie;
	}
	public void setLeczenie(String leczenie)
	{
		this.leczenie = leczenie;
	}
	public String getKomentarz()
	{
		return komentarz;
	}
	public void setKomentarz(String komentarz)
	{
		this.komentarz = komentarz;
	}
	public void generateId()
	{
		Calendar cal=Calendar.getInstance();
		setId(cal.getTime().getTime());
	}
	
}
