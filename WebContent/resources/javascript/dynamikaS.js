window.onload=akcja;
var dane;
var link = new Array("Pacjenci", "Terminarz" , "Usługi","Ustawienia");
var link_address = new Array("pacjenci","terminarz","uslugi","ustawienia");
var guzik;
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
	
	for (var licznik=0;licznik<link.length;licznik++)
	{
		var wynik=testAdresu(link_address[licznik]);
		guzik=document.getElementById(link[licznik]);
		guzik.setAttribute("address",link_address[licznik]+".xhtml");
		if (wynik==true)
		{
			guzik.setAttribute("class", "passive");
		}
		else
		{
			guzik.onmouseover=activation;
			guzik.onmouseout=deactivation;
			guzik.onclick=linkowanie;
		}	
	}
}
activation=function activate(e)
{
	var target=e.target;
	if(target.nodeName=="LI")
	{
		target.setAttribute("class", "activeIn");
	}
};
deactivation=function deactivate(e)
{
	var target=e.target;
	var relTarget=e.relatedTarget;
	if(target.nodeName!="LI")
	return;
	
	
	while (target != relTarget && relTarget.nodeName != "BODY")
		relTarget= relTarget.parentNode;
	if (relTarget== target) return;
	target.setAttribute("class", "activeOut");
};
function deactivateAll()
{
	for (var licznik=0;licznik<link.length;licznik++)
	{
		var element=document.getElementById(link[licznik]);
		if(element.getAttribute("class")!="passive")
		{
			deactivate(element);
		}	
	}
}

linkowanie=function link(e)
{
	var target=e.target;
	
		while (target.nodeName!="LI" )
		target=target.parentNode;
			
	document.location=target.getAttribute("address");
};