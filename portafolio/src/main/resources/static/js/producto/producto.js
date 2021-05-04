$(document).ready(function() {

	var abrirModal = $('#llamado').val();
	
	if(abrirModal !== null && abrirModal === "actualizar"){
		$('#funcion').val("actualizarProducto");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	}


	$("#crearProducto").click(function() {
		$('#funcion').val("crearProducto");
		$('#myModal').modal('toggle');
		$('#myModal').modal('show');
	});
});

function editar(id){
	$(location).attr('href', '/feriavirtual/administracionProductos/'+id)
}

function eliminar(id){
	$.ajax({
		url: '/feriavirtual/administracionProductos/'+id,
		type: 'DELETE',
		success: function(result) {
			alert("Producto Eliminado");
			$(location).attr('href', '/feriavirtual/administracionProductos/')
		}
	});
}
