package com.dentus;

public class Pojazd
{
	private long id;
	private String marka;
	private Owner klient;
	private Kolor kolor;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getMarka()
	{
		return marka;
	}
	public void setMarka(String marka)
	{
		this.marka = marka;
	}
	public Owner getKlient()
	{
		return klient;
	}
	public void setKlient(Owner klient)
	{
		this.klient = klient;
	}
	public Kolor getKolor()
	{
		return kolor;
	}
	public void setKolor(Kolor kolor)
	{
		this.kolor = kolor;
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
		if (!(obj instanceof Pojazd))
		{
			return false;
		}
		Pojazd other = (Pojazd) obj;
		if (id != other.id)
		{
			return false;
		}
		return true;
	}
	@Override
	public String toString()
	{
		return "Pojazd [id=" + id + ", marka=" + marka + ", klient=" + klient
				+ ", kolor=" + kolor + "]";
	}
	
	
}
