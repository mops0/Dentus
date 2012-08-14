package com.dentus;

public class Kolor
{
	private String nazwa;
	private long id;
	public String getNazwa()
	{
		return nazwa;
	}
	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
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
		if (!(obj instanceof Kolor))
		{
			return false;
		}
		Kolor other = (Kolor) obj;
		if (id != other.id)
		{
			return false;
		}
		return true;
	}
	@Override
	public String toString()
	{
		return "Kolor [nazwa=" + nazwa + ", id=" + id + "]";
	}
	
}
