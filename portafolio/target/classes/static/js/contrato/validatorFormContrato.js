$("#formContrato").validate({
errorClass: 'errorForm',
  rules: {
    codProductor: {
        required: true
	},
	estadoContrato: {
        required: true
	},
	fechaGeneracion: {
        required: true
	},
	fechaVencimiento: {
        required: true
	}
  },
  messages : {
	codProductor: {
        required: "Debe ingresar un Codigo Productor"
	},
	estadoContrato: {
        required: "Debe ingresar un estado del Contrato"
	},
	fechaGeneracion: {
        required: "Debe ingresar  Fecha de Generacion"
	},
	fechaVencimiento: {
        required: "Debe ingresar  Fecha de Vencimiento"
	}
  },
   submitHandler: function(form) {
      form.submit();
    }
});
