window.onload=init;
var pamiec="pusta";

function konstruktor(adres,guzik)
{
	this.adres=adres;
	this.guzik=guzik;
}		
function init()
	
	{
		alert(pamiec);
		var str1=new konstruktor("/home/tomasz/Dentus/about/index.html","home");
		var str2=new konstruktor("/home/tomasz/Dentus/about/kontakt.html","kontakt");
		var tab = new Array();
		tab[0]=str1.guzik;
		tab[1]="oSystemie";
		tab[2]="zarejestrujSie";
		tab[3]="logowanie";
		tab[4]=str2.guzik;
		
		for(var i=0;i<tab.length;i++)
		{
			
			document.getElementById(tab[i]).onmouseover=function(evt){ overMouse(evt.currentTarget.getAttribute("id"));};
			document.getElementById(tab[i]).onmouseout=function(evt){ outMouse(evt.currentTarget.getAttribute("id"));};
		}
		document.getElementById(tab[0]).onclick=function(evt){window.location=str1.adres;pamiec;};	
		document.getElementById(tab[1]).onclick=function(evt){window.location="/home/tomasz/Dentus/about/index.html";};	
		document.getElementById(tab[2]).onclick=function(evt){window.location="/home/tomasz/Dentus/about/index.html";};	
		document.getElementById(tab[3]).onclick=function(evt){window.location="/home/tomasz/Dentus/about/index.html";};	
		document.getElementById(tab[4]).onclick=function(){window.location=str2.adres;pamiec="bla";alert(pamiec);};	
		
	}	
function outMouse(id)
{
	var g=document.getElementById(id);	
	g.className="menuFonts";
	
}	
function overMouse(id)
{
	var g=document.getElementById(id);	
	g.className="menuFlash";
}

