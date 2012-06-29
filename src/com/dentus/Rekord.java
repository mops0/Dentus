package com.dentus;


import java.util.List;



public class Rekord
{
	private long id;
	private String imie;
	private String nazwisko;
	private Historia historia;
	private List<Zdarzenie> zdarzenia;
	
	Rekord()
	{
		
	}
	Rekord(String imie,String nazwisko)
	{
		
		this.imie=imie;
		this.nazwisko=nazwisko;
	}
	
	
	
	public List<Zdarzenie> getZdarzenia()
	{
		return zdarzenia;
	}
	public void setZdarzenia(List<Zdarzenie> zdarzenia)
	{
		this.zdarzenia = zdarzenia;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public Historia getHistoria()
	{
		return historia;
	}
	public void setHistoria(Historia historia)
	{
		this.historia = historia;
	}
	
	public String getImie()
	{
		return imie;
	}
	public void setImie(String imie)
	{
		this.imie = imie;
	}
	public String getNazwisko()
	{
		return nazwisko;
	}
	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}
	
}
