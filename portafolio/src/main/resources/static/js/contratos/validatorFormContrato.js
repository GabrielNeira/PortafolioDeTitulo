$("#formContrato").validate({
  rules: {
    nombre: {
        required: true
	},
	apellido: {
        required: true
	},
	email: {
        required: true
	}
  },
  messages : {
	nombre: {
        required: "Debe ingresar un ID CONTRATO"
	},
	apellido: {
        required: "Debe ingresar un CODIGO PRODUCTOR"
	},
	email: {
        required: "Debe ingresar un ESTADO CONTRATO"
	}
  },
   submitHandler: function(form) {
      form.submit();
    }
});
