window.onload=akcja;
var dane;
var link = new Array("home", "register","sign in","contact");
var link_address = new Array("entry/index","entry/register","system/pacjenci","entry/contact");

function testAdresu(adresArg)
{
	var adres=String(this.location);
	adres=adres.split("/");
	adres=adres[adres.length-1];
	adres=adres.split(".");
	adres=adres[adres.length-2];
		
	var adres2=adresArg.split("/");
	
	
	
	if(adres2[1]=="pacjenci")
	adres2[1]="signin";
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
		element.setAttribute("address","/Dentus/"+link_address[licznik]+".xhtml");
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