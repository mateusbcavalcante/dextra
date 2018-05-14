/**
 *  @author Mateus Bastos
 *  @since 26/11/2013
 */

/**
 * Funcao utilizada para deixar caracteres maiusculos
 * 
 * @param campo
 */

function upper(campo)
{
	var str = campo.value;
	campo.value = str.toUpperCase();
}


function soNumero(evt, campo)
{
	if (navigator.appCodeName == 'Mozilla' && (navigator.appName == 'Netscape' || navigator.appName == 'Opera'))
	{
		if (evt.which)
		{
			if ((evt.which < 48 || evt.which > 57) && evt.which != 8 && evt.which != 9)
			{
				if(navigator.appName == 'Opera') // Opera
				{
					if((evt.which == 86 || evt.which == 67) && evt.ctrlKey) // Ctrl + V ou Ctrl + C
					{
						return true;
					}
				}
				else // Firefox, Chrome, Safari
				{
					if((evt.which == 118 || evt.which == 99) && evt.ctrlKey) // Ctrl + V ou Ctrl + C
					{
						return true;
					}
				}	
				return false;
			}
		}
	}
	else // IE
	{
		if (evt.keyCode < 48 || evt.keyCode > 57)
		{
			return false;
		}
	}
	
	return true;
}

function validarEmail(campo)
{
	if(campo.value == "")
	{
		return;
	}
	
	var usuario = campo.value.substring(0, campo.value.indexOf("@"));
	var dominio = campo.value.substring(campo.value.indexOf("@") + 1, campo.value.length);
	
	if ((usuario.length >= 1) && (dominio.length >= 3)
			&& (usuario.search("@") == -1) && (dominio.search("@") == -1)
			&& (usuario.search(" ") == -1) && (dominio.search(" ") == -1)
			&& (dominio.search(".") != -1) && (dominio.indexOf(".") >= 1)
			&& (dominio.lastIndexOf(".") < dominio.length - 1))
	{
		return true;
	} 
	else
	{
		alert("Favor digitar um e-mail válido!");
		campo.value = "";
	}
}

function validarCPF(cpf)
{
	cpf = cpf.value.replace(/[^\d]+/g,'');

	if(cpf == '') return false; 
    
	
	// Elimina CPFs invalidos conhecidos    
    if (cpf.length != 11 || 
        cpf == "00000000000" || 
        cpf == "11111111111" || 
        cpf == "22222222222" || 
        cpf == "33333333333" || 
        cpf == "44444444444" || 
        cpf == "55555555555" || 
        cpf == "66666666666" || 
        cpf == "77777777777" || 
        cpf == "88888888888" || 
        cpf == "99999999999")
    	{
    		alert('CPF Invalido, Favor informar um CPF válido!');
			cpf.value = "";
			return false;
    	}
    
    // Valida 1o digito 
    add = 0;    
    for (i=0; i < 9; i ++)       
        add += parseInt(cpf.charAt(i)) * (10 - i);  
        rev = 11 - (add % 11);  
        if (rev == 10 || rev == 11)     
            rev = 0;    
        if (rev != parseInt(cpf.charAt(9))) 
        {
        	alert('CPF Invalido, Favor informar um CPF válido!');
			cpf.value = "";
			return false;
        }
    
    // Valida 2o digito 
    add = 0;    
    for (i = 0; i < 10; i ++)        
        add += parseInt(cpf.charAt(i)) * (11 - i);  
    rev = 11 - (add % 11);  
    if (rev == 10 || rev == 11) 
        rev = 0;    
    if (rev != parseInt(cpf.charAt(10)))
    {
    	alert('CPF Invalido, Favor informar um CPF válido!');
		cpf.value = "";
		return false;
    }
}

 
function remove(str, sub)
{
	i = str.indexOf(sub);
	r = "";
	if (i == -1) return str;
	{
		r += str.substring(0,i) + remove(str.substring(i + sub.length), sub);
	}
	
	return r;
}

function mascara(o,f)
{
	v_obj=o
	v_fun=f
	setTimeout("execmascara()",1)
}

function execmascara()
{
	v_obj.value=v_fun(v_obj.value)
}

function cpf_mask(v)
{
	v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o terceiro e o quarto dígitos
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o setimo e o oitava dígitos
	v=v.replace(/(\d{3})(\d)/,"$1-$2")   //Coloca ponto entre o decimoprimeiro e o decimosegundo dígitos
	return v
}

function cnpj_mask(v)
{
	v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
	v=v.replace(/(\d{2})(\d)/,"$1.$2")    //Coloca ponto entre o segundo e o terceiro dígitos
	v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o setimo e o oitava dígitos
	v=v.replace(/(\d{3})(\d)/,"$1/$2")   //Coloca barra entre o decimoprimeiro e o decimosegundo dígitos
	v=v.replace(/(\d{4})(\d)/,"$1-$2")   //Coloca barra entre o decimoprimeiro e o decimosegundo dígitos
	return v
}

function mtel(v)
{
	v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
	v=v.replace(/^(\d{2})(\d)/g,"($1) $2");
	v=v.replace(/(\d)(\d{4})$/,"$1-$2");
	return v
}

function mcep(v){
    v=v.replace(/\D/g,"")                    //Remove tudo o que não é dígito
    v=v.replace(/^(\d{5})(\d)/,"$1-$2")         //Esse é tão fácil que não merece explicações
    return v
}

function mhor(v)
{
	v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito	
	v=v.replace(/(\d)(\d{2})$/,"$1:$2");
	return v
}

function formataData(evt, campo) 
{
	if (soNumero(evt, campo))
	{
		var unicode = (evt.which) ? evt.which : evt.keyCode;

		vr = campo.value;
		vr = vr.replace( ".", "" );
		vr = vr.replace( "-", "" );
		vr = vr.replace( "/", "" );
		vr = vr.replace( "/", "" );
		vr = vr.replace( "/", "" );
		tam = vr.length + 1;
		
		if (unicode >= 48 && unicode <= 57)
		{
			if (tam > 2 && tam < 5)
				campo.value = vr.substr(0, 2) + '/' + vr.substr(2, tam);
			if (tam >= 5 && tam <= 10)
				campo.value = vr.substr(0, 2) + '/' + vr.substr(2, 2) + '/' + vr.substr(4, 4); 
		}
		return true;
	}
	return false;
}

function verificaTamanho(campo, tamMax, resposta)
{
	var strLen;
	strLen = 0;
	if(campo.value.length > 0)
	{
		strLen = strLen + campo.value.length;
	}
	if (strLen == 1)
   	{
	   	if(campo.value.substring(0,1) == " ")
		{
	       	campo.value = "";
			strLen = strLen - 1;
		}
   	}
	if (strLen > tamMax)
	{
		campo.value = campo.value.substring(0,tamMax);
		strLen = strLen - 1;
		return;
	}
	if(resposta != null)
	{	
		if(document.forms['formulario'].elements[resposta] != null)
		{
			document.forms['formulario'].elements[resposta].value = (tamMax - strLen);
		}
		else
		{
			document.getElementById(resposta).innerHTML = '(' + (tamMax - strLen) + ' caracteres restantes)';
		}
	}
}

function formataMoeda(campo)
{	
	  if(campo.value != '')
	  {
		  decs=2;

		  var money = campo.value;
		  
		  if(parseFloat(money.replace(',','.')) < 0){ money = ''; }
		  
		   while(money.toString().indexOf('.') != -1)
		   {
		   	money = money.replace(".","");
		   }
		   money = money.replace(",",".");
		   if (isNaN(new Number(money)))
		   {
			   campo.value = '';
			   return;
		   }
		   money = new Number(money);
		   money = money.toFixed(decs);
		   money = money.toString().replace(".",",");
		   var number, comma, numberAux = "";
		
		   number = ( money + "" ).replace( "\.", "" );
		   
		   var zeros = "";
		   for (i=0; i<decs; i++) zeros +="0";
		   
		   number = ( number.indexOf( "," ) == -1 ? number + "," + zeros : number ); //se for numero inteiro, coloca-se as casas decimais
		   comma = number.indexOf( "," ) -1; //posicao da virgula
		
		   for ( var x = comma, count = 1; x >= 0; x--, count++ )
		   {
			   numberAux = number.substring( x, x +1 ) + numberAux;
			   if ( count == (3) ) {
				   if ( x != 0 && number.substring( x -1, x ) != "-" ) {
				       numberAux = "." + numberAux;
				       count = 0;
				   }
			   }
		   }
		
		   var comma_casas_decimais = number.substring( comma +1, number.length );
		   if (comma_casas_decimais.length == decs)
		   {
		   		comma_casas_decimais += '0';
		   }
		
		   campo.value = numberAux + comma_casas_decimais;
	   }
}


/**
 * Contador de sessao - Inicio 
 */

var tempoPassado = 0;
var funcaoTimeout;

var tempoMaximoSessao=1800000;
var tempoAtualizacao=1000;
 
window.onload=function()
{
	contarTempo();
}

function contarTempo()
{
	document.getElementById('formulario:contadorSessao').innerHTML= formataSegundos(tempoMaximoSessao / 1000 - tempoPassado);
	tempoPassado = tempoPassado + 1;
	//SE ULTRAPASSAR O TEMPO MAXIMO, REDIRECIONAR PARA PAGINA DE LOGIN
	if(tempoPassado > tempoMaximoSessao / 1000)
	{
		document.getElementById('formulario:lblExpira').innerHTML = "Sessão expirada";
		document.getElementById('formulario:contadorSessao').innerHTML = "";
		return false;
	}
   
	funcaoTimeout=setTimeout("contarTempo()", tempoAtualizacao);
}
 
function resetaContador()
{
	tempoPassado=0;
	clearTimeout(funcaoTimeout);
	contarTempo();
}

function formataSegundos(segundo)
{
	var curtime = 0.0;
	var curmin = Math.floor((segundo / 60) % 60);
	var cursec = segundo % 60;
	
	if (curmin!=0){
		if(curmin > 9)
			if(cursec > 9){
				curtime=curmin+"min "+cursec+"s";
			}
			else{						
				curtime=curmin+"min 0"+cursec+"s";
			}
		else{	
			aux = cursec+"";
			if(aux.length == 1){
				curtime="0"+curmin+"min 0"+cursec+"s";
			}else{					
				curtime="0"+curmin+"min "+cursec+"s";
			}
		}
	}
	else{
		curtime=cursec+"s";
	}			

	
	if(curtime.length == 1){
		curtime = "00min 0"+curtime;
	}else{			
		if(curtime.length == 2){
			curtime = "00min "+curtime;
		}
	}
	
	return curtime;
}

/**
 *	Contador de Sessao - Fim
 */


function setarFoco(element){	
	element.focus();
	element.value=element.value;
}
