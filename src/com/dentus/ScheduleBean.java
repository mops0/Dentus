package com.dentus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name="scheduleBean")
@SessionScoped
public class ScheduleBean
{
	
	private ScheduleModel model;
	private BackedEvent backedEvent;
	private SelectItemsBean selectItemsBean; 
	FacesContext context =FacesContext.getCurrentInstance();
	private TimeZone timeZone=TimeZone.getDefault();
	private TimeZone timeZoneGMT=TimeZone.getTimeZone("GMT");
	public ScheduleBean()
	{
		model=new DefaultScheduleModel();
		
		initializeScheduleModel();
		backedEvent=new BackedEvent();
		selectItemsBean=(SelectItemsBean)context.getApplication().evaluateExpressionGet(context, "#{selectItemsBean}", SelectItemsBean.class);
	}
	
	public ScheduleModel getModel()
	{
		return model;
	}

	public void setModel(ScheduleModel model)
	{
		this.model = model;
	}

	public BackedEvent getBackedEvent()
	{
		return backedEvent;
	}

	public void setBackedEvent(BackedEvent backedEvent)
	{
		this.backedEvent = backedEvent;
	}

	public TimeZone getTimeZone()
	{
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone)
	{
		this.timeZone = timeZone;
	}

	public TimeZone getTimeZoneGMT()
	{
		return timeZoneGMT;
	}

	public void setTimeZoneGMT(TimeZone timeZoneGMT)
	{
		this.timeZoneGMT = timeZoneGMT;
	}

	private void initializeScheduleModel()
	{
		GOIService goiService = new GOIService();
		ArrayList<GOI> goiList=goiService.pobierzListeGOI();
		 
		for (int i=0;i<goiList.size();i++)
		{
			model.addEvent(goiList.get(i).toDSEvent());
		}
	}
	public void addEvent(ActionEvent event)
	{
		
		
		GOIService goiService = new GOIService();
		if(backedEvent.getGOIid()==0)
		{
			GOI goi = backedEvent.toGOI();
			model.addEvent(goi.toDSEvent());
			goiService.addGOI(goi);
			System.out.println("Jestem w addEvent null");
		}
		else
		{
			GOI goi = backedEvent.toGOI();
			goiService.update(goi);
			DefaultScheduleEvent scheduleEvent =goi.toDSEvent(backedEvent.getScheduleId()) ;
			model.updateEvent(scheduleEvent);
			
			System.out.println("Jestem w addEvent poczatek");
		}
		
	}
	public void deleteEvent(ActionEvent event) throws IOException
	{
		GOIService goiService = new GOIService();
		GOI goi = backedEvent.toGOI();
		goiService.deleteGOI(goi);
		DefaultScheduleEvent scheduleEvent =goi.toDSEvent(backedEvent.getScheduleId()) ;
		model.deleteEvent(scheduleEvent);
	}
	
	public void onDateSelect(DateSelectEvent e) throws IOException
	{
		CommandButton button = (CommandButton) getComponentReference(":formaTerminarz:usun");
		button.setRendered(false);
		selectItemsBean.generateNames();
		selectItemsBean.generateUslugi();
		System.out.println("Jestem w onDateSelect");
		backedEvent=new BackedEvent();
		backedEvent.setStartDateNoTime(e.getDate());
		
	}
	public void onEventSelect(ScheduleEntrySelectEvent e) throws IOException
	{
		
		CommandButton button = (CommandButton) getComponentReference(":formaTerminarz:usun");
		button.setRendered(true);
		selectItemsBean.generateNames();
		selectItemsBean.generateUslugi();
		backedEvent=new BackedEvent((GOI)e.getScheduleEvent().getData());
		backedEvent.setScheduleId(e.getScheduleEvent().getId());
		System.out.println("goi.id od referencji DataDcheduleEvent: "+((GOI)e.getScheduleEvent().getData()).getId());
		System.out.println("goi.usluga.nazwa od referencji DataDcheduleEvent:"+((GOI)e.getScheduleEvent().getData()).getUsluga().getNazwa());
		//System.out.println("Z onEventSelect wartosc backedEvent"+ backedEvent.getPacjent().toString());
		
	}
	public UIComponent getComponentReference(String id)
	{
		FacesContext ncontext =FacesContext.getCurrentInstance();
		UIViewRoot view = ncontext.getViewRoot();
		UIComponent component=view.findComponent(id);
		
		return component;
		
	}
}