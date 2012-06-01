package com.dentus;




import java.io.IOException;
import java.io.Serializable;


import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
@ManagedBean(name="edytorPBB")
@SessionScoped
public class EdytorPBB implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pacjent pacjent=new Pacjent();
	private Pacjent comparePacjent = new Pacjent();
	private String tytul;
	private boolean isNew;
	
	
	/*
	private String imie;
	private String nazwisko;
	private int wiek;
	private String adres1;
	private String adres2;
	private String adres3;
	
	private String email;
	private String telefon;
	private String alergie;
	private String choroby;
	private String opisPacjenta;
	private Date terminWizyty;
	private String historia;

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
	public int getWiek()
	{
		return wiek;
	}
	public void setWiek(int wiek)
	{
		this.wiek = wiek;
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
	public Date getTerminWizyty()
	{
		return terminWizyty;
	}
	public void setTerminWizyty(Date terminWizyty)
	{
		this.terminWizyty = terminWizyty;
	}
	public String getHistoria()
	{
		return historia;
	}
	public void setHistoria(String historia)
	{
		this.historia = historia;
	}
	*/
	public Pacjent getPacjent()
	{
		return pacjent;
	}
	public void setPacjent(Pacjent pacjent)
	{
		this.pacjent = pacjent;
	}
	public String getTytul()
	{
		return tytul;
	}
	public void setTytul(String tytul)
	{
		this.tytul = tytul;
	}
	public boolean isNew()
	{
		return isNew;
	}
	public void setNew(boolean isNew)
	{
		this.isNew = isNew;
	}
	public Pacjent getComparePacjent()
	{
		return comparePacjent;
	}
	public void setComparePacjent(Pacjent comparePacjent)
	{
		this.comparePacjent = comparePacjent;
	}
	public void confirm() throws IOException
	{
		/*
		Pacjent pacjent = new Pacjent();
		
		pacjent.setNazwisko(nazwisko);
		pacjent.setImie(imie);
		pacjent.setWiek(wiek);
		pacjent.setAdres1(adres1);
		pacjent.setAdres2(adres2);
		pacjent.setAdres3(adres3);
		pacjent.setEmail(email);
		pacjent.setTelefon(telefon);
		pacjent.setAlergie(alergie);
		pacjent.setChoroby(choroby);
		historia="puste pole";
		pacjent.setHistoria(historia);
		*/
		RecordService rs = new RecordService();
		if (isNew)
		{
			rs.dodajRekord(pacjent);
		}
		else
		{
			rs.zastapRekord(pacjent,getComparePacjent());
		}
		FacesContext context = FacesContext.getCurrentInstance();
		Tablica tablica = (Tablica) context.getApplication().evaluateExpressionGet(context, "#{tablica}", Tablica.class);
		tablica.updateList();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, "pacjenci"+"?faces-redirect=true");
	}
	public void updateParameters()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Tablica tablica = (Tablica) context.getApplication().evaluateExpressionGet(context, "#{tablica}", Tablica.class);
		/*
		Pacjent pacjent = tablica.getSelectedPatient();
		setNazwisko(pacjent.getNazwisko());
		setImie(pacjent.getImie());
		setWiek(pacjent.getWiek());
		setAdres1(pacjent.getAdres1());
		setAdres2(pacjent.getAdres2());
		setAdres3(pacjent.getAdres3());
		setEmail(pacjent.getEmail());
		setTelefon(pacjent.getTelefon());
		setAlergie(pacjent.getAlergie());
		setChoroby(pacjent.getChoroby());
		setHistoria(pacjent.getHistoria());
		*/
		 pacjent = tablica.getSelectedPatient();
	}
	public void ustawPacjenta(Pacjent pacjent)
	{
		setPacjent(pacjent);
		setComparePacjent(pacjent);
	}
	
}
