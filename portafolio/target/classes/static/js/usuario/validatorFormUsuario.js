$("#formUsuario").validate({
  errorClass: 'errorForm',
  rules: {
    nombre: {
        required: true
	},
	apellido: {
        required: true
	},
	email: {
        required: true
	},
        tipo:{
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
	},
        tipo: {
        required: "Debe ingresar un Tipo"
        }
        
        
  },
   submitHandler: function(form) {
      form.submit();
    }
});
