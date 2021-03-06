package com.dentus;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean(name="tablica")
@SessionScoped
public class Tablica implements Serializable
{
	private List<Pacjent> lista= new ArrayList<Pacjent>();
	private static final long serialVersionUID = 1L;
	private PatientDataModel patientModel;
	private Pacjent selectedPatient;

	
	public Tablica() throws IOException
	{
		Calendar cal= Calendar.getInstance();
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.get(Calendar.MONTH));
		lista=new RecordService().odczytajRekordy();
		patientModel=new PatientDataModel(lista);
	}
	public PatientDataModel getPatientModel()
	{
		return patientModel;
	}
	public void setPatientModel(PatientDataModel patientModel)
	{
		this.patientModel = patientModel;
	}
	public Pacjent getSelectedPatient()
	{
		return selectedPatient;
	}
	public void setSelectedPatient(Pacjent selectedPatient)
	{
		this.selectedPatient = selectedPatient;
	}
	public void onRowSelect(SelectEvent event) 
	{  
	      
		FacesContext context = FacesContext.getCurrentInstance();
		PanelPBB panel= (PanelPBB) context.getApplication().evaluateExpressionGet(context, "#{panelPBB}", PanelPBB.class);
		getSelectedPatient().generateBirthDayString();

		//selectedPatient.getHistoria().add(new HistoriaWpis("22.01.2012","Pruchnica","Borowanie","Pacjent oszaloł"));
		//selectedPatient.getHistoria().add(new HistoriaWpis("23.01.2012","Szaleństwo","Lewatywa","Pacjent polubił zabawe z mydłem"));
		panel.setHistoryModel(new HistoryDataModel(selectedPatient.getHistoria()));
		
		panel.setSelectedPatient(getSelectedPatient());
		panel.setGoiModel(new GOIDataModel(new GOIService().getListofSpecificGOI(getSelectedPatient()))); 
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, "panelp"+"?faces-redirect=true");
	}
	
	public void onNowyPacjent(ActionEvent event)
	{
		 FacesContext context = FacesContext.getCurrentInstance();
		 
		 EdytorPBB edytor= (EdytorPBB) context.getApplication().evaluateExpressionGet(context, "#{edytorPBB}", EdytorPBB.class);
		 edytor.setNew(true);
		 edytor.setPacjent(new Pacjent());
		 edytor.setTytul("New Patient");
		 
		 NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		 navigationHandler.handleNavigation(context, null, "edytorp"+"?faces-redirect=true");
	} 
	public void updateList() throws IOException
	{
		lista=new RecordService().odczytajRekordy();
		patientModel=new PatientDataModel(lista);
	}
	
}
