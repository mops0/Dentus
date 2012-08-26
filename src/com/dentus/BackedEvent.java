package com.dentus;

import java.util.Calendar;
import java.util.Date;

public class BackedEvent
{
	private Pacjent pacjent;
	private Usluga usluga;
	private Date startDateNoTime;
	private Date startTime;
	private long GOIid;
	private String scheduleId;
	
	BackedEvent()
	{
		
	}
	BackedEvent(GOI goi)
	{
		pacjent=goi.getPacjent();
		usluga=goi.getUsluga();
		startTime=extractTimeFromDate(goi.getStartDate());
		startDateNoTime=goi.getStartDate();
		GOIid=goi.getId();
	}
	
	public Pacjent getPacjent()
	{
		return pacjent;
	}
	public void setPacjent(Pacjent pacjent)
	{
		this.pacjent = pacjent;
	}
	public Usluga getUsluga()
	{
		return usluga;
	}
	public void setUsluga(Usluga usluga)
	{
		this.usluga = usluga;
	}
	
	
	
	public long getGOIid()
	{
		return GOIid;
	}
	public void setGOIid(long gOIid)
	{
		GOIid = gOIid;
	}
	public Date getStartDateNoTime()
	{
		return startDateNoTime;
	}
	public void setStartDateNoTime(Date startDateNoTime)
	{
		this.startDateNoTime = startDateNoTime;
	}
	
	public String getScheduleId()
	{
		return scheduleId;
	}
	public void setScheduleId(String scheduleId)
	{
		this.scheduleId = scheduleId;
	}
	public Date getStartTime()
	{
		return startTime;
	}
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}
	private Date mergeTimeDate(Date data,Date czas)
	{
		Date newDate=new Date();
		newDate.setTime(data.getTime()+czas.getTime());
		return newDate;
		
	}
	private Date extractTimeFromDate(Date data)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int hours=cal.get(Calendar.HOUR_OF_DAY);
		int minutes=cal.get(Calendar.MINUTE);
		Date newDate=new Date((long)(hours*3600000+minutes*60000));
		return newDate;
	}
	private Date generateStartDate()
	{
		return mergeTimeDate(startDateNoTime,startTime);
	}
	private Date generateEndDate()
	{
		long servicePeriod=usluga.getCzas()*60000;
		return(new Date(generateStartDate().getTime()+servicePeriod));
	}
	public GOI toGOI()
	{
		GOI goi = new GOI();
		goi.setId(GOIid);
		goi.setPacjent(pacjent);
		goi.setUsluga(usluga);
		goi.setStartDate(generateStartDate());
		goi.setEndDate(generateEndDate());
		
		return goi;
	}
	
}


