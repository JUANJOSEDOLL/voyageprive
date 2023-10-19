

$(document).ready(function () {
    
    let id = parseInt(localStorage.getItem("IDUSER"));

    let datatable = $('#mytable').DataTable({
        "ajax": {

            "url": "../Controller?ACTION=RESERVA.FIND_ALL_USER&ID_USER="+id,

            "dataSrc": ""
        },
        "columns": [
            {"data": "ID_Reserva"}, //columna 0
            {"data": "Fecha_Reserva"},
            {"data": "NombreyApellidosHuesped"},
            {"data": "ID_Usuario"},
            {"data": "Email"},
            {"data": "ID_Hotel"},
            {"data": "Nombre_hotel"},
            {"data": "Fecha_Entrada"},
            {"data": "Ubicacion"},
            {"data": "Duracion"},
            {"data": "PrecioEstancia"},
            {"data": "TitularTarjeta"},
            {"data": "NumeroTarjeta"},
            {"data": "VencimientoTarjeta"},
            {"data": "CVV"}

        ]



    });


       
        

});
//to do
 function llenarDatosUsuario() {

    const email = localStorage.getItem("EMAILUSER");
    $("#emailuser").text(email);
    

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




