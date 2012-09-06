package security;

import java.io.Serializable;

public class Authorities implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String authority;
	private PillUser2 puser;
	public Authorities()
	{
		
	}
	
	public PillUser2 getPuser()
	{
		return puser;
	}

	public void setPuser(PillUser2 puser)
	{
		this.puser = puser;
	}

	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getAuthority()
	{
		return authority;
	}
	public void setAuthority(String authority)
	{
		this.authority = authority;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
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
		if (!(obj instanceof Authorities))
		{
			return false;
		}
		Authorities other = (Authorities) obj;
		if (authority == null)
		{
			if (other.authority != null)
			{
				return false;
			}
		} else if (!authority.equals(other.authority))
		{
			return false;
		}
		if (id != other.id)
		{
			return false;
		}
		return true;
	}
	
}
