package com.dentus;

import java.util.Date;

public class BackedEvent
{
	private String nazwisko;
	private String usluga;
	private Date startDate;
	private Date startTime;
	public String getNazwisko()
	{
		return nazwisko;
	}
	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}
	public String getUsluga()
	{
		return usluga;
	}
	public void setUsluga(String usluga)
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
	public Date getStartTime()
	{
		return startTime;
	}
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	} 
	
}
