$(document).ready(function() {

    $('#modalConfirmacionTransporte').click(function() {
        $.ajax({
            type : "GET",
            url : "/detalleProcesosrest/cambiaEstado/121/6",
            done : function(data) {
            }
        });
        location.reload();
    });

});

function actualizaEnTransporte(idProceso){
    $.ajax({
        type : "GET",
        url : "./detalleProcesosrest/cambiaEstado/"+idProceso+"/4",
        done : function(data) {
        }
    });
}

function actualizaFinalizado(idProceso){
    $.ajax({
        type : "GET",
        url : "./detalleProcesosrest/cambiaEstado/"+idProceso+"/6",
        done : function(data) {
        }
    });
}

function actualizaCancelado(idProceso){
    $.ajax({
        type : "GET",
        url : "./detalleProcesosrest/cambiaEstado/"+idProceso+"/7",
        done : function(data) {
        }
    });
}