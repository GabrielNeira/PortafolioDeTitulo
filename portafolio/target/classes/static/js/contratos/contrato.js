$(document).ready(function() {

	var abrirModal = $('#llamado').val();
	
	if(abrirModal !== null && abrirModal === "actualizar"){
		$('#funcion').val("actualizarContrato");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	}


	$("#crearUsuario").click(function() {
		$('#funcion').val("crearContrato");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	});
});

function editar(id){
	$(location).attr('href', '/feriavirtual/administracionContratos/'+id)
}