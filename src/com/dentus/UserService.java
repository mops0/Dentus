package com.dentus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserService
{
	File file;
	UserService(File file)
	{
		this.file=file;
	}
	public void wstawRekord(User user) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		List<User> lista =pobierzListe(file);
		lista.add(user);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(lista);
	}
	public User pobierzRekord(String nick,String haslo) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		List<User> lista =pobierzListe(file);
		for (int licznik=0;licznik<lista.size();licznik++ )
		{
			User user= lista.get(licznik);
			if(nick.equals(user.getNick())&&haslo.equals(user.getHaslo()))
			return user;
			
		}
		
		return null;
		
	}
	public List<User> pobierzListe(File plik) throws FileNotFoundException, IOException, ClassNotFoundException
	{
		if (plik.exists())
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(plik));
			@SuppressWarnings("unchecked")
			List<User> lista=(List<User>)in.readObject();
			return lista;
		}
		else
		return new ArrayList<User>();
		
	}
	
	
}
