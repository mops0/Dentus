package com.dentus;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;


public class PersonDataModel extends ListDataModel<Person> implements SelectableDataModel<Person> {    
	  
    public PersonDataModel() {  
    }  
  
    public PersonDataModel(List<Person> data) {  
        super(data);  
    }  
      
    @Override  
    public Person getRowData(String rowKey) {  
        
          
        @SuppressWarnings("unchecked")
		List<Person> people = (List<Person>) getWrappedData();  
          
        for(Person person : people) {  
            if(person.getSurname().equals(rowKey))  
                return person;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Person person) {  
        return person.getSurname();  
    }  
}  