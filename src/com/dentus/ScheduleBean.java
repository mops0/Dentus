package com.dentus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

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
	private TimeZone timeZone=TimeZone.getTimeZone("GMT");
	private TimeZone timeZone2=TimeZone.getTimeZone("Europe/London");
	private DefaultScheduleEvent selectedEvent = new DefaultScheduleEvent();
	private List<Pacjent> lista = new ArrayList<Pacjent>();
	public ScheduleBean() throws IOException
	{
		
		model=new DefaultScheduleModel();
		lista=new RecordService().odczytajRekordy();
	}
	
	public TimeZone getTimeZone2()
	{
		return timeZone2;
	}

	public void setTimeZone2(TimeZone timeZone2)
	{
		this.timeZone2 = timeZone2;
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
	public void onDateSelect(DateSelectEvent e)
	{
		selectedEvent=new DefaultScheduleEvent("",e.getDate(),e.getDate());
	}
	public void onEventSelect(ScheduleEntrySelectEvent e)
	{
		selectedEvent=(DefaultScheduleEvent)e.getScheduleEvent();
	}
	public void addEvent()
	{
		
		
		//selectedEvent.setEndDate(new Date(selectedEvent.getStartDate().getTime()+900000));
		if(selectedEvent.getId()==null)
			model.addEvent(selectedEvent);
		
		else
			model.updateEvent(selectedEvent);
		Calendar kal=Calendar.getInstance();
		
		kal.setTime(selectedEvent.getStartDate());
		Calendar ekal=Calendar.getInstance();
		ekal.setTime(selectedEvent.getEndDate());
		System.out.println(kal.get(Calendar.DAY_OF_MONTH));
		System.out.println(ekal.get(Calendar.DAY_OF_MONTH));
		System.out.println(TimeZone.getDefault());
		System.out.println(TimeZone.getTimeZone("Euroe/Warsaw"));
		selectedEvent=new DefaultScheduleEvent();
	}

	
}
