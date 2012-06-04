package com.dentus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RecordService
{
	public void dodajRekord(Pacjent pacjent) throws IOException
	{
		 File file = new File ("/home/tomasz/work/Dentus/pacjenci.txt");
		 List<String> pacjenci=new ArrayList<String>();
		 
		 BufferedReader br = new BufferedReader(new FileReader(file));
		 String rekord=br.readLine();
		 while (rekord!=null)
		 {
			 
			 pacjenci.add(rekord);
			 rekord=br.readLine();
		 }
		 PrintWriter out = new PrintWriter(file);
		 for(int licznik=0;licznik<pacjenci.size();licznik++)
		 {
			 out.println(pacjenci.get(licznik));
		 }
		 out.println(pacjent.getNazwisko()+"***"+pacjent.getImie()+"***"+pacjent.getWiek()+"***"+pacjent.getAdres1()+"***"+pacjent.getAdres2()+"***"+pacjent.getAdres3()+"***"+pacjent.getEmail()+"***"+pacjent.getTelefon()+"***"+pacjent.getAlergie()+"***"+pacjent.getChoroby()+"***"+pacjent.getHistoria());
		 out.close();
	}
	public List<Pacjent> odczytajRekordy() throws IOException
	 {
		 File file = new File ("/home/tomasz/work/Dentus/pacjenci.txt");
		 List<Pacjent> listaP= new ArrayList<Pacjent>();
		 BufferedReader br = new BufferedReader(new FileReader(file));
		 String rekord=br.readLine();
		
		 
		 while (rekord!=null)
		 {
			 
			 StringTokenizer token=new StringTokenizer(rekord,"***");
			 Pacjent pacjent= new Pacjent();
			 pacjent.setNazwisko(token.nextToken());
			 pacjent.setImie(token.nextToken());
			 pacjent.setWiek(Integer.valueOf(token.nextToken()));
			 pacjent.setAdres1(token.nextToken());
			 pacjent.setAdres2(token.nextToken());
			 pacjent.setAdres3(token.nextToken());
			 pacjent.setEmail(token.nextToken());
			 pacjent.setTelefon(token.nextToken());
			 pacjent.setAlergie(token.nextToken());
			 pacjent.setChoroby(token.nextToken());
			 pacjent.setHistoria(token.nextToken());
			 listaP.add(pacjent);
			 rekord=br.readLine();
		 }
		 return listaP;
	 }
	public void resetujPlik(File file) throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter(file);
		out.print("");
		
	}
	public void zastapRekord(Pacjent nowyP,Pacjent staryP) throws IOException
	{
		
		List<Pacjent> lista= odczytajRekordy();
		
		File file = new File ("/home/tomasz/work/Dentus/pacjenci.txt");
		resetujPlik(file);
		for (int i=0;i<lista.size();i++)
		{
			Pacjent pacjent =lista.get(i); 
			if(pacjent.getNazwisko().equals(staryP.getNazwisko()))
			{
				dodajRekord(nowyP);
				
				
			}
			else
			dodajRekord(pacjent);
		}
		
				
	}
}