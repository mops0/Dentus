package com.dentus;

import java.util.Calendar;
import java.util.List;

public class Usluga
{
 private String nazwa;
 private int czas;
 private int koszt;
 private String uwagi;
 private long id;
 private String principalName;
 
public String getPrincipalName()
{
	return principalName;
}
public void setPrincipalName(String principalName)
{
	this.principalName = principalName;
}
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
	if (!(obj instanceof Usluga))
	{
		return false;
	}
	Usluga other = (Usluga) obj;
	if (id != other.id)
	{
		return false;
	}
	return true;
}
public void generateId()
{
	Calendar cal=Calendar.getInstance();
	setId(cal.getTime().getTime());
}
public void usunZlisty(List<Usluga> uslugi)
{
	for(int licznik=0;licznik<uslugi.size();licznik++)
	{
		Usluga usluga=uslugi.get(licznik);
		if(getId()==usluga.getId())
		{
			uslugi.remove(licznik);
			
		}
	}
}
@Override
public String toString()
{
	return "Usluga [nazwa=" + nazwa + ", czas=" + czas + ", koszt=" + koszt
			+ ", uwagi=" + uwagi + ", id=" + id + "]";
}

}
