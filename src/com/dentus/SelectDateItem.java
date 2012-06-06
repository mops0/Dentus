package com.dentus;

import java.io.Serializable;
import java.util.Calendar;


public class SelectDateItem implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day;
	private int month;
	private int year;
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public Calendar parseToCalendar()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(getYear(), getMonth(), getDay());
		return cal;
		
	}
	
}
