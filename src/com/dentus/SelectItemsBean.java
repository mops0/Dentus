package com.dentus;

import java.io.IOException;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
@ManagedBean(name="selectItemsBean")
@SessionScoped
public class SelectItemsBean
{
	
	private List<SelectItem> dayOptions;
	private List<SelectItem> monthOptions;
	private List<SelectItem> yearOptions;
	private List<SelectItem> nazwiskoOptions;
	private List<SelectItem> uslugaOptions;
	private List<Pacjent> lista = new ArrayList<Pacjent>();
	private List<Usluga> listaUslug = new ArrayList<Usluga>();

	public SelectItemsBean() throws IOException
	{
		generateDays();
		generateMonths();
		generateYears();
		generateNames();
		generateUslugi();
	}
	
	

	public List<SelectItem> getUslugaOptions()
	{
		return uslugaOptions;
	}



	public void setUslugaOptions(List<SelectItem> uslugaOptions)
	{
		this.uslugaOptions = uslugaOptions;
	}



	public List<SelectItem> getNazwiskoOptions()
	{
		return nazwiskoOptions;
	}



	public void setNazwiskoOptions(List<SelectItem> nazwiskoOptions)
	{
		this.nazwiskoOptions = nazwiskoOptions;
	}



	public List<Pacjent> getLista()
	{
		return lista;
	}

	public void setLista(List<Pacjent> lista)
	{
		this.lista = lista;
	}

	public List<SelectItem> getYearOptions()
	{
		return yearOptions;
	}


	public void setYearOptions(List<SelectItem> yearOptions)
	{
		this.yearOptions = yearOptions;
	}


	public List<SelectItem> getMonthOptions()
	{
		return monthOptions;
	}


	public void setMonthOptions(List<SelectItem> monthOptions)
	{
		this.monthOptions = monthOptions;
	}
	public List<SelectItem> getDayOptions()
	{
		return dayOptions;
	}


	public void setDayOptions(List<SelectItem> dayOptions)
	{
		this.dayOptions = dayOptions;
	}


	public void generateDays()
	{
		List<SelectItem> opcje= new ArrayList<SelectItem>();
		SelectItem item = new SelectItem(1,"1");
		for (int licznik=1;licznik<32;licznik++)
		{
			item=new SelectItem(licznik,String.valueOf(licznik));
			
			opcje.add(item);
		}
		setDayOptions(opcje);
	}
	public void generateMonths()
	{
		List<SelectItem> opcje= new ArrayList<SelectItem>();
		String[] months = new String[]{"Styczneń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Październik","Listopad","Grudzień"};
		
		SelectItem item = new SelectItem(1,"1");
		for (int licznik=0;licznik<12;licznik++)
		{
			item=new SelectItem(licznik+1,months[licznik]);
			
			opcje.add(item);
		}
		setMonthOptions(opcje);
	}
	public void generateYears()
	{
		List<SelectItem> opcje= new ArrayList<SelectItem>();
		Calendar now = Calendar.getInstance();
		int rok= now.get(Calendar.YEAR);
		SelectItem item = new SelectItem(1,"1");
		for (int licznik=0;licznik<95;licznik++)
		{
			item=new SelectItem(rok-licznik,String.valueOf(rok-licznik));
			
			opcje.add(item);
		}
		setYearOptions(opcje);
	}
	public void generateNames() throws IOException
	{
		lista=new RecordService().odczytajRekordy();
		List<SelectItem> opcje= new ArrayList<SelectItem>();
		SelectItem item = new SelectItem(1,"1");
		for (int licznik=0;licznik<lista.size();licznik++)
		{
			item=new SelectItem(lista.get(licznik).getImie()+" "+lista.get(licznik).getNazwisko(),lista.get(licznik).getImie()+" "+lista.get(licznik).getNazwisko());
			opcje.add(item);
			System.out.println(lista.get(licznik).getImie()+" "+lista.get(licznik).getNazwisko());
		}
		setNazwiskoOptions(opcje);
		System.out.println("W funkcji generateNames()");
	}
	public void generateUslugi() throws IOException
	{
		listaUslug=new RecordServiceUsluga().readUslugi();
		List<SelectItem> opcje= new ArrayList<SelectItem>();
		SelectItem item = new SelectItem(1,"1");
		for (int licznik=0;licznik<listaUslug.size();licznik++)
		{
			item=new SelectItem(listaUslug.get(licznik).getNazwa(),listaUslug.get(licznik).getNazwa());
			opcje.add(item);
			
		}
		setUslugaOptions(opcje);
		
	}
}
