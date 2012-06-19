package com.dentus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name="scheduleBean")
@ApplicationScoped

public class ScheduleBean
{
	private ScheduleModel model;
	private TimeZone timeZone=TimeZone.getDefault();
	private DefaultScheduleEvent selectedEvent = new DefaultScheduleEvent();
	private List<Pacjent> lista = new ArrayList<Pacjent>();
	FacesContext context =FacesContext.getCurrentInstance();
	private SelectItemsBean selectItemsBean; 
	
	public ScheduleBean() throws IOException
	{
		
		model=new DefaultScheduleModel();
		lista=new RecordService().odczytajRekordy();
		selectItemsBean =(SelectItemsBean)context.getApplication().evaluateExpressionGet(context, "#{selectItemsBean}", SelectItemsBean.class);
		//System.out.println(selectItemsBean.toString());
	}
	public TimeZone getTimeZone()
	{
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZOne)
	{
		this.timeZone = timeZOne;
	}

	public List<Pacjent> getLista()
	{
		return lista;
	}

	public void setLista(List<Pacjent> lista)
	{
		this.lista = lista;
	}

	public ScheduleEvent getSelectedEvent()
	{
		return selectedEvent;
	}

	public void setSelectedEvent(DefaultScheduleEvent selectedEvent)
	{
		this.selectedEvent = selectedEvent;
	}
	public ScheduleModel getModel()
	{
		return model;
	}
	public void onDateSelect(DateSelectEvent e) throws IOException
	{
		selectItemsBean.generateNames();
		selectedEvent=new DefaultScheduleEvent("",e.getDate(),e.getDate());
		
	}
	public void onEventSelect(ScheduleEntrySelectEvent e) throws IOException
	{
		selectItemsBean.generateNames();
		selectedEvent=(DefaultScheduleEvent)e.getScheduleEvent();
	}
	public void addEvent()
	{
		if(selectedEvent.getId()==null)
			model.addEvent(selectedEvent);
		
		else
			model.updateEvent(selectedEvent);
			selectedEvent=new DefaultScheduleEvent();
	}

	
}
