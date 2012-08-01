package com.dentus;

import java.util.Calendar;

public class Usluga
{
 private String nazwa;
 private int czas;
 private int koszt;
 private String uwagi;
 private long id;
public String getNazwa()
{
	return nazwa;
}
public void setNazwa(String nazwa)
{
	this.nazwa = nazwa;
}
public int getCzas()
{
	return czas;
}
public void setCzas(int czas)
{
	this.czas = czas;
}
public int getKoszt()
{
	return koszt;
}
public void setKoszt(int koszt)
{
	this.koszt = koszt;
}
public String getUwagi()
{
	return uwagi;
}
public void setUwagi(String uwagi)
{
	this.uwagi = uwagi;
}
public long getId()
{
	return id;
}
public void setId(long id)
{
	this.id = id;
}
public void generateId()
{
	Calendar cal=Calendar.getInstance();
	setId(cal.getTime().getTime());
}
 
}
