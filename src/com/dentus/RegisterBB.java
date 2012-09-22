package com.dentus;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;

import security.Authorities;
import security.PillDao;
import security.PillUser2;
@ManagedBean(name="registerBB")
@SessionScoped
public class RegisterBB
{
	
	private PillUser2 puser= new PillUser2();
	private String passwordCompare=new String();
	public RegisterBB()
	{
		
	}
	
	public String getPasswordCompare()
	{
		return passwordCompare;
	}

	public void setPasswordCompare(String passwordCompare)
	{
		this.passwordCompare = passwordCompare;
	}

	public PillUser2 getPuser()
	{
		return puser;
	}
	public void setPuser(PillUser2 puser)
	{
		this.puser = puser;
	}
	public void commit()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Authorities auth= new Authorities();
		auth.setAuthority("POOOO");
		puser.addAuthority(auth);
		PillDao pilldao = new PillDao();
		pilldao.dodajPillUsera(puser);
		puser=new PillUser2();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, "/system/pacjenci"+"?faces-redirect=true");
	}
	public void validatePasswordEquality(FacesContext context,UIComponent component, Object data)
	{
		UIViewRoot view = context.getViewRoot();
		HtmlInputSecret password = (HtmlInputSecret) view.findComponent("registerForm:passwordRegister");
		HtmlInputSecret passwordConfirm=(HtmlInputSecret)component;
		String passwordVal=(String)password.getValue();
		String passwordConfVal=(String)data;
		System.out.println("Wartośc: "+passwordVal+" i wartoć passwordConf "+passwordConfVal);
		if(!passwordVal.equals(passwordConfVal))
		
		{
			passwordConfirm.setValid(false);
			FacesMessage message = new FacesMessage("Password should be the same like password confirmation!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(component.getClientId(context), message);
		
		}
	}
}
