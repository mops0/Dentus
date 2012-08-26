package com.dentus;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.confirmdialog.ConfirmDialog;
import org.primefaces.component.dialog.Dialog;
@ManagedBean(name="tabelaUslug")
@SessionScoped
public class TabelaUslug implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RecordServiceUsluga recordService;
	private List<Usluga> listaUslug;
	private UslugaDataModel uslugaModel;
	private Usluga editedUsluga;
	private Usluga selectedUsluga;
	private boolean dialogforNewWpis;
	public TabelaUslug()
	{
		recordService=new RecordServiceUsluga();
		listaUslug=recordService.readUslugi();
		uslugaModel= new UslugaDataModel(listaUslug);
	}
	public boolean isDialogforNewWpis()
	{
		return dialogforNewWpis;
	}
	public void setDialogforNewWpis(boolean dialogforNewWpis)
	{
		this.dialogforNewWpis = dialogforNewWpis;
	}
	public UslugaDataModel getUslugaModel()
	{
		return uslugaModel;
	}
	public void setUslugaModel(UslugaDataModel uslugaModel)
	{
		this.uslugaModel = uslugaModel;
	}
	public Usluga getEditedUsluga()
	{
		return editedUsluga;
	}
	public void setEditedUsluga(Usluga editedUsluga)
	{
		this.editedUsluga = editedUsluga;
	}
	public Usluga getSelectedUsluga()
	{
		return selectedUsluga;
	}
	public void setSelectedUsluga(Usluga selectedUsluga)
	{
		this.selectedUsluga = selectedUsluga;
	}
	public void onNowaUsluga(ActionEvent event)
	{
		setDialogforNewWpis(true);
		showWpisWindow();
		editedUsluga = new Usluga();
		
	}
	public void onRowSelect()
	{
		setDialogforNewWpis(false);
		
		
		

		setEditedUsluga(getSelectedUsluga()); 
		showWpisWindow(); 
	}
	public void potwierdzWpis()
	{
		Usluga usluga=getEditedUsluga();
		//usluga.generateId();
		if (isDialogforNewWpis())
		{
			listaUslug.add(usluga);
			recordService.addUsluga(usluga);
		}
		else
		{	
			recordService.update(usluga);
			
		}
		uslugaModel.setWrappedData(listaUslug);
			
	}
	public void usunWpis() throws IOException
	{	
		Usluga usluga=getEditedUsluga();
		
		if (recordService.isUsedbySchedule(usluga))
		{	
			hideWpisWindow();
			showAlert();
			System.out.println("Nie można usunąć ze względu na powiązania z kalendarzem!!!");
		}
		else
		{
		usluga.usunZlisty(listaUslug);
		uslugaModel.setWrappedData(listaUslug);
		recordService.delete(usluga);
		hideWpisWindow();
		}
	}
	private void showWpisWindow()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		Dialog dialog = (Dialog) view.findComponent("uslugaEdytor");
		dialog.setVisible(true);
		
	}
	private void hideWpisWindow()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		Dialog dialog = (Dialog) view.findComponent("uslugaEdytor");
		dialog.setVisible(false);
		
	}
	private void showAlert()
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		ConfirmDialog dialog = (ConfirmDialog) view.findComponent("confirmDialog");
		dialog.setVisible(true);
		
	}
	
	
	
}
