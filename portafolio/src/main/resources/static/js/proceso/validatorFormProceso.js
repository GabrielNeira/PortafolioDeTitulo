
$("#formProceso").validate({
 errorClass: 'errorForm',
  rules: {
    nombreProceso: {
        required: true
	},
	estadoProceso: {
        required: true
	},
	fechaInicio: {
        required: true
	},
	fechaTermino: {
        required: true
	}
  },
  messages : {
	nombreProceso: {
        required: "Debe ingresar un Nombre para el proceso"
	},
	estadoProceso: {
        required: "Debe ingresar un Estado del proceso"
	},
	fechaInicio: {
        required: "Debe ingresar la fecha de inicio"
	},
	fechaTermino: {
        required: "Debe ingresar la fecha de termino"
	}
  },
   submitHandler: function(form) {
      form.submit();
    }
});
