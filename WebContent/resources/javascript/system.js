window.onload=akcja;
var dane;
var link = new Array("patients", "calendar","service","logout");
var link_address = new Array("system/pacjenci","system/terminarz","system/uslugi","system/logout");

function testAdresu(adresArg)
{
	var adres=String(this.location);
	adres=adres.split("/");
	adres=adres[adres.length-1];
	adres=adres.split(".");
	adres=adres[adres.length-2];	
	var adres2=adresArg.split("/");
	if(adres==adres2[1])
		return true;
	else
		return false;
}
function akcja()
{
	
	var node =document.getElementById("nav_szereg");
	
	
	for (var licznik=0;licznik<link.length;licznik++)
	{
		var wynik=testAdresu(link_address[licznik]);
		var txtNode = document.createTextNode(link[licznik]+"\n "); 
		var element = document.createElement('a');
		var elementLi=document.createElement('li');
		if(licznik<3)
			element.setAttribute("address","/"+link_address[licznik]+".xhtml");
		else
			element.setAttribute("address","/"+link_address[licznik]);	
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
		node.appendChild(elementLi);
		elementLi.appendChild(element);
		element.appendChild(txtNode);
		
	}
	
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