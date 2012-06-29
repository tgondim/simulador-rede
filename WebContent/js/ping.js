$(document).ready(function(){
		
	$("#enviar_ping").click(function(){	
		
		var terminal = $("#text_terminal").val();
		var tokens = terminal.split(" ");
		
		$.ajax({
			type: 'POST',
			url: 'SessionManagerServlet',
			data: {
					"operacao" : "processarpacote",
					"idRede" : sessionId,
					"nomeOrigem" : $(this).attr('data-id-origem'),
					"ipDestino" : tokens[1],
					"conteudo" : tokens[0]
				  },
			dataType: 'json'

		}).done(function(resposta){
			if(resposta.result == "OK"){

				$("#terminal").empty();
				$("#terminal").html(
						"<textarea id='text_terminal' type='text'>Entregue com sucesso.</textarea>"
				);
			}
			if(resposta.result == "ERRO"){
				
				$("#terminal").empty();
				$("#terminal").html(	
					"<textarea id='text_terminal' type='text'>"+resposta.retorno+"</textarea>"
				);
			}
			
		});				
	});
	
	$("#limpar_terminal").click(function(){
		$("#terminal").empty();
		$("#terminal").html(
				"<textarea id='text_terminal' type='text' placeholder='Enter your command'></textarea>"
		);
	});
});