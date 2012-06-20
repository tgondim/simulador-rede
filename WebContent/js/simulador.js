$(document).ready(function() {

	$(function() {

		$("#endereco_ip").mask("999.999.999.999");
		$("#mascara_sub_rede").mask("999.999.999.999");
		$("#gateway_padrao").mask("999.999.999.999");
	});

	$("#tab_computer").click(function() {

		$("#endereco_ip").fadeIn('slow', function() {
		});
		$("#tab_router").removeClass("active");
		$("#tab_switch").removeClass("active");
		$("#tab_hub").removeClass("active");
		$(this).addClass("active");
		$("#switch_table").fadeOut('fast', function() {
		});
		$("#router_table").fadeOut('slow', function() {
		});
	});

	$("#tab_router").click(function() {

		$("#endereco_ip").fadeIn('slow', function() {
		});
		$("#tab_computer").removeClass("active");
		$("#tab_switch").removeClass("active");
		$("#tab_hub").removeClass("active");
		$(this).addClass("active");
		$("#switch_table").fadeOut('fast', function() {
		});
		$("#router_table").fadeIn('slow', function() {
		});
	});

	$("#tab_switch").click(function() {

		$("#endereco_ip").fadeOut('slow', function() {
		});
		$("#tab_router").removeClass("active");
		$("#tab_computer").removeClass("active");
		$("#tab_hub").removeClass("active");
		$(this).addClass("active");
		$("#router_table").fadeOut('fast', function() {
		});
		$("#switch_table").fadeIn('slow', function() {
		});
	});

	$("#tab_hub").click(function() {

		$("#endereco_ip").fadeIn('slow', function() {
		});
		$("#tab_router").removeClass("active");
		$("#tab_switch").removeClass("active");
		$("#tab_computer").removeClass("active");
		$(this).addClass("active");
		$("#switch_table").fadeOut('fast', function() {
		});
		$("#router_table").fadeOut('slow', function() {
		});
	});

	$(function() {

		$(".draggable").draggable({
			helper : 'clone',
			cursor : 'move',
			tolerance : 'fit'
		});
	});

	$(function() {

		$('#droppable').on('click', '.hub', function() {
			ativarAbaHub();
			deviceFocused(this);
			showConfigDevice();
		});
		
		$('#droppable').on('click', '.computer', function() {
			ativarAbaComputer();
			deviceFocused(this);
			showConfigDevice();
		});
		
		$('#droppable').on('click', '.router', function() {
			ativarAbaRouter();
			deviceFocused(this);
			showConfigDevice();
		});
		
		$('#droppable').on('click', '.switch', function() {
			ativarAbaSwitch();
			deviceFocused(this);
			showConfigDevice();
		});
	});

	$("#droppable").droppable({
		accept : ".draggable",
		drop : function(e, ui) {

			if ($(ui.drag)[0] != "") {

				var pos = ui.helper.offset();

				x = ui.helper.clone();
				ui.helper.remove();

				x.draggable({

					helper : 'original',

					containment : '#droppable',

					tolerance : 'fit',

					drop : function(ev, ui) {
						this.draggable.remove();
					}
				});

				//x.addClass(".removable");
				x.appendTo('#droppable');
				//x.appendTo(".draggable");

			}

		}
	});

	$("#trash").droppable({
		accept : '.draggable',

		drop : function(ev, ui) {

			if ($(ui.draggable)[0].id == "") {
				ui.draggable.remove();
			}
		}
	});

	// Mascara IP    //
	function maskIP(e, obj) {
		if (document.all) {
			var evt = event.keyCode;
		}// caso seja IE
		else {
			var evt = e.charCode;
		}// do contrário...
		if (evt < 20)
			return true;
		// liberando teclas de controle
		if ((/^(\d{1,3}\.){3}\d{3}$/).test(obj.value))
			return false;
		var chr = String.fromCharCode(evt);
		// pegando a tecla digitada
		if (!(/[\d\.]/).test(chr))
			return false;
		// testando se é uma tecla válida (um digito ou um ponto)
		if (chr == '.')
			return (!(/\.$|^(\d{1,3}\.){3}/).test(obj.value));
		else if ((/\d{3}$/).test(obj.value))
			obj.value += '.';
		return true;
	}

	// Valida IP    //
	function validateIP(ip, showErrMsg) {
		a = (/\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/).test(ip);
		if (!a && showErrMsg) {
			alert(ip + ' Não é um IP valido!');
		}
		return a;
	}

	$(function() {
		network = document.network = {};
		nk = network.networks = {};
		nk.network1 = {};
	});

});

function ativarAbaComputer() {

	$("#endereco_ip").fadeIn('slow', function() {
	});
	$("#gateway_padrao").fadeIn('slow', function() {
	});
	$("#mascara_sub_rede").fadeIn('slow', function() {
	});
	removeClasseTodasAbas();
	$("#tab_computer").addClass("active");
	$("#switch_table").fadeOut('fast', function() {
	});
	$("#router_table").fadeOut('slow', function() {
	});
}

function ativarAbaSwitch(){
	$("#endereco_ip").fadeOut('slow', function() {
	});
	$("#gateway_padrao").fadeOut('slow', function() {
	});
	$("#mascara_sub_rede").fadeOut('slow', function() {
	});
	removeClasseTodasAbas();
	$("#tab_switch").addClass("active");
	$("#switch_table").fadeOut('fast', function() {
	});
//	$("#router_table").fadeIn('slow', function() {
//	});
}

