package com.dentus;

public class HistoriaWpis
{
	String data;
	String rozpoznanie;
	String leczenie;
	String komentarz;
	HistoriaWpis()
	{
		
	}
	HistoriaWpis(String data,String rozpoznanie,String leczenie,String komentarz)
	{
		this.data=data;
		this.rozpoznanie=rozpoznanie;
		this.leczenie=leczenie;
		this.komentarz=komentarz;
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
	
}
