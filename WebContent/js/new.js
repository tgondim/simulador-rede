$(document).ready(function(){

	$(".draggable").live('click',function(){

		if($(this).attr("data-id")== ""){
			
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
				dataTypeString: 'json'

			}).done(function(resposta){
				if(resposta.result == "OK"){
					alert("OK");
				}
				else{
					alert("ERRO");
				}
			});	
		}
	});
});

