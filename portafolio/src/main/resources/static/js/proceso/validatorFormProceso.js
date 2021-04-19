$("#formProceso").validate({
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
        required: "Debe ingresar un Nombre"
	},
	fecha_inicio: {
        required: "Debe ingresar una fecha_inicio"
	},
	fecha_termino: {
        required: "Debe ingresar una fecha_termino"
	}
  },
   submitHandler: function(form) {
      form.submit();
    }
});
