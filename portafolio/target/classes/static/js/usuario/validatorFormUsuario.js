$("#formUsuario").validate({
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
	apellido: {
        required: "Debe ingresar un Apellido"
	},
	email: {
        required: "Debe ingresar un Email"
	}
  },
   submitHandler: function(form) {
      form.submit();
    }
});
