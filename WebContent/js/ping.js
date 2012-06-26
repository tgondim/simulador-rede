$(document).ready(function(){

	$("#enviar_ping").click(function(){	
		

			if($(this).attr("data-id") != ""){
				
				$.ajax({
					type: 'POST',
					url: 'SessionManagerServlet',
					data: {
							"operacao" : "criarNovaRede"
								},
					dataType: 'json'

				}).done(function(resposta){
					alert('result=' + resposta.result + ' id=' + resposta.id);
				});	
			}
	
	});
});