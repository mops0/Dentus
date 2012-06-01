package com.dentus;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String imie;
	private String nazwisko;
	private String nick;
	private String haslo;
	User(String imie,String nazwisko,String nick,String haslo)
	{
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.nick=nick;
		this.haslo=haslo;
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
	public String getNick()
	{
		return nick;
	}
	public void setNick(String nick)
	{
		this.nick = nick;
	}
	public String getHaslo()
	{
		return haslo;
	}
	public void setHaslo(String haslo)
	{
		this.haslo = haslo;
	}
	
}
 