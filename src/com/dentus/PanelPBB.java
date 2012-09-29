package com.dentus;

import java.io.IOException;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.confirmdialog.ConfirmDialog;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.component.tabview.TabView;

import org.primefaces.event.SelectEvent;

@ManagedBean(name="panelPBB")
@SessionScoped
public class PanelPBB
{
	private Pacjent selectedPatient;
	private GOI selectedGOI=new GOI();
	private GOIDataModel goiModel;
	private HistoriaWpis selectedWpis;
	private HistoriaWpis editedWpis;
	private HistoryDataModel historyModel;
	private Boolean DialogforNewWpis=true; 
	public PanelPBB()
	{
	
	}
	public Boolean isDialogforNewWpis()
	{
		return DialogforNewWpis;
	}
	public void setDialogforNewWpis(Boolean dialogforNewWpis)
	{
		DialogforNewWpis = dialogforNewWpis;
	}
	public HistoriaWpis getEditedWpis()
	{
		return editedWpis;
	}

	public void setEditedWpis(HistoriaWpis editedWpis)
	{
		this.editedWpis = editedWpis;
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
	
	public GOI getSelectedGOI()
	{
		return selectedGOI;
	}
	public void setSelectedGOI(GOI selectedGOI)
	{
		this.selectedGOI = selectedGOI;
	}
	public GOIDataModel getGoiModel()
	{
		return goiModel;
	}
	public void setGoiModel(GOIDataModel goiModel)
	{
		this.goiModel = goiModel;
	}
	public void onEdytuj()
	{
		 FacesContext context = FacesContext.getCurrentInstance();
		 EdytorPBB edytor= (EdytorPBB) context.getApplication().evaluateExpressionGet(context, "#{edytorPBB}", EdytorPBB.class);
		 edytor.setPacjent(getSelectedPatient());
		 edytor.setTytul("Patient data edition");
		 edytor.setNew(false);
		 NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		 navigationHandler.handleNavigation(context, null, "edytorp"+"?faces-redirect=true");
	}
	public void onUsunButton() throws IOException
	{
		RecordService rs = new RecordService();
		if (rs.isUsedbySchedule(getSelectedPatient()))
		{	
			
			showAlert();
			
		}
		else
		{
			showDeleteAlert();
		}
	}
	public void usun() throws IOException
	{
		RecordService rs = new RecordService();
		FacesContext context = FacesContext.getCurrentInstance();
		rs.usunRekord(getSelectedPatient());
		Tablica tablica = (Tablica) context.getApplication().evaluateExpressionGet(context, "#{tablica}", Tablica.class);
		tablica.updateList();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, "pacjenci"+"?faces-redirect=true");
	}
	public void onNowyWpis(ActionEvent event)
	{
		setDialogforNewWpis(true);
		showWpisWindow();
		editedWpis = new HistoriaWpis();
	}
	public void onRowSelect(SelectEvent event)
	{
		//System.out.println(getSelectedWpis().data);
		setDialogforNewWpis(false);
		setEditedWpis(getSelectedWpis()); //ustawia pola dialogu zgodnie z wartosciami wybranego wiersza
		showWpisWindow(); //uwidacznia dialog
	}
	public void onToHistoryButton()
	{
		System.out.println("Jestem w onToHistoryButton()");
		
		setDialogforNewWpis(true);
		editedWpis = new HistoriaWpis();
		fillDialogwithGOI();
		showWpisWindow();
		
		
	}
	public void onDeleteAppointmentButton() throws IOException
	{
		//System.out.println("Jestem w onDeleteAppointmentButton()");
		FacesContext ctx = FacesContext.getCurrentInstance();
		ScheduleBean sB = (ScheduleBean) ctx.getApplication().evaluateExpressionGet(ctx, "#{scheduleBean}", ScheduleBean.class);
		GOIService goiService = new GOIService();
		goiService.deleteGOI(selectedGOI);
		sB.initializeScheduleModel();
		
		setGoiModel(new GOIDataModel(new GOIService().getListofSpecificGOI(getSelectedPatient())));
	}
		
	public void potwierdzWpis() throws IOException
	{
		HistoriaWpis wpis =getEditedWpis();
		
		
		if(isDialogforNewWpis())
		{
			selectedPatient.dodajHistoriaWpis(wpis);
			System.out.println("is dialog for new wpis yes");
		}
			else
			{
			selectedPatient.zastapWpis(wpis, selectedWpis);
			System.out.println("zastapienie wpisu");
			}
			historyModel.setWrappedData(selectedPatient.getHistoria());
		new RecordService().updateRecord(selectedPatient);
		
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view = context.getViewRoot();
		TabView tabview= (TabView) view.findComponent(":tab");
		tabview.setActiveIndex(2);
		//hideWpisWindow();
	}
	public void usunWpis() throws IOException
	{
		
		selectedPatient.usunWpis(selectedWpis);
		historyModel.setWrappedData(selectedPatient.getHistoria());
		new RecordService().updateRecord(selectedPatient);
		
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
	private void showAlert()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		ConfirmDialog dialog = (ConfirmDialog) view.findComponent("confirmPatientDialog");
		dialog.setVisible(true);
		
	}
	private void showDeleteAlert()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		ConfirmDialog dialog = (ConfirmDialog) view.findComponent("deleteAlert");
		dialog.setVisible(true);
		
	}
	private void fillDialogwithGOI() //wypełnia Edytor wpisów do historii za pomocą danych z wybranego rekordu
	{
		System.out.println(selectedGOI.getStartDate().toString());
		editedWpis.data=selectedGOI.getStartDate();
		editedWpis.leczenie=selectedGOI.getUsluga().getNazwa();
	}
}
