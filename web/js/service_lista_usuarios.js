$(document).ready(function () {

    let datatable = $('#mytable').DataTable({
        "ajax": {
         
            "url": "../Controller?ACTION=USUARIO.FIND_ALL",
          
            "dataSrc": ""
        },
        "columns": [
            {"data": "id"}, //columna 0
            {"data": "email"},
            {"data": "password"},
            {"defaultContent": "<button type='button' id='idEditar' class='form btn btn-primary btn-xs ' data-toggle='modal' data-target='#updateModal' > <span class='glyphicon glyphicon-search'></span></button>"},
            {"defaultContent": "<button type='button' id='idborrar' class='form btn btn-primary btn-xs ' > <span class='glyphicon glyphicon-remove'></span></button>"}
        ]



    });


    $('#add_button').click(function () {
        $('#user_form')[0].reset();
        $('.modal-title').text("Nuevo usuario");
        $('#action').val("Inserta nuevo usuario");
        $('#operacion').val("Add");
        $('#user_uploaded_image').html('');
    });



//Metodo que abre modal para editar y lo rellena

    $("#mytable tbody").on("click", "#idEditar", function () {

        //Identifica boton dinamicamente
       var data = datatable.row($(this).parents("tr")).data();
        var id = $("#ID_Usuario").val(data.id),
                Email_ = $("#Email").val(data.email),
                Password_ = $('#Password').val(data.password);
      

    });




    //Metodo que abre modal para borrar registro
    $("#mytable tbody").on("click", "#idborrar", function () {
        alert("vas a borrar un registro");
        //Busca dentro de la matriz del datatable el valor del id
        let data = datatable.row($(this).parents()).data();
        //console.log(data);
        var iduser = data.id;
        window.location.href = "../Controller?ACTION=USUARIO.DELETE&ID=" + iduser;
        datatable.ajax.reload(null, false);

    });


});


//He utilizado notación javascript en lugar de jquery para la insercion del registro
function registroUsuario() {

    var host = "../Controller";
    host += "?ACTION=USUARIO.ADDADMIN";
    host += "&EMAIL=" + document.getElementById("nuevo_Email").value;
    host += "&PASS=" + document.getElementById("nuevo_Password").value;

    document.location = host;
}

//He utilizado notacion javascript en lugar de jquery para la edicion del registro
function editarUsuario() {

    var host = "../Controller";
    host += "?ACTION=USUARIO.EDIT";
    host += "&ID=" + document.getElementById("ID_Usuario").value;
    host += "&EMAIL=" + document.getElementById("Email").value;
    host += "&PASS=" + document.getElementById("Password").value;

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