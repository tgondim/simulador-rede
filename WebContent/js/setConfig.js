$(document).ready(function(){

	$("#endereco_ip").focusout(function() {
		var ip = $("#endereco_ip").val();
		var id = $("#id_dispositivo").attr("value");
		
		if($("#endereco_ip").val() != ""){
					
			$.ajax({
				type: 'POST',
				url: 'SessionManagerServlet',
				data: {
						"operacao" : "alterarpropriedadedispositivo",
						"idRede" : sessionId,
						"nomeDispositivo" : $("#id_dispositivo").attr("value"),
						"nome" : "ip",
						"valor" : $("#endereco_ip").val()
					  },
				dataType: 'json'

			}).done(function(resposta){
				
				$("#endereco_ip").val($("#endereco_ip").val());
				$("#"+id).attr("data-ip",ip);
				
				$(".alert-info").empty();
				$(".alert-info").html(
						"<button class='close' data-dismiss='alert'>×</button><strong>"+resposta.retorno+"</strong>"
				);
			});	
		}
	});		

	$("#mascara_sub_rede").focusout(function() {
		
		if($("#mascara_sub_rede").val()){
			
			$.ajax({
				
				type: 'POST',
				url: 'SessionManagerServlet',
				data: {
						"operacao" : "alterarpropriedadedispositivo",
						"idRede" : sessionId,
						"nomeDispositivo" : $("#id_dispositivo").val(),
						"nome" : "mascara",
						"valor" : $("#mascara_sub_rede").val()
					  },
				dataType: 'json'

			}).done(function(resposta){
				
				$(".alert-info").empty();
				$(".alert-info").html(
						"<button class='close' data-dismiss='alert'>×</button><strong>"+resposta.retorno+"</strong>"
				);
				
			});	
		}
	});
	

	$("#gateway_padrao").focusout(function() {
		
		if($("#gateway_padrao").val()){
			
			$.ajax({
				
				type: 'POST',
				url: 'SessionManagerServlet',
				data: {
						"operacao" : "alterarpropriedadedispositivo",
						"idRede" : sessionId,
						"nomeDispositivo" : $("#id_dispositivo").val(),
						"nome" : "gateway",
						"valor" : $("#gateway_padrao").val()
					  },
				dataType: 'json'

			}).done(function(resposta){
				
				$(".alert-info").empty();
				$(".alert-info").html(
						"<button class='close' data-dismiss='alert'>×</button><strong>"+resposta.retorno+"</strong>"
				);
				
			});	
		} 
	});
});