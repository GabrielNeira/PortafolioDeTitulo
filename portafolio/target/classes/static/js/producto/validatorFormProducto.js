
$("#formProducto").validate({
 errorClass: 'errorForm',
  rules: {
    nombreProducto: {
        required: true,maxlength:50
	},
	cantidadProducto: {
        required: true,maxlength:10
	},
	pesoProducto: {
        required: true,maxlength:3
	},
	volumenProducto: {
        required: true,maxlength:4
	},
        estadoProducto: {
        required: true,maxlength:1
        },
        refrigeración: {
        required: true, maxlength:1
        },
        fechaLlegada: {
        required: true,maxlength:10
        },
        codProductor:{
        required:true,maxlength: 5
        }
        

  },
  messages : {
	nombreProducto: {
        required: "Debe ingresar un Nombre para el producto",
        maxlength:"Nombre no puede exceder largo de 50"
	},
	cantidadProducto: {
        required: "Debe ingresar una Cantidad para el producto"
        },
        pesoProducto: {
        required: "Debe ingresar un Peso para el producto"
        },
        volumenProducto: {
        required: "Debe ingresar un Volumen para el producto"
        },
        estadoProducto: {
        required: "Debe ingresar un Estado para el producto"
        },
        refrigeración: {
        required: "Debe ingresar si es necesaria Refrigeración para el producto",
        maxlength:"Ingresar 1 para SI, 2 para NO"
        },
        fechaLlegada: {
        required: "Debe ingresar una Fecha de llegada para el producto", 
        maxlength: "Largo máximo 10 carácteres"
        },
        codProductor:{
        required:"Debe ingresar un productor asociado"
        }

  },
   submitHandler: function(form) {
      form.submit();
    }
});
