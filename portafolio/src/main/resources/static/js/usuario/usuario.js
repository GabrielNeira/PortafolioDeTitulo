$(document).ready(function() {

	var abrirModal = $('#llamado').val();
	
	if(abrirModal !== null && abrirModal === "actualizar"){
		$('#funcion').val("actualizarUsuario");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	}


	$("#crearUsuario").click(function() {
		$('#funcion').val("crearUsuario");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	});
});

function editar(id){
	$(location).attr('href', '/feriavirtual/administracionUsuarios/'+id)
}

function eliminar(id){
	$.ajax({
		url: '/feriavirtual/administracionUsuarios/'+id,
		type: 'DELETE',
		success: function(result) {
			alert("Usuario Eliminado");
			$(location).attr('href', '/feriavirtual/administracionUsuarios/')
		}
	});
}