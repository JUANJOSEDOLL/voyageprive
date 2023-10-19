
//document.querySelector('#botonFecha').addEventListener('click', traerDatos());//es automatico

//llamamos a las funciones que llenan el select de ciudades y el cards de los hoteles

//Verifico que en localStorage se haya guardado el email
//ya que si está correctamente logueado ha de tener la variable en memoria

const email = localStorage.getItem("EMAILUSER");

//ponemos a prueba el registro de usuario, para que no se pueda acceder escribiendo
//la dirección URL de index. Todo lo que pasa por controller, se encarga el filtro

if (email !== null) {
    var llamadaaController = null;
    window.onload = cargarFunciones;
} else {
    document.location = "../login.html";

}
;
function cargarFunciones() {
    llenarDatosUsuario();
    llenarSelect();
    traerDatos(llamadaaController);


}

//document.querySelector('#botonCiudad').addEventListener('click', traerDatosFiltradosPorCiudad); //responde a comando
//document.querySelector('#botonFecha').addEventListener('click', traerDatosFiltradosPorFecha);//Responde a comando



function llenarSelect() {

    const xhttpU = new XMLHttpRequest();
    xhttpU.open('GET', '../Controller?ACTION=HOTEL.FIND_ALL_ACTIVO', true); //desde el controller
    xhttpU.send();
    xhttpU.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let datos = JSON.parse(this.responseText);
            for (let item of datos) {
                var option = document.createElement("option");
                option.text = item.Ubicacion;
                option.value = item.Ubicacion;
                var select = document.getElementById("selectorCiudadU");

                select.appendChild(option);
            }

        }
    };
}

function traerDatos(llamadaaController) {

    if (llamadaaController === null) {

        var host = "../Controller?ACTION=HOTEL.FIND_ALL_ACTIVO";

    } else {

        var host = llamadaaController;
    }
    ;

    const xhttp = new XMLHttpRequest();
    xhttp.open('GET', host, true); //desde el controller

    //xhttp.open('GET', 'json/catalogo.json', true); //desde un archivo
    xhttp.send();
    xhttp.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            let datos = JSON.parse(this.responseText);
            //llenamos las cards con los datos de los hoteles
            let res = document.querySelector('#res');
            res.innerHTML = '';
            for (let item of datos) {
                res.innerHTML += `<div class="card">
                                <img class="card__image card__image" src="../images/${item.URL_Imagen}"/>
                                <div class="card__content">
                                <div id="nombre" class="card__title"><h1>${item.Nombre_hotel}</h1></div>
                                <h6  id="descripcion" class="card__text"><b>${item.Descripcion}</b></h6>
                                <p  id="ubicacion" class="card__text"><span>Ciudad: </span>${item.Ubicacion}</p>
                                <p  id="fecha" class="card__text">Fecha de entrada: ${item.Fecha_Entrada}</p>
                                <p  id="duracion" class="card__text">Días: ${item.duracion}</p>
                                <p  id="precio" class="card__text"> Euros: ${item.PrecioEstancia}</p>
                     <div class="box">
                                <button class="btn btn--block card__btn" id='${item.ID_Hotel}'  value='${item.ID_Hotel}'  onclick='enviaOfertaSeleccionada(${item.ID_Hotel})'>RESERVAR</button>
                          </div>  </div>
                <br/>
                          </div>`; //comilla junto ^, es el acento abierto

                //otra manera de hacerlo sin las comillas raras
                //<img src="' + photo.url + '" class="frame" onclick="document.location=this.src">
            }

        }

    };
}

function traerDatosFiltradosPorCiudad() {
    var ciudad = "../Controller?ACTION=HOTEL.FINDBYCITY";

    ciudad += "&UBICACION=" + document.getElementById("selectorCiudadU").value;

    traerDatos(ciudad);


}

function traerDatosFiltradosPorFecha() {

    var fecha = "../Controller?ACTION=HOTEL.FINDBYDATE";

    fecha += "&FECHA_ENTRADA=" + document.getElementById("fechaEntrada").value;

    traerDatos(fecha);
}
;

//recoge los datos de usuario
function llenarDatosUsuario() {
    //por localStorage podemos recoger el email del user, ya que 
    //viene de la tecnología de javascript
    const email = localStorage.getItem("EMAILUSER");

    $("#emailuser").text(email);
    var txtemail = "Hola " + email + " click aquí para acceder a tus reservas!";
    $("#suEmail").text(txtemail);
    //Para obtener el id de user hay que obtenerlo desde java
    //java lo reenvia por parametro y se recoge desde JS, ahora si esta disponible
    //mediante localStorage

    var params = new URLSearchParams(location.search);
    var parametro = params.get('IDUSER');
    $("#idUserINPUT").val(parametro);
    localStorage.setItem("IDUSER", parametro);

    //https://developer.mozilla.org/es/docs/Web/API/Window/localStorage


}
;


//recoge la oferta seleccionada, los datos del cliente los gestiona en el backend via sesion
function enviaOfertaSeleccionada(ID) {
    //var idUser = document.getElementById("hidden").value;
    localStorage.setItem("IDH", +ID);
    var host = "../user/seleccion.html";
    window.open(host, "_self");
}
;

function iraDatosdeCliente() {

    document.location = "../user/listadoReservasUser.html";
}
;


function cerrarSesion() {
    //Deja a 0 o null los parametros de localStorage 
    localStorage.removeItem("IDUSER");
    localStorage.removeItem("EMAILUSER");
    //nos dirijimos al controller para cerrar sesion y llevar al usuario a login
    var host = "../Controller";
    host += "?ACTION=USUARIO.LOGOUT";
    document.location = host;




}
;   