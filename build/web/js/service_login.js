
function validacion(a){

var dato1=document.getElementById("EMAIL").value;
var dato2=document.getElementById("PASS").value;

if(dato1.length==0||dato1.length==null||/^\s+$/.test(dato1)||dato2.length==0||dato2.length==null||/^\s+$/.test(dato2)){

    //return false;                                                              
    
    alert("Falta cumplimentar dato");

}
else{

if(a===1){
    //return true;
	loginEntrar();
        }else{
        loginRegistro();    
        }
}
}





function loginEntrar() {

    var emailUser = document.getElementById("EMAIL").value;
    localStorage.setItem("EMAILUSER", emailUser);

    var host = "./Controller";
    host += "?ACTION=USUARIO.LOGIN";
    host += "&EMAIL=" + document.getElementById("EMAIL").value;
    host += "&PASS=" + document.getElementById("PASS").value;
    document.location = host;
}

function loginRegistro() {
    //alert("Estoy llegando a Login para registro");
    var host = "./Controller";
    host += "?ACTION=USUARIO.ADD";
    host += "&EMAIL=" + document.getElementById("EMAIL").value;
    host += "&PASS=" + document.getElementById("PASS").value;
    document.location = host;

}

