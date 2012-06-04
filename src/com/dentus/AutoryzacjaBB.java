package com.dentus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="autoryzacja")
@ApplicationScoped
public class AutoryzacjaBB implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String imie;
	private String nazwisko;
	private String nick;
	private String haslo;
	private UserService us = new UserService(new File("/home/tomasz/git/Dentus/lista.dat"));
	
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
	public String registerUser() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		User user = new User(imie,nazwisko,nick,haslo);
		us.wstawRekord(user);
		return "confirm";
		
	}
	public String verifyUser() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		User user=us.pobierzRekord(nick, haslo);
		if (user!=null)
		{
			imie=user.getImie();
			nazwisko=user.getNazwisko();
			nick=user.getNick();
			haslo=user.getHaslo();
			return "confirm";
		}
		else
		return null;
		
	}
}
