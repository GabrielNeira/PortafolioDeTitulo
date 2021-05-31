function actualizaProceso(idProceso, estado){
    $.ajax({
        type : "GET",
        url : "./detalleProcesosrest/cambiaEstado/"+idProceso+"/"+estado,
        done : function(data) {
        alert("proceso Actualizado");
        }
    });
}
