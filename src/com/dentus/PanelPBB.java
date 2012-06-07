package com.dentus;

import java.io.IOException;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.primefaces.component.dialog.Dialog;
import org.primefaces.event.SelectEvent;

@ManagedBean(name="panelPBB")
@SessionScoped
public class PanelPBB
{
	private Pacjent selectedPatient=new Pacjent();
	private HistoriaWpis selectedWpis= new HistoriaWpis();
	List<HistoriaWpis> wpisy = new ArrayList<HistoriaWpis>();
	private HistoryDataModel historyModel;
	public PanelPBB()
	{
		wpisy.add(new HistoriaWpis("22.01.2012","Pruchnica","Borowanie","Pacjent oszalał"));
		wpisy.add(new HistoriaWpis("23.01.2012","Szaleństwo","Lewatywa","Pacjent polubił zabawe z mydłem"));
		
		
		historyModel=new HistoryDataModel(wpisy);
	}
	public HistoriaWpis getSelectedWpis()
	{
		return selectedWpis;
	}

	public void setSelectedWpis(HistoriaWpis selectedWpis)
	{
		this.selectedWpis = selectedWpis;
	}

	public HistoryDataModel getHistoryModel()
	{
		return historyModel;
	}
	public void setHistoryModel(HistoryDataModel historyModel)
	{
		this.historyModel = historyModel;
	}
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
	public void onNowyWpis(SelectEvent event)
	{
		showWpisWindow();
		System.out.println("bla");
		
		
		
	}
	public void onRowSelect(SelectEvent event)
	{
		showWpisWindow();
	}
	public void potwierdzWpis()
	{
		hideWpisWindow();
	}
	public void usunWpis()
	{
		
	}
	public void showWpisWindow()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		Dialog dialog = (Dialog) view.findComponent("wpisEdytor");
		dialog.setVisible(true);
	}
	public void hideWpisWindow()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		Dialog dialog = (Dialog) view.findComponent("wpisEdytor");
		dialog.setVisible(false);
	}
}
