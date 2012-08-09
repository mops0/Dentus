package com.dentus;

import java.util.Date;

import org.primefaces.model.DefaultScheduleEvent;

public class GOI
{	
	private long id;
	private Pacjent pacjent;
	private Usluga usluga;
	private Date startDate;
	private Date endDate;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
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
	public Date getStartDate()
	{
		return startDate;
	}
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	public Date getEndDate()
	{
		return endDate;
	}
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}
	public DefaultScheduleEvent toDSEvent()
	{
		DefaultScheduleEvent dsEvent= new DefaultScheduleEvent();	
		dsEvent.setTitle("\n"+pacjent.getImie()+" "+pacjent.getNazwisko()+"\n"+usluga.getNazwa());
		dsEvent.setStartDate(startDate);
		dsEvent.setEndDate(endDate);
		dsEvent.setData(this);
		return dsEvent;
	}
	public DefaultScheduleEvent toDSEvent(String key)
	{
		DefaultScheduleEvent dsEvent =toDSEvent();
		dsEvent.setId(key);
		return dsEvent;
		
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof GOI))
		{
			return false;
		}
		GOI other = (GOI) obj;
		if (id != other.id)
		{
			return false;
		}
		return true;
	}
	
}
