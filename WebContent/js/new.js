$(document).ready(function(){

	$(".draggable").live('click',function(){

		if($(this).attr("data-id").val() == ""){
			
			$(this).attr("data-id", new Date().getTime());
			$(this).attr("title", $(this).attr("data-id"));

			$.ajax({
				type: 'POST',
				url: '/criarObjeto/' + ".json",
				data: {
						"id" : $(this).attr('data-id'),
						"tipo" : $(this).attr('data-tipo'), 
					  },
				dataTypeString: 'json'

			}).done(function(resposta){
				
			});	
		}
	});
});