function ativarAbaHub(){
	$("#endereco_ip").fadeOut('slow', function() {
	});
	$("#gateway_padrao").fadeOut('slow', function() {
	});
	$("#mascara_sub_rede").fadeOut('slow', function() {
	});
	removeClasseTodasAbas();
	$("#tab_hub").addClass("active");
	$("#switch_table").fadeOut('fast', function() {
	});
	//$("#router_table").fadeOut('slow', function() {
	//});
}

function ativarAbaRouter(){
	$("#endereco_ip").fadeIn('slow', function() {
	});
	$("#gateway_padrao").fadeIn('slow', function() {
	});
	$("#mascara_sub_rede").fadeIn('slow', function() {
	});
	removeClasseTodasAbas();
	$("#tab_router").addClass("active");
	$("#switch_table").fadeOut('fast', function() {
	});
	$("#router_table").fadeIn('slow', function() {
	});
}

function removeClasseTodasAbas(){
	$("#tab_computer").removeClass("active");
	$("#tab_switch").removeClass("active");
	$("#tab_hub").removeClass("active");
	$("#tab_router").removeClass("active");
	
}

function deviceFocused(currentDevice){
	var devices = $("#droppable > .router, .hub, .switch, .computer");
	for (var i=0; i < devices.length; i++) {
	  	$(devices[i]).removeClass("focused");
	};
	if(currentDevice != null){
		$(currentDevice).addClass("focused");
	}
}

function saveConfigDevice(){
	f = $(".focused");
	if(f.length > 0){
		ip = $("#endereco_ip").val();
		mask = $("#mascara_sub_rede").val();
		gateway = $("#gateway_padrao").val();
		nome_lan = $("#nome_lan").val();
		//if(f.config == null)
		//	f.config = {};
		//f.data(nome_lan, {"ip": ip, "mask":mask, "gateway": gateway});
		tmpData = f.data();
		if(tmpData.config == undefined)
			tmpData.config = {};
		tmpData.config[nome_lan] = {"ip": ip, "mask":mask, "gateway": gateway}; 
		f.data("config", tmpData.config);
		ip = $("#endereco_ip").val('');
		mask = $("#mascara_sub_rede").val('');
		gateway = $("#gateway_padrao").val('');
	}
}

function showConfigDevice(){
	f = $(".focused");
	if(f.length > 0){
		tmpData = f.data("config");
		//alert($.toJSON(tmpData));
	}
}

function callServer(parameter) {
	var parameters = {
		//"device_type" : typeDevice,
		"parameter" : parameter
	};

	$.ajax({
		url : "RemoteApiServlet",
		type : "POST",
		async : "true",
		dataType : "json",
		data : parameters,
		success : handlerSucesseCallServer,
		error : handlerErrorCallServer,
	});
}

function handlerSucesseCallServer(data) {

}

function handlerErrorCallServer(jqXHR, textStatus, errorThrown) {

};


function testServer() {
	var parameter = {
		"rede" : {
			"rede1" : {
				"switch" : {
					"switch1" : ["pc1", "pc2"]
				},
				"pc" : {
					"pc1" : {
						"ip" : "10.0.0.100",
						"mac" : "",
						"mask" : "255.255.255.0",
						"interface" : {
							1 : ["switch1"]
						},
					},
					"pc2" : {
						"ip" : "10.0.0.101",
						"mac" : "",
						"mask" : "255.255.255.0",
						"interface" : {
							1 : ["switch1"]
						},
					}
				},
			}
		}
	};
	callServer("configNetwork", parameter);
};

function buildNetworks() {
	//network = document.network = {};
	var network = {};
	//nk = network.networks = {};
	//nk.network1 = {};
	var devices = $("#droppable > .router, .hub, switch, .computer");
	var routers_ = $("#droppable > .router");
	var hubs_ = $("#droppable > .hub");
	var switchs_ = $("#droppable > .switch");
	var pcs_ = $("#droppable > .computer");
	
	var tmpPcs = {}; 
	for(var i=0,j=pcs_.length; i<j; i++){
	  	tmpPcs[i] = $(pcs_[i]).data("config");
	};
	var tmpRoutes = {};
	for(var i=0,j=routers_.length; i<j; i++){
	  	tmpRoutes[i] = $(routers_[i]).data("config");
	};
	
	var tmpSwitch = {};
	for(var i=0,j=switchs_.length; i<j; i++){
	  	tmpSwitch[i] = $(switchs_[i]).data("config");
	};
	
	var tmpHub = {};
	for(var i=0,j=hubs_.length; i<j; i++){
	  	tmpHub[i] = $(hubs_[i]).data("config");
	};
	// for(var i=0,j=routers.length; i<j; i++){
	  // //routers[i]
	// };
	network["pc"] = tmpPcs;
	network["router"] = tmpRoutes;
	network["switch"] = tmpSwitch;
	network["hub"] = tmpHub;
	
	//TODO: 1: Procurar pelos componentes...
	//   	2: Identificar configura��es diferentes
	//		3: criar uma estrutura de rede para cada uma rede encontrada.
	tmpRetorno = $.toJSON(network);
	alert(tmpRetorno);
	return tmpRetorno;
	
	
};

function sendDataToServer(){
	data = buildNetworks();
	callServer(data);
} 
