package security;


import java.io.IOException;


import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Named
@ManagedBean(name="authenticationBean")
@RequestScoped
public class AuthenticationBean
{
	
	public void doLogin() throws ServletException, IOException
	{
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher =((ServletRequest)context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest)context.getRequest(), (ServletResponse)context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		
	}
}
