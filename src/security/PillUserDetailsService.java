package security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public class PillUserDetailsService implements UserDetailsService
{

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		PillDao pillDao = new PillDao();
	
		
		return new PillUserToUserDetails(pillDao.findByLogin(username));
	}

}
