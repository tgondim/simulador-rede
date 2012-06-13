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
	
	$(function() {

		$( "#droppable" ).draggable({
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

    $("#trash").droppable({
	    accept: '#droppable',

	    drop: function (ev, ui) {
	        if ($(ui.draggable)[0].id == "") {
	            ui.draggable.remove();
	        }
	    }
	});

    // Mascara IP    //
    function maskIP(e, obj) {
        if (document.all) { var evt = event.keyCode; } // caso seja IE
        else { var evt = e.charCode; }    // do contrário...
        if (evt < 20) return true;    // liberando teclas de controle
        if ((/^(\d{1,3}\.){3}\d{3}$/).test(obj.value)) return false;
        var chr = String.fromCharCode(evt);    // pegando a tecla digitada
        if (!(/[\d\.]/).test(chr)) return false; // testando se é uma tecla válida (um digito ou um ponto)
        if (chr == '.')
            return (!(/\.$|^(\d{1,3}\.){3}/).test(obj.value));
        else
            if ((/\d{3}$/).test(obj.value))
                obj.value += '.';
        return true;
    }

    // Valida IP    //    
    function validateIP(ip, showErrMsg) {
        a = (/\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/).test(ip);
        if (!a && showErrMsg) {
            alert(ip + ' não é um ip válido!');
        }
        return a;
    }


	function callServer(method, parameter) {
		var parameters = {
			"method" : method,
			"parameter" : parameter
		};

		$.ajax({
			url : "RemoteApiServlet",
			type : "POST",
			async : "true",
			dataType : "json",
			data : parameters,
			success : handlerSucesseCallServer,
			error : handleErrorCallServer,
		});
	}

	// function(data) {
	// if (data.result == "OK") {
	// //ajxfrm.state.value = data.state;
	// handlerSucesseCallServer(data);
	// } else {
	// alert(data.state);
	// }
	// }

	function handlerSucesseCallServer(data) {

	}

	function handleErrorCallServer(jqXHR, textStatus, errorThrown) {

	}

	function testServer() {
		var parameter = {
			"rede" : {
				"rede1" : {
					"switch" : {
						"switch1" : [ "pc1", "pc2" ]
					},
					"pc" : {
						"pc1" : {
							"ip" : "10.0.0.100",
							"mac" : "",
							"mask" : "255.255.255.0",
							"interface" : {
								1 : [ "switch1" ]
							},
						},
						"pc2" : {
							"ip" : "10.0.0.101",
							"mac" : "",
							"mask" : "255.255.255.0",
							"interface" : {
								1 : [ "switch1" ]
							},
						}
					},
				}
			}
		}
		callServer("configNetwork", parameter);
	}

});