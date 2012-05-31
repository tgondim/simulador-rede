$(document).ready(function(){

	$(function() {

		$( "#endereco_ip" ).mask("999.999.999.999");
		$( "#mascara_sub_rede" ).mask("999.999.999.999");
		$( "#gateway_padrao" ).mask("999.999.999.999");			
	});

	$("#tab_computer").click(function(){
		
		$("#endereco_ip").fadeIn('slow', function(){});
		$("#tab_router").removeClass("active");
		$("#tab_switch").removeClass("active");
		$("#tab_hub").removeClass("active");
		$(this).addClass("active");
		$("#switch_table").fadeOut('fast',function(){});
		$("#router_table").fadeOut('slow',function(){});
	});

	$("#tab_router").click(function(){

		$("#endereco_ip").fadeIn('slow', function(){});
		$("#tab_computer").removeClass("active");
		$("#tab_switch").removeClass("active");
		$("#tab_hub").removeClass("active");
		$(this).addClass("active");
		$("#switch_table").fadeOut('fast',function(){});
		$("#router_table").fadeIn('slow',function(){});
	});

	$("#tab_switch").click(function(){

		$("#endereco_ip").fadeOut('slow', function(){});
		$("#tab_router").removeClass("active");
		$("#tab_computer").removeClass("active");
		$("#tab_hub").removeClass("active");
		$(this).addClass("active");
		$("#router_table").fadeOut('fast',function(){});
		$("#switch_table").fadeIn('slow',function(){});
	});

	$("#tab_hub").click(function(){

		$("#endereco_ip").fadeIn('slow', function(){});
		$("#tab_router").removeClass("active");
		$("#tab_switch").removeClass("active");
		$("#tab_computer").removeClass("active");
		$(this).addClass("active");
		$("#switch_table").fadeOut('fast',function(){});
		$("#router_table").fadeOut('slow',function(){});
	});

	$(function() {

		$( ".draggable" ).draggable({
			helper: 'clone',
		    cursor: 'move',
			tolerance: 'fit'
		});
	});

	$("#droppable").droppable({
       accept: ".draggable",
       drop: function (e, ui) {

            if ($(ui.drag)[0] != "") {

                var pos = ui.helper.offset();
               
                x = ui.helper.clone();
                ui.helper.remove();

                x.draggable({

                    helper: 'original',

                    containment: '#droppable',

                    tolerance: 'fit'

                });
                   
                x.appendTo('#droppable');

            }

        }

    });

});