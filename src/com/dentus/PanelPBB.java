package com.dentus;

import java.io.IOException;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="panelPBB")
@SessionScoped
public class PanelPBB
{
	private Pacjent selectedPatient=new Pacjent();
	
	public Pacjent getSelectedPatient()
	{
		return selectedPatient;
	}

	public void setSelectedPatient(Pacjent selectedPatient)
	{
		this.selectedPatient = selectedPatient;
	}
	public void onEdytuj()
	{
		 FacesContext context = FacesContext.getCurrentInstance();
		 EdytorPBB edytor= (EdytorPBB) context.getApplication().evaluateExpressionGet(context, "#{edytorPBB}", EdytorPBB.class);
		 edytor.ustawPacjenta(getSelectedPatient());
		 edytor.setTytul("Edycja danych pacjenta");
		 edytor.setNew(false);
		 NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		 navigationHandler.handleNavigation(context, null, "edytorp"+"?faces-redirect=true");
	}
	public void onUsun() throws IOException
	{
		 FacesContext context = FacesContext.getCurrentInstance();
		 new RecordService().usunRekord(getSelectedPatient());
		 Tablica tablica = (Tablica) context.getApplication().evaluateExpressionGet(context, "#{tablica}", Tablica.class);
		 tablica.updateList();
		 NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		 navigationHandler.handleNavigation(context, null, "pacjenci"+"?faces-redirect=true");
	}
	/*
	public void update()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Tablica tablica = (Tablica) context.getApplication().evaluateExpressionGet(context, "#{tablica}", Tablica.class);
		selectedPatient = tablica.getSelectedPatient();
		
	}
	*/
	
}
