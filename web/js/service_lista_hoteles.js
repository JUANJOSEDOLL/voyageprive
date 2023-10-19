$(document).ready(function () {

    let datatable = $('#mytable').DataTable({
        "ajax": {

            "url": "../Controller?ACTION=HOTEL.FIND_ALL",
            "dataSrc": ""
        },
        "columns": [
            {"data": "ID_Hotel"}, //columna 0
            {"data": "Nombre_hotel"},
            {"data": "Ubicacion"},
            {"data": "Descripcion"},
            {"data": "Fecha_Entrada"},
            {"data": "URL_Imagen"},
            {"data": "duracion"},
            {"data": "PrecioEstancia"},
            {"data": "Activo"},
            {"defaultContent": "<button type='button' id='idFoto' class='form btn btn-primary btn-xs ' data-toggle='modal' data-target='#fotoModal' > <span class='glyphicon glyphicon-camera'></span></button>"},
            {"defaultContent": "<button type='button' id='idEditar' class='form btn btn-primary btn-xs ' data-toggle='modal' data-target='#updateModal' > <span class='glyphicon glyphicon-search'></span></button>"},
            {"defaultContent": "<button type='button' id='idborrar' class='form btn btn-primary btn-xs ' > <span class='glyphicon glyphicon-remove'></span></button>"}
        ]



    });


    $('#add_button').click(function () {
        $('#user_form')[0].reset();
        $('.modal-title').text("Nueva oferta de hotel");
        $('#action').val("Inserta nueva oferta");
        $('#operacion').val("Add");
        $('#user_uploaded_image').html('');
    });



//Metodo que abre modal para editar y lo rellena

    $("#mytable tbody").on("click", "#idEditar", function () {

        //Identifica boton dinamicamente
        var data = datatable.row($(this).parents("tr")).data();
        var id = $("#id_hotel").val(data.ID_Hotel),
                nombre_ = $("#nombre").val(data.Nombre_hotel),
                ubicacion_ = $('#ubicacion').val(data.Ubicacion),
                descripcion_ = $("#descripcion").val(data.Descripcion),
                fecha_entrada_ = $("#fecha_entrada").val(data.Fecha_Entrada),
                duracion_ = $("#duracion").val(data.duracion),
                precio_ = $("#precio").val(data.PrecioEstancia),
                imagen_ = $("#cambiaImagen").val(data.URL_Imagen);
        activo_ = $("#activo").val(data.Activo);


    });

    //Metodo que abre modal para subir imagen

    $("#mytable tbody").on("click", "#idFoto", function () {

        //Identifica boton dinamicamente
        var data = datatable.row($(this).parents("tr")).data();
        var imagen_F = data.URL_Imagen;

        $("#cambiaImagenF").val(imagen_F);


    });

    //TO DO: PENDIENTE QUE PUEDA LLAMAR A SERVLET Y ENTRAR EL PARAMETRO NOMBRE IMAGEN
    function registroImagen() {
        window.location.href = "../ImageServlet";
    }


    //Metodo que abre modal para borrar registro
    $("#mytable tbody").on("click", "#idborrar", function () {
        alert("vas a borrar un registro");
        //Busca dentro de la matriz del datatable el valor del id
        let data = datatable.row($(this).parents()).data();
        //console.log(data);
        var idhotel = data.ID_Hotel;
        window.location.href = "../Controller?ACTION=HOTEL.DELETE&ID=" + idhotel;
        datatable.ajax.reload(null, false);

    });


});


//He utilizado notación javascript en lugar de jquery para la insercion del registro
function registroHotel() {

    var host = "../Controller";
    host += "?ACTION=HOTEL.ADD";
    host += "&NOMBRE_HOTEL=" + document.getElementById("nuevo_nombre").value;
    host += "&UBICACION=" + document.getElementById("nuevo_ubicacion").value;
    host += "&DESCRIPCION=" + document.getElementById("nuevo_descripcion").value;
    host += "&FECHA_ENTRADA=" + document.getElementById("nuevo_fecha_entrada").value;
    host += "&URL_IMAGEN=" + Date.now().toString() + '.jpg';
    host += "&DURACION=" + document.getElementById("nuevo_duracion").value;
    host += "&PRECIOESTANCIA=" + document.getElementById("nuevo_precio").value;
    //*****************determinar estado check********************************
    var nvalor = false;
    var ndato = document.getElementById("nuevo_activo");
    if (!ndato.checked) {var nvalor = false;} else {var nvalor = true;}
    host += "&ACTIVO=" + nvalor;
  //**********************************************************************  

    document.location = host;
}

//He utilizado notación javascript en lugar de jquery para la edicion del registro
function editarHotel() {
    
    var host = "../Controller";
    host += "?ACTION=HOTEL.EDIT";
    host += "&ID_HOTEL=" + document.getElementById("id_hotel").value;
    host += "&NOMBRE_HOTEL=" + document.getElementById("nombre").value;
    host += "&UBICACION=" + document.getElementById("ubicacion").value;
    host += "&DESCRIPCION=" + document.getElementById("descripcion").value;
    host += "&FECHA_ENTRADA=" + document.getElementById("fecha_entrada").value;
    //host += "&URL_IMAGEN=" + document.getElementById("cambiaImagen").value;
    host += "&DURACION=" + document.getElementById("duracion").value;
    host += "&PRECIOESTANCIA=" + document.getElementById("precio").value;
  //*****************determinar estado check********************************
    var valor = false;
    var dato = document.getElementById("activo");
    if (!dato.checked) {var valor = false;} else {var valor = true;}
    host += "&ACTIVO=" + valor;
  //**********************************************************************  
    
     
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







