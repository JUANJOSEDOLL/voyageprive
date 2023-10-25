//document.querySelector('#botonFecha').addEventListener('click', traerDatos());//es automatico

//llamamos a las funciones que llenan el select de ciudades y el cards de los hoteles

window.onload = cargarFunciones;
//var nombre="";
function cargarFunciones() {
    traerDatos();
    llenarDatosUsuario();
}

function traerDatos() {

    //const IDU = localStorage.getItem("IDU");
    //const EMAILUSER=localStorage.getItem("EMAILUSER");
    const IDH = localStorage.getItem("IDH");

    const direccionURL = "../Controller?ACTION=HOTEL.SELECCION&ID_HOTEL=" + IDH;
    const xhttp = new XMLHttpRequest();
    xhttp.open('GET', direccionURL, true); //desde el controller

    xhttp.send();
    xhttp.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            let datos = JSON.parse(this.responseText);
            //llenamos las cards con los datos de los hoteles
            let res = document.querySelector('#res');
            res.innerHTML = '';
            for (let item of datos) {
                res.innerHTML += `<div class="card">
                                <img class="card-img-top" src="../images/${item.URL_Imagen}"/>
                                <div class="card-body">
                                <h3 class="card-title">${item.Nombre_hotel}</h3>
                                <p  class="card-text">${item.Descripcion}</p>
                                <p  class="card-text">Ciudad: ${item.Ubicacion}</p>
                                <p  class="card-text">Fecha check In: ${item.Fecha_Entrada}</p>
                                <p  class="card-text">Días de estancia: ${item.duracion}</p>
                                <p  class="card-text">Euros: ${item.PrecioEstancia}</p>
                                <input type="hidden" id="nombre" value="${item.Nombre_hotel}">
                                <input type="hidden" id="ubicacion" value="${item.Ubicacion}">
                                <input type="hidden" id="fecha" value="${item.Fecha_Entrada}">
                                <input type="hidden" id="duracion" value="${item.duracion}">
                                <input type="hidden" id="precio" value="${item.PrecioEstancia}">
                        </div>
                  </div>`; //comilla junto ^, es el acento abierto
                //otra manera de hacerlo sin las comillas raras
                //<img src="' + photo.url + '" class="frame" onclick="document.location=this.src">
            }

        }

    };
    var output = new Date().toLocaleDateString();
    $('#Fecha_Reserva').val(output);
}

//recoge los datos de usuario
function llenarDatosUsuario() {

    const email = localStorage.getItem("EMAILUSER");

    $("#emailuserP").text(email);
    $("#Email").val(email);

}
;


//Al igual que en la web original, en el momento de finalizar la reserva, recoge los datos del cliente así como el pago
//en la tabla RESERVA, se recogen los datos del usuario, el cliente que ira al hotel, y los de la oferta.
//MOTIVO: no necesariamente usuario y huesped sean el mismo, ni los datos de la tarjeta, el código de oferta sí que
//es el mismo al ser un identificador unico, aunque por simplificar posteriores consultas, cargo en la misma tabla y duplico informacion

function enviaOfertaSeleccionada() {


    var host = "../Controller";
    host += "?ACTION=RESERVA.ADD";
//Estos datos los recoge de la sesion, localStorage, calculado en el formulario 
    host += "&ID_USUARIO=" + localStorage.getItem("IDUSER");;
    host += "&EMAIL=" + document.getElementById("Email").value;
    host += "&FECHA_RESERVA=" + document.getElementById("Fecha_Reserva").value;
    host += "&IDH=" + localStorage.getItem("IDH");//este id es el del hotel
    
//Estos datos los toma del formulario dinamico nombre

    host += "&NOMBRE=" + document.getElementById("nombre").value;
    host += "&FECHA=" + document.getElementById("fecha").value;
    host += "&UBICACION=" + document.getElementById("ubicacion").value;
    host += "&DURACION=" + document.getElementById("duracion").value;
    host += "&PRECIO=" + document.getElementById("precio").value;
    
//Estos datos los recoge del formulario a cumplimentar por el cliente
    host += "&NOMBREYAPELLIDOSHUESPED=" + document.getElementById("NombreyApellidosHuesped").value;
    host += "&TITULARTARJETA=" + document.getElementById("TitularTarjeta").value;
    host += "&NUMEROTARJETA=" + document.getElementById("NumeroTarjeta").value;
    host += "&VENCIMIENTOTARJETA=" + document.getElementById("VencimientoTarjeta").value;
    host += "&CVV=" + document.getElementById("CVV").value;
    
//enviamos los datos al controller para que lleve a cabo el registro de la reserva con los datos

    document.location = host;


}
;

    