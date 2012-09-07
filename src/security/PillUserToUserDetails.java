package security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PillUserToUserDetails implements UserDetails
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PillUser2 pillUser;
	PillUserToUserDetails(PillUser2 pillUser)
	{
		this.pillUser=pillUser;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		
		return pillUser.getAuthorityList();
	}

	@Override
	public String getPassword()
	{
		
		return pillUser.getPassword();
	}

	@Override
	public String getUsername()
	{
		
		return pillUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}
	
}
