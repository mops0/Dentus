package com.dentus;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class HistoriaWpis implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	Date data=new Date();
	String rozpoznanie=new String();
	String leczenie=new String();
	String komentarz=new String();
	private long id;
	private Pacjent pacjent;
	HistoriaWpis()
	{
		
	}
	HistoriaWpis(Date data,String rozpoznanie,String leczenie,String komentarz,long id)
	{
		this.data=data;
		this.rozpoznanie=rozpoznanie;
		this.leczenie=leczenie;
		this.komentarz=komentarz;
		this.id=id;
	}
	
	public Pacjent getPacjent()
	{
		return pacjent;
	}
	public void setPacjent(Pacjent pacjent)
	{
		this.pacjent = pacjent;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public Date getData()
	{
		return data;
	}
	public void setData(Date data)
	{
		this.data = data;
	}
	public String getRozpoznanie()
	{
		return rozpoznanie;
	}
	public void setRozpoznanie(String rozpoznanie)
	{
		this.rozpoznanie = rozpoznanie;
	}
	public String getLeczenie()
	{
		return leczenie;
	}
	public void setLeczenie(String leczenie)
	{
		this.leczenie = leczenie;
	}
	public String getKomentarz()
	{
		return komentarz;
	}
	public void setKomentarz(String komentarz)
	{
		this.komentarz = komentarz;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((komentarz == null) ? 0 : komentarz.hashCode());
		result = prime * result
				+ ((leczenie == null) ? 0 : leczenie.hashCode());
		result = prime * result
				+ ((rozpoznanie == null) ? 0 : rozpoznanie.hashCode());
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
		if (!(obj instanceof HistoriaWpis))
		{
			return false;
		}
		HistoriaWpis other = (HistoriaWpis) obj;
		if (data == null)
		{
			if (other.data != null)
			{
				return false;
			}
		} else if (!data.equals(other.data))
		{
			return false;
		}
		if (komentarz == null)
		{
			if (other.komentarz != null)
			{
				return false;
			}
		} else if (!komentarz.equals(other.komentarz))
		{
			return false;
		}
		if (leczenie == null)
		{
			if (other.leczenie != null)
			{
				return false;
			}
		} else if (!leczenie.equals(other.leczenie))
		{
			return false;
		}
		if (rozpoznanie == null)
		{
			if (other.rozpoznanie != null)
			{
				return false;
			}
		} else if (!rozpoznanie.equals(other.rozpoznanie))
		{
			return false;
		}
		return true;
	}
	
}
