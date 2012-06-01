package com.dentus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean(name="tableBean")
@SessionScoped

  
public class TableBean implements Serializable
{  
  
   
  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Person> people;  
      
    private Person selectedPerson;  
  
    private PersonDataModel personModel;  
  
    public TableBean() {  
       people=new ArrayList<Person>();
    	people.add(new Person("Mike","Smith"));
        people.add(new Person("Margaret","Collins"));
        personModel=new PersonDataModel(people);
    }

	public Person getSelectedPerson()
	{
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson)
	{
		this.selectedPerson = selectedPerson;
	}

	public PersonDataModel getPersonModel()
	{
		return personModel;
	}

	public void setPersonModel(PersonDataModel personModel)
	{
		this.personModel = personModel;
	}  
  
    
  
    
  
    
  
   
  
    
}  