package com.dentus;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class UslugaDataModel extends ListDataModel<Usluga> implements SelectableDataModel<Usluga> {    
	  
    public UslugaDataModel() {  
    }  
  
    public UslugaDataModel(List<Usluga> data) {  
        super(data);  
    }  
      
    @Override  
    public Usluga getRowData(String rowKey) {  
        
          
        @SuppressWarnings("unchecked")
		List<Usluga> uslugi = (List<Usluga>) getWrappedData();  
          
        for(Usluga usluga : uslugi) {  
            if(usluga.getNazwa().equals(rowKey))  
                return usluga;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Usluga usluga) {  
        return usluga.getNazwa();  
    }

	
}  