package com.dentus;
import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;



public class GOIDataModel extends ListDataModel<GOI> implements SelectableDataModel<GOI>, Serializable
{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GOIDataModel(List<GOI> list)
	{
		super(list);
		// TODO Auto-generated constructor stub
	}
@Override
	public GOI getRowData(String rowKey)
	{
	GOIService gs = new GOIService();
	  
            return gs.findById(Long.parseLong(rowKey));  
    

    
	}
	@Override
	public Object getRowKey(GOI goi)
	{
		
		return Long.toString(goi.getId());
	}

	
	
}
