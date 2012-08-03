package com.dentus;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.event.SelectEvent;
@ManagedBean(name="tabelaUslug")
@SessionScoped
public class TabelaUslug implements Serializable
{
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
		System.out.println("Info z NowaUsluga");
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
		usluga.usunZlisty(listaUslug);
		uslugaModel.setWrappedData(listaUslug);
		recordService.delete(usluga);
	}
	private void showWpisWindow()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view= context.getViewRoot();
		Dialog dialog = (Dialog) view.findComponent("uslugaEdytor");
		dialog.setVisible(true);
		
	}
	
	
	
	
}
