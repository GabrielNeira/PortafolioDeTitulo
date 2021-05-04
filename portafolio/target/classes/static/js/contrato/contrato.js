$(document).ready(function() {

	var abrirModal = $('#llamado').val();
	
	if(abrirModal !== null && abrirModal === "actualizar"){
		$('#funcion').val("actualizarContrato");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	}


	$("#crearContrato").click(function() {
		$('#funcion').val("crearContrato");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	});
});

function editar(id){
	$(location).attr('href', '/feriavirtual/administracionContratos/'+id)
}


	function eliminar(id){
		$.ajax({
			url: '/feriavirtual/administracionContratos/'+id,
			type: 'DELETE',
			success: function(result) {
				alert("Contrato Eliminado");
				$(location).attr('href', '/feriavirtual/administracionContratos/')			}
		});
}