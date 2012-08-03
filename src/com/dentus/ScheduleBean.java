
package com.dentus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

//import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name="scheduleBean")
@SessionScoped

public class ScheduleBean
{
	private ScheduleModel model;
	private EventService eventService= new EventService();
	private TimeZone timeZone=TimeZone.getDefault();
	private TimeZone timeZoneGMT=TimeZone.getTimeZone("GMT");
	private DefaultScheduleEvent selectedEvent = new DefaultScheduleEvent();
	private Date startTime;
	private List<Pacjent> lista = new ArrayList<Pacjent>();
	FacesContext context =FacesContext.getCurrentInstance();
	private SelectItemsBean selectItemsBean; 
	
	
	public ScheduleBean() throws IOException
	{
		
		model=new DefaultScheduleModel();
		lista=new RecordService().odczytajRekordy();
		selectItemsBean =(SelectItemsBean)context.getApplication().evaluateExpressionGet(context, "#{selectItemsBean}", SelectItemsBean.class);
		initializeScheduleModel();
		//System.out.println(selectItemsBean.toString());
		/*
		UIViewRoot view = context.getViewRoot();
		CommandButton button = (CommandButton) view.findComponent(":j_idt11:j_idt19");
		button.setRendered(false);
	*/
	}
	
	

	public TimeZone getTimeZoneGMT()
	{
		return timeZoneGMT;
	}



	public void setTimeZoneGMT(TimeZone timeZoneGMT)
	{
		this.timeZoneGMT = timeZoneGMT;
	}



	public Date getStartTime()
	{
		return startTime;
	}



	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
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
		CommandButton button = (CommandButton) getComponentReference(":j_idt11:j_idt19");
		button.setRendered(false);
		selectItemsBean.generateNames();
		selectedEvent=new DefaultScheduleEvent("",e.getDate(),e.getDate());
		startTime=extractTimeFromDate(selectedEvent.getStartDate());
	}
	public void onEventSelect(ScheduleEntrySelectEvent e) throws IOException
	{
		
		CommandButton button = (CommandButton) getComponentReference(":j_idt11:j_idt19");
		button.setRendered(true);
		selectItemsBean.generateNames();
		selectedEvent=(DefaultScheduleEvent)e.getScheduleEvent();
		startTime=extractTimeFromDate(selectedEvent.getStartDate());
		
	}
	public void addEvent()
	{
		
		
		selectedEvent.setStartDate(mergeTimeDate(selectedEvent.getStartDate(),startTime));
		selectedEvent.setEndDate(new Date(selectedEvent.getStartDate().getTime()+30*60000));

		Event event = new Event();
		event.setStartDate(selectedEvent.getStartDate());
		event.setEndDate(selectedEvent.getEndDate());
		event.setName(selectedEvent.getTitle());
		
		if(selectedEvent.getId()==null)
		{	
			model.addEvent(selectedEvent);
			eventService.addEvent(event);
		}
		else
		{
			model.updateEvent(selectedEvent);
			event.setId(Long.parseLong(selectedEvent.getId()));
			eventService.update(event);
		}
		
		
		selectedEvent=new DefaultScheduleEvent();
		
		
	}
	public void initializeScheduleModel()
	{
		List<Event> lista = eventService.readEvents();
		DefaultScheduleEvent sEvent= null;
		for (int i=0;i<lista.size();i++)
		{
			sEvent=new DefaultScheduleEvent();
			sEvent.setStartDate(lista.get(i).getStartDate());
			
			sEvent.setEndDate(lista.get(i).getEndDate());
			sEvent.setTitle(lista.get(i).getName()+"\n"+lista.get(i).getTopic());
			sEvent.setId(Long.toString(lista.get(i).getId()));
			/*
			Calendar cal = Calendar.getInstance();
			cal.setTime(sEvent.getStartDate());
			System.out.println(cal.get(Calendar.HOUR_OF_DAY));
			*/
			model.addEvent(sEvent);
		}
		
		
	}
	public void deleteEvent()
	{
		model.deleteEvent(selectedEvent);
		selectedEvent=new DefaultScheduleEvent();
	}
	public Date mergeTimeDate(Date data,Date czas)
	{
		Date newDate=new Date();
		newDate.setTime(data.getTime()+czas.getTime());
		return newDate;
		
	}
	public Date extractTimeFromDate(Date data)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int hours=cal.get(Calendar.HOUR_OF_DAY);
		int minutes=cal.get(Calendar.MINUTE);
		Date newDate=new Date((long)(hours*3600000+minutes*60000));
		return newDate;
		
	}
	public UIComponent getComponentReference(String id)
	{
		FacesContext ncontext =FacesContext.getCurrentInstance();
		UIViewRoot view = ncontext.getViewRoot();
		UIComponent component=view.findComponent(id);
		
		return component;
		
	}
	
}