package com.dentus;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter(value="pacjentConverter")

public class PacjentConverter implements Converter
{

	
	RecordService rs= new RecordService();
	
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		Map<Long,Pacjent> mapa=rs.generujMape();
		
		
		return mapa.get(new Long(value));
	}

	
	public String getAsString(FacesContext context, UIComponent component, Object object)
	{
		
		
		return Long.toString(((Pacjent)object).getId());
	}

}
