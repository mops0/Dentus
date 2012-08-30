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
	public void confirm() throws IOException
	{
		Calendar cal= Calendar.getInstance();
		cal=getBirthDate().parseToCalendar();
		pacjent.setDataUrodzenia(cal.getTime());
		
		RecordService rs = new RecordService();
		if (isNew)
		{
			
			pacjent.generateId();
			rs.dodajRekord(pacjent);
		}
		else
		{
			rs.updateRecord(pacjent);
	
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
		
		 pacjent = tablica.getSelectedPatient();
	}
	public void ustawPacjenta(Pacjent pacjent)
	{
		setPacjent(pacjent);
		
	}
	
}
