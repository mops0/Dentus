package com.dentus;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Pacjent implements Serializable

{
	private static final long serialVersionUID = 1L;
	
	private String imie;
	private String nazwisko;
	private Date dataUrodzenia=new Date();
	private String birthDayString;
	private String adres1;
	private String adres2;
	private String adres3;
	private int wiek;
	private long id;
	private String email;
	private String telefon;
	private String alergie;
	private String choroby;
	private String opisPacjenta;
	//private Date terminWizyty;
	private List<HistoriaWpis> historia= new ArrayList<HistoriaWpis>();
	
	
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
	public List<HistoriaWpis> getHistoria()
	{
		return historia;
	}
	public void setHistoria(List<HistoriaWpis> historia)
	{
		this.historia = historia;
	}
	public String getBirthDayString()
	{
		return birthDayString;
	}
	public void setBirthDayString(String birthDayString)
	{
		this.birthDayString = birthDayString;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public int getWiek()
	{
		return wiek;
	}
	public void setWiek(int wiek)
	{
		this.wiek = wiek;
	}
	public Date getDataUrodzenia()
	{
		return dataUrodzenia;
	}
	public void setDataUrodzenia(Date dataUrodzenia)
	{
		this.dataUrodzenia = dataUrodzenia;
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
	public void generateWiek()
	{
		Calendar cal= Calendar.getInstance();
		int currentYear=cal.get(Calendar.YEAR);
		cal.setTime(getDataUrodzenia());
		int birthYear=cal.get(Calendar.YEAR);
		
		 setWiek(currentYear-birthYear);
		
	}
	
	public void generateBirthDayString()
	{
		Calendar cal= Calendar.getInstance();
		cal.setTime(getDataUrodzenia());
		setBirthDayString(cal.get(Calendar.DAY_OF_MONTH)+"."+cal.get(Calendar.MONTH)+"."+cal.get(Calendar.YEAR));
	}
	public void zastapWpis(HistoriaWpis nWpis, HistoriaWpis sWpis)
	{
		for(int licznik=0;licznik<historia.size();licznik++)
		{
			HistoriaWpis wpis=historia.get(licznik);
			if(wpis.getId()==sWpis.getId())
			{
				historia.set(licznik, nWpis);
			}
		}
	}
	public void usunWpis(HistoriaWpis uWpis)
	{
		for(int licznik=0;licznik<historia.size();licznik++)
		{
			HistoriaWpis wpis=historia.get(licznik);
			if(wpis.getId()==uWpis.getId())
			{
				historia.remove(licznik);
			}
		}
	}
	public void dodajHistoriaWpis(HistoriaWpis wpis)
	{
		wpis.setPacjent(this);
		historia.add(wpis);
	}
	@Override
	public String toString()
	{
		return "Pacjent [imie=" + imie + ", nazwisko=" + nazwisko
				+ ", dataUrodzenia=" + dataUrodzenia + ", birthDayString="
				+ birthDayString + ", adres1=" + adres1 + ", adres2=" + adres2
				+ ", adres3=" + adres3 + ", wiek=" + wiek + ", id=" + id
				+ ", email=" + email + ", telefon=" + telefon + ", alergie="
				+ alergie + ", choroby=" + choroby + ", opisPacjenta="
				+ opisPacjenta + ", historia=" + historia + "]";
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Pacjent))
		{
			return false;
		}
		Pacjent other = (Pacjent) obj;
		if (id != other.id)
		{
			return false;
		}
		return true;
	}
	
}

