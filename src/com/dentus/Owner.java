package com.dentus;

public class Owner
{
	private long id;
	private String nazwisko;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getNazwisko()
	{
		return nazwisko;
	}
	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
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
		if (!(obj instanceof Owner))
		{
			return false;
		}
		Owner other = (Owner) obj;
		if (id != other.id)
		{
			return false;
		}
		return true;
	}
	@Override
	public String toString()
	{
		return "Owner [id=" + id + ", nazwisko=" + nazwisko + "]";
	}
	
}
