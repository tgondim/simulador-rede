************
Para Desenvolvedores
************

Visão geral sobre arquitetura do Simulador
======================
	A arquiteruta do atual projeto é cliente-servidor. Desta forma a aplicação do cliente irá ter a responsabilidade de fazer chamadas ajax(uma tecnica de fazer chamadas ao servidor), para o servidor para criar a rede. A idéia é que o cliente o servidor fiquem sincronizados, para que quando o usuário executar algum comando no cliente, o servidor possa responder com a resposta correta.

	A construção do projeto usando essa arquitetura fui imposta pois os participantes do desenvolvimento do projeto, não possuiam pleno conhecimento da linguagem `JavaScript`, para que ela fosse construída.
    
Diagrama de Classe do Servidor
===========================

Abaixo está o diagrama de classes da parte do core do projeto que é executada no servidor.

.. image:: ../../DiagramadeClasse.png
   :scale: 65 %
   :height: 800px


Funcionamento do Cliente
=========================
	Como já dito, o cliente  funciona com o uso de ajax. Cada objeto construído no cliente irá ser enviado para o servidor. 
	
	* Abaixo exemplo de código para a chamada do ping no Servidor:
	
$(document).ready(function(){

	$("#enviar_ping").click(function(){	
		
		if($(this).attr("data-id") != ""){
			
			$.ajax({
				type: 'POST',
				url: 'SessionManagerServlet',
				data: {
						"operacao" : "processarpacote",
						"idRede" : sessionId,
						"nomeOrigem" : $(this).attr('data-id-origem'),
						"ipDestino" : "192.168.0.1",
						"conteudo" : "1"
						
					  },
				dataType: 'json'

			}).done(function(resposta){
				
				$("#text_terminal").append(resposta.retorno + "\n");
				
			});	
		}
	
	});
});