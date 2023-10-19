$(document).ready(function () {

    let datatable = $('#mytable').DataTable({
        "ajax": {

            "url": "../Controller?ACTION=RESERVA.FIND_ALL",

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
            {"data": "CVV"},

            {"defaultContent": "<button type='button' id='idEditar' class='form btn btn-primary btn-xs ' data-toggle='modal' data-target='#updateModal' > <span class='glyphicon glyphicon-search'></span></button>"},
            {"defaultContent": "<button type='button' id='idborrar' class='form btn btn-primary btn-xs ' > <span class='glyphicon glyphicon-remove'></span></button>"}
        ]



    });


    $('#add_button').click(function () {
        $('#reserva_form')[0].reset();
        $('.modal-title').text("Nueva reserva");
        $('#action').val("Inserta Nueva reserva");
        $('#operacion').val("Add");
        $('#reserva_uploaded_image').html('');
    });



//Metodo que abre modal para editar y lo rellena

    $("#mytable tbody").on("click", "#idEditar", function () {

        //Identifica boton dinamicamente
        var data = datatable.row($(this).parents("tr")).data();
        var ID_Reserva_ = $("#ID_Reserva").val(data.ID_Reserva),
                Fecha_Reserva_ = $("#Fecha_Reserva").val(data.Fecha_Reserva),
                NombreyApellidosHuesped_ = $('#NombreyApellidosHuesped').val(data.NombreyApellidosHuesped),
                ID_Usuario_ = $('#ID_Usuario').val(data.ID_Usuario),
                Email_ = $('#Email').val(data.Email),
                ID_Hotel_ = $('#ID_Hotel').val(data.ID_Hotel),
                Nombre_hotel_ = $('#Nombre_hotel').val(data.Nombre_hotel),
                Fecha_Entrada_ = $('#Fecha_Entrada').val(data.Fecha_Entrada),
                Ubicacion_ = $('#Ubicacion').val(data.Ubicacion),
                Duracion_ = $('#Duracion').val(data.Duracion),
                PrecioEstancia_ = $('#PrecioEstancia').val(data.PrecioEstancia),
                TitularTarjeta_ = $('#TitularTarjeta').val(data.TitularTarjeta),
                NumeroTarjeta_ = $('#NumeroTarjeta').val(data.NumeroTarjeta),
                VencimientoTarjeta_ = $('#VencimientoTarjeta').val(data.VencimientoTarjeta),
                CVV_ = $('#CVV').val(data.CVV);


    });




    //Metodo que abre modal para borrar registro
    $("#mytable tbody").on("click", "#idborrar", function () {
        alert("vas a borrar un registro");
        //Busca dentro de la matriz del datatable el valor del id
        let data = datatable.row($(this).parents()).data();
        console.log(data);
        //var idr = data.id;
        var idr = data.ID_Reserva;
        console.log(idr);
        window.location.href = "../Controller?ACTION=RESERVA.DELETE&ID=" + idr;
        datatable.ajax.reload(null, false);

    });


});


//He utilizado notación javascript en lugar de jquery para la insercion del registro
function registroReserva() {

    var host = "../Controller";
    host += "?ACTION=RESERVA.ADDADMIN";
    host += "&FECHA_RESERVA=" + document.getElementById("nFecha_Reserva").value;
    host += "&NOMBREYAPELLIDOSHUESPED=" + document.getElementById("nNombreyApellidosHuesped").value;
    host += "&ID_USUARIO=" + document.getElementById("nID_Usuario").value;
    host += "&EMAIL=" + document.getElementById("nEmail").value;
    host += "&ID_HOTEL=" + document.getElementById("nID_Hotel").value;
    host += "&NOMBRE_HOTEL=" + document.getElementById("nNombre_hotel").value;
    host += "&FECHA_ENTRADA=" + document.getElementById("nFecha_Entrada").value;
    host += "&UBICACION=" + document.getElementById("nUbicacion").value;
    host += "&DURACION=" + document.getElementById("nDuracion").value;
    host += "&PRECIOESTANCIA=" + document.getElementById("nPrecioEstancia").value;
    host += "&TITULARTARJETA=" + document.getElementById("nTitularTarjeta").value;
    host += "&NUMEROTARJETA=" + document.getElementById("nNumeroTarjeta").value;
    host += "&VENCIMIENTOTARJETA=" + document.getElementById("nVencimientoTarjeta").value;
    host += "&CVV=" + document.getElementById("nCVV").value;


    document.location = host;
}

//He utilizado notacion javascript en lugar de jquery para la edicion del registro
function editarReserva() {

    var host = "../Controller";
    host += "?ACTION=RESERVA.EDIT";
    host += "&ID_RESERVA=" + document.getElementById("ID_Reserva").value;
    host += "&FECHA_RESERVA=" + document.getElementById("Fecha_Reserva").value;
    host += "&NOMBREYAPELLIDOSHUESPED=" + document.getElementById("NombreyApellidosHuesped").value;
    host += "&ID_USUARIO=" + document.getElementById("ID_Usuario").value;
    host += "&EMAIL=" + document.getElementById("Email").value;
    host += "&ID_HOTEL=" + document.getElementById("ID_Hotel").value;
    host += "&NOMBRE_HOTEL=" + document.getElementById("Nombre_hotel").value;
    host += "&FECHA_ENTRADA=" + document.getElementById("Fecha_Entrada").value;
    host += "&UBICACION=" + document.getElementById("Ubicacion").value;
    host += "&DURACION=" + document.getElementById("Duracion").value;
    host += "&PRECIOESTANCIA=" + document.getElementById("PrecioEstancia").value;
    host += "&TITULARTARJETA=" + document.getElementById("TitularTarjeta").value;
    host += "&NUMEROTARJETA=" + document.getElementById("NumeroTarjeta").value;
    host += "&VENCIMIENTOTARJETA=" + document.getElementById("VencimientoTarjeta").value;
    host += "&CVV=" + document.getElementById("CVV").value;

    document.location = host;
}

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
