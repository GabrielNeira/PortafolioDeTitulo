$(document).ready(function() {

	var abrirModal = $('#llamado').val();
	
	if(abrirModal !== null && abrirModal === "actualizar"){
		$('#funcion').val("actualizarProceso");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	}


	$("#crearProceso").click(function() {
		$('#funcion').val("crearProceso");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	});
});

function editar(id){
	$(location).attr('href', '/feriavirtual/administracionProcesos/'+id)
}

function eliminar(id){
	$.ajax({
		url: '/feriavirtual/administracionProcesos/'+id,
		type: 'DELETE',
		success: function(result) {
			alert("Proceso Eliminado");
			$(location).attr('href', '/feriavirtual/administracionProcesos/')
		}
	});
}
