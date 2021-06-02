function actualizaProceso(idProceso, estado) {
    $.ajax({
        type: "GET",
        url: "./detalleProcesosrest/cambiaEstado/" + idProceso + "/" + estado,
        success: function (data) {
            alert("proceso Actualizado");
            window.location.reload();
        }
    });
}

