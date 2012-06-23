$(document).ready(function(){

	$("#enviar_ping").click(function(){	
		
		$(".draggable").live('click',function(){

			if($(this).attr("data-id").val() != ""){
				
				$.ajax({
					type: 'POST',
					url: '/ping',
					data: {
							"id" : $(this).attr('data-id'),
							"tipo" : $("#text_terminal").val() 
						  },
					dataTypeString: 'string'

				}).done(function(resposta){
					
				});	
			}
		});
	});
});