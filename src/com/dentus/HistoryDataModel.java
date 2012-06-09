package com.dentus;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class HistoryDataModel extends ListDataModel<HistoriaWpis> implements SelectableDataModel<HistoriaWpis>, Serializable
{




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	
	public HistoryDataModel(List<HistoriaWpis> wpisy)
	{
		super(wpisy);
	}
	@Override
	public HistoriaWpis getRowData(String rowKey)
	{
		
		 
		@SuppressWarnings("unchecked")
		
		 List<HistoriaWpis> wpisy = (List<HistoriaWpis>) getWrappedData(); 
		
		for(HistoriaWpis wpis : wpisy) {  
	            if(wpis.getId()==Long.parseLong(rowKey))  
	                return wpis;  
	        }  

		    return wpisy.get(2);
		  
			
	}

	@Override
	public Object getRowKey(HistoriaWpis wpis)
	{
		
		return wpis.getLeczenie();
	}

	

}
