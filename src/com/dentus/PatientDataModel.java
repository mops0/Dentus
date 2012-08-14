package com.dentus;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class PatientDataModel extends ListDataModel<Pacjent> implements SelectableDataModel<Pacjent>, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//public PatientDataModel() {}

	public PatientDataModel(List<Pacjent> pacjenci)
	{
		super(pacjenci);
	}
	@Override
	public Pacjent getRowData(String rowKey)
	{
		
		 
		@SuppressWarnings("unchecked")
		
		 List<Pacjent> pacjenci = (List<Pacjent>) getWrappedData(); 
		
		for(Pacjent pacjent : pacjenci) {  
	            if(pacjent.getNazwisko().equals(rowKey))  
	                return pacjent;  
	        }  

		    return pacjenci.get(2);
		  
			
	}

	
	public Object getRowKey(Pacjent pacjent)
	{
		
		return pacjent.getNazwisko();
	}

	

}
