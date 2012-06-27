$(document).ready(function(){

	$(".draggable").live('click',function(){

		if($(this).attr("data-id") == ""){
			
			$(this).attr("data-id", new Date().getTime());
			$(this).attr("title", $(this).attr("data-id"));
			
			$.ajax({
				type: 'POST',
				url: 'SessionManagerServlet',
				data: {
						"operacao" : "criarDispositivo",
						"idRede" : sessionId,
						"nomeDispositivo" : $(this).attr("data-id"),
						"tipoDispositivo" : $(this).attr("data-tipo"),
						"numInterfaces" : $(this).attr("data-num-interface"),
					  },
				dataType: 'json'

			}).done(function(resposta){
				
				$("#text_terminal").append(resposta.retorno + "\n");
				
			});	
		}
		else{			
			$("#enviar_ping").attr("data-id-origem",$(this).attr("data-id"));
			$("#id_dispositivo").val($(this).attr("data-id"));
		}
		
	});
});

