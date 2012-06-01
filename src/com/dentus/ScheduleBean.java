package com.dentus;

import java.util.Calendar;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name="scheduleBean")
@ApplicationScoped

public class ScheduleBean
{
	private ScheduleModel model;
	
	public ScheduleBean()
	{
		
		Calendar czas1 = Calendar.getInstance();
		Calendar czas2 = Calendar.getInstance();
		czas1.set(2012, 4, 8, 15, 30, 0);
		czas2.set(2012, 4, 8, 15, 43, 0);
		model=new DefaultScheduleModel();
		model.addEvent(new DefaultScheduleEvent("Zdarzenie nr 1 ",czas1.getTime(),czas2.getTime()));
	}

	public ScheduleModel getModel()
	{
		return model;
	}

	
}
