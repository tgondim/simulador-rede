$(document).ready(function(){

	$("#enviar_ping").click(function(){	
		
		if($(this).attr("data-id") != ""){
			
			$.ajax({
				type: 'POST',
				url: 'SessionManagerServlet',
				data: {
						"operacao" : "processarpacote",
						"idRede" : sessionId,
						"nomeOrigem" : $(this).attr('data-id-origem'),
						"ipDestino" : "192.168.0.1",
						"conteudo" : "1"
						
					  },
				dataType: 'json'

			}).done(function(resposta){
				
				$("#text_terminal").append(resposta.retorno + "\n");
				
			});	
		}
	
	});
});