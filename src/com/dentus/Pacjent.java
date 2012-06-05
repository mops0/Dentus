package com.dentus;
 
import java.io.Serializable;
import java.util.Date;


public class Pacjent implements Serializable

{
	




	private static final long serialVersionUID = 1L;
	
	private String imie;
	private String nazwisko;
	private Date dataUrodzenia;
	private String adres1;
	private String adres2;
	private String adres3;
	
	



	private String email;
	private String telefon;
	private String alergie;
	private String choroby;
	private String opisPacjenta;
	//private Date terminWizyty;
	private String historia;
	
	Pacjent()
	{
		
	}
	Pacjent(String imie, String nazwisko,String telefon, String email)
	{
		this.imie=imie;
		this.nazwisko=nazwisko;
		
		this.telefon=telefon;
		this.email=email;
		
	}
	public Date getDataUrodzenia()
	{
		return dataUrodzenia;
	}
	public void setDataUrodzenia(Date dataUrodzenia)
	{
		this.dataUrodzenia = dataUrodzenia;
	}

	public String getHistoria()
	{
		return historia;
	}
	public void setHistoria(String historia)
	{
		this.historia = historia;
	}
	
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getTelefon()
	{
		return telefon;
	}
	public void setTelefon(String telefon)
	{
		this.telefon = telefon;
	}
	public String getAlergie()
	{
		return alergie;
	}
	public void setAlergie(String alergie)
	{
		this.alergie = alergie;
	}
	public String getChoroby()
	{
		return choroby;
	}
	public void setChoroby(String choroby)
	{
		this.choroby = choroby;
	}
	public String getOpisPacjenta()
	{
		return opisPacjenta;
	}
	public void setOpisPacjenta(String opisPacjenta)
	{
		this.opisPacjenta = opisPacjenta;
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
	
	public String getAdres1()
	{
		return adres1;
	}
	public void setAdres1(String adres1)
	{
		this.adres1 = adres1;
	}




	public String getAdres2()
	{
		return adres2;
	}




	public void setAdres2(String adres2)
	{
		this.adres2 = adres2;
	}




	public String getAdres3()
	{
		return adres3;
	}




	public void setAdres3(String adres3)
	{
		this.adres3 = adres3;
	}

}

