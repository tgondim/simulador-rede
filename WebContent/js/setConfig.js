$(document).ready(function(){

	$("#endereco_ip").focusout(function() {
		
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
			
			$("#text_terminal").append(resposta.retorno + "\n");
			
		});	
	//}).blur(function() {
		  
	});
	

	$("#mascara_sub_rede").focusout(function() {
		
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
			
			$("#text_terminal").append(resposta.retorno + "\n");
			
		});
	//}).blur(function() {
		  
	});
	

	$("#gateway_padrao").focusout(function() {
		
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
			
			$("#text_terminal").append(resposta.retorno + "\n");
			
		});
	//}).blur(function() {
		  
	});
});