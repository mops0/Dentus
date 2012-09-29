package com.dentus;


//Jakis dopisek

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;


import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	public void validateDateofBirth(FacesContext context,UIComponent component, Object data)
	{
		System.out.println("System walidacji działa");
		
		UIViewRoot view = context.getViewRoot();
		int birthDay =(Integer)((HtmlSelectOneMenu) view.findComponent("form:dzien")).getValue();
		int birthMonth =(Integer)((HtmlSelectOneMenu) view.findComponent("form:miesiac")).getValue();
		int birthYear =(Integer)((HtmlSelectOneMenu) view.findComponent("form:rok")).getValue();
		HtmlInputText adres1 = (HtmlInputText)component;
		
		//int birthYear =(Integer)rokComponent.getValue();
		System.out.println("wpisana data urodzenia:" +birthDay+" "+birthMonth+" "+birthYear);
		Calendar birthCal= Calendar.getInstance();
		birthCal.set(birthYear, birthMonth, birthDay);
		Calendar currentCal= Calendar.getInstance();
		
		long birthDate=birthCal.getTime().getTime();
		long currentDate=currentCal.getTime().getTime();
		if (birthDate>currentDate)
	
		{
			adres1.setValid(false);
			FacesMessage message = new FacesMessage("Date of birth not correct!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(component.getClientId(context), message);
		 
		}
		
	}
	public void validateEmail(FacesContext context,UIComponent component, Object data)
	{
		String email =(String) data;
		System.out.println("wpisany email: " +email);
		if(email.indexOf('@')==-1)
		{
			((HtmlInputText)component).setValid(false);
			FacesMessage message = new FacesMessage("Email not correct!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(component.getClientId(context), message);
			
		}
	}
}
