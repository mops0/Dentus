package com.dentus;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter(value="uslugaConverter")

public class UslugaConverter implements Converter
{

	
	RecordServiceUsluga rsu= new RecordServiceUsluga();
	
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		Map<Long,Usluga> mapa=rsu.generujMape();
		
		
		
		return mapa.get(new Long(value));
	}

	
	public String getAsString(FacesContext context, UIComponent component, Object object)
	{
		
		
		return Long.toString(((Usluga)object).getId());
	}

}
