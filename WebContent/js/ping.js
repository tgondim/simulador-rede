$(document).ready(function(){

	$("#enviar_ping").click(function(){	
		
		var terminal = $("#text_terminal").val();
		var tokens = terminal.split(" ");
		
		if($(this).attr("data-id") != ""){
			
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
				
				$.ajax({
					type: 'POST',
					url: 'SessionManagerServlet',
					data: {
							"operacao" : "getConsole"
						  },
					dataType: 'json'

				}).done(retornaConsole(retornoConsole));	
				
			});	
		}
		
		function retornaConsole(retornoConsole){
			$("#text_terminal").empty();
			$("#text_terminal").val(retornoConsole.retorno + "\n");
		}
	
	});
});