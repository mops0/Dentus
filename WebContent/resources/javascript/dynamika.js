window.onload=akcja;
var dane;
var link = new Array("home", "o Systemie" , "zarejestruj Sie","logowanie","kontakt");
var link_address = new Array("index","oSystemie","rejestracja","logowanie","kontakt");

function testAdresu(adresArg)
{
	var adres=String(this.location);
	adres=adres.split("/");
	adres=adres[adres.length-1];
	adres=adres.split(".");
	adres=adres[adres.length-2];
		
	if(adres==adresArg)
		return true;
	else
		return false;
}
function akcja()
{
	
	var node =document.getElementById("menu");
	var guzik=document.getElementById("forma:potwierdz");
	
	for (var licznik=0;licznik<link.length;licznik++)
	{
		var wynik=testAdresu(link_address[licznik]);
		var txtNode = document.createTextNode(link[licznik]+"\n"); 
		var element = document.createElement('p');
		
		element.setAttribute("address",link_address[licznik]+".xhtml");
		if (wynik==true)
		{
			element.setAttribute("class", "passive");
		}
		else
		{
			element.setAttribute("class", "activeOut");
			element.onmouseover=aktywacja;
			element.onmouseout=deaktywacja;
			element.onclick=linkowanie;
		}
		node.appendChild(element);
		element.appendChild(txtNode);
		
	}
	guzik.disabled=true;
}
aktywacja=function activate(e)
{
	var element=e.target;
	element.setAttribute("class", "activeIn");
};
deaktywacja=function deactivate(e)
{
	var element=e.target;
	element.setAttribute("class", "activeOut");
};
linkowanie=function link(e)
{
	var element=e.target;
	document.location=element.getAttribute("address");
};
function singleEmptyTestNoInfo(inputElement)
{
	
	if(inputElement.value=="")
	{
		return false;
	}
	else
	{
		
		return true;
	}
}
function singleEmptyTest(inputElement,outputElement)
{
	
	if(inputElement.value=="")
	{
		outputElement.textContent="To pole nie może być puste!";
	}
	else
	{
		outputElement.textContent="";
	}
	var tablica = new Array("imie","nazwisko","nick","haslo","hasloConf");
	finalEmptyTest(tablica);
}
function singleEmptyTestLogowanie(inputElement,outputElement)
{
	
	if(inputElement.value=="")
	{
		outputElement.textContent="To pole nie może być puste!";
	}
	else
	{
		outputElement.textContent="";
	}
	var tablica = new Array("nick","haslo");
	finalEmptyTest(tablica);
}
function finalEmptyTest(tablica)
{
	
	
	var ifnotempty=true;
	var ifnotempty1=true;

	var przycisk=document.getElementById("forma:potwierdz");
	
	for(var licznik=0;licznik<tablica.length;licznik++)
	{
	
		var inputElement=document.getElementById("forma:"+tablica[licznik]);
		
		
		ifnotempty=singleEmptyTestNoInfo(inputElement);
		ifnotempty1=ifnotempty1 && ifnotempty; //&& comparePasswords();
		
	}
	
	if (ifnotempty1)
		
		{
		
		przycisk.disabled=false;
		}
		else	
		{
			
			przycisk.disabled=true;
		}
		
}
function getElement(form,nazwa)
{
	return document.getElementById(form.id+":"+nazwa);
}
function comparePasswords(inputElement,outputElement)
{
	singleEmptyTest(inputElement,outputElement);
	var haslo=document.getElementById("forma:haslo").value;
	var hasloConf=document.getElementById("forma:hasloConf").value;
	
	if(haslo==hasloConf)	
		return true;
	else
		{
		if(hasloConf!="")
		alert("Popraw haslo");
		return false;
		}
}



