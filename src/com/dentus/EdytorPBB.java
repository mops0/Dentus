package com.dentus;


//Jakis dopisek

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;


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
	private SelectDateItem birthDate = new SelectDateItem();
	private String tytul;
	private boolean isNew;
	
	
	public SelectDateItem getBirthDate()
	{
		return birthDate;
	}
	public void setBirthDate(SelectDateItem birthDate)
	{
		this.birthDate = birthDate;
	}
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
		Calendar cal= Calendar.getInstance();
		cal=getBirthDate().parseToCalendar();
		pacjent.setDataUrodzenia(cal.getTime());
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
class SelectDateItem
{
	private int day;
	private int month;
	private int year;
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public Calendar parseToCalendar()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(getYear(), getMonth(), getDay());
		return cal;
		
	}
	
}
