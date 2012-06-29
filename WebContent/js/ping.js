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
				$("#terminal").empty();
				$("#terminal").html(
						"<textarea id='text_terminal' type='text' placeholder='Enter your command'>" +
						"" + resposta.retorno + "\n</textarea>"
				);
			});				
		}
	});
	
	$("#limpar_terminal").click(function(){
		$("#terminal").empty();
		$("#terminal").html(
				"<textarea id='text_terminal' type='text' placeholder='Enter your command'></textarea>"
		);
	});
});