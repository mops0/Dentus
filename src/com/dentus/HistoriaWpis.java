package com.dentus;

import java.util.Calendar;

public class HistoriaWpis
{
	String data=new String();
	String rozpoznanie=new String();
	String leczenie=new String();
	String komentarz=new String();
	long id;
	HistoriaWpis()
	{
		
	}
	HistoriaWpis(String data,String rozpoznanie,String leczenie,String komentarz,long id)
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
	public String getData()
	{
		return data;
	}
	public void setData(String data)
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
