package security;

import java.util.ArrayList;
import java.util.List;



public class PillUser2 
{

	private long id;
	private String username;
	private String password;
	List<Authorities> authorityList= new ArrayList<Authorities>();
	public PillUser2()
	{
		
	}
	
	

	public List<Authorities> getAuthorityList()
	{
		return authorityList;
	}



	public void setAuthorityList(List<Authorities> authorityList)
	{
		this.authorityList = authorityList;
	}



	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void addAuthority(Authorities auth)
	{
		auth.setPuser(this);
		authorityList.add(auth);
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		if (!(obj instanceof PillUser2))
		{
			return false;
		}
		PillUser2 other = (PillUser2) obj;
		if (password == null)
		{
			if (other.password != null)
			{
				return false;
			}
		} else if (!password.equals(other.password))
		{
			return false;
		}
		if (username == null)
		{
			if (other.username != null)
			{
				return false;
			}
		} else if (!username.equals(other.username))
		{
			return false;
		}
		return true;
	}
	
}
