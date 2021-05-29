	$(document).ready(function() {

		$('#btn-ingresar').click(function() {
			var form = $("#formDetalleProd");
			var url = form.attr('action');
			var jsonData = form.serializeArray().reduce(function(a, z) {
				a[z.name] = z.value;
				return a;
			}, {});
			$.ajax({
				type : "POST",
				url : url,
				data : JSON.stringify(jsonData),
				 dataType: 'json',
                contentType: "application/json; charset=utf-8",
				done : function(data) {
				}
			});
			location.reload();
		});

	});