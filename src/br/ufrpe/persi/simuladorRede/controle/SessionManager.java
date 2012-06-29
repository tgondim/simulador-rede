package br.ufrpe.persi.simuladorRede.controle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.ufrpe.persi.simuladorRede.controle.exception.DestinoInvalidoException;
import br.ufrpe.persi.simuladorRede.controle.exception.DispositivoNaoEncontradoException;
import br.ufrpe.persi.simuladorRede.modelo.ConfiguracaoRede;
import br.ufrpe.persi.simuladorRede.modelo.Dispositivo;
import br.ufrpe.persi.simuladorRede.modelo.EnderecoIP;
import br.ufrpe.persi.simuladorRede.modelo.Host;
import br.ufrpe.persi.simuladorRede.modelo.Hub;
import br.ufrpe.persi.simuladorRede.modelo.Pacote;
import br.ufrpe.persi.simuladorRede.modelo.Rede;
import br.ufrpe.persi.simuladorRede.modelo.Router;
import br.ufrpe.persi.simuladorRede.modelo.Switch;
import br.ufrpe.persi.simuladorRede.modelo.exception.EnderecoIPMalFormadoException;
import br.ufrpe.persi.simuladorRede.modelo.exception.ImpossivelConectarDispositivoExeption;
import br.ufrpe.persi.simuladorRede.modelo.exception.ImpossivelCriarDispositivoExeption;

public class SessionManager {

	//tempo de vida da sessao em milissegundos
//	private static final long TEMPO_DE_VIDA_SESSAO = 300000; //5 min
	
	private static SessionManager instance;
	
	private Map<String, Rede> sessoes;	
	
//	private Timer sessionCollector;
	
	static {
		SessionManager.instance = new SessionManager();
	}
	
	private SessionManager() {
		super();
		this.sessoes = new HashMap<String, Rede>();
//		this.sessionCollector = new Timer();
//		this.sessionCollector.scheduleAtFixedRate(new SessionCollectorTimerTask(), SessionManager.TEMPO_DE_VIDA_SESSAO, SessionManager.TEMPO_DE_VIDA_SESSAO);
	}
	
	public static SessionManager getInstance() {
		return instance;
	}
	
	public String criarNovaRede() {
		String newId = gerarId();
		this.sessoes.put(newId, new Rede(newId));
		this.sessoes.get(newId).getConsole().append("Nova rede de id " + newId + " criada com sucesso.\n");
		return newId;
	}
	
	public String criarDispositivo(String idRede, String nomeDispositivo, String tipoDispositivo, int numInterfaces) throws ImpossivelCriarDispositivoExeption {
		String retorno = "";
		Dispositivo newDispositivo = null;
		
		if (tipoDispositivo.toLowerCase().equals("host")) {
			newDispositivo = new Host(nomeDispositivo, numInterfaces);
		}
		if (tipoDispositivo.toLowerCase().equals("switch")) {
			newDispositivo = new Switch(nomeDispositivo, numInterfaces);
		}
		if (tipoDispositivo.toLowerCase().equals("router")) {
			newDispositivo = new Router(nomeDispositivo, numInterfaces);
		}
		if (tipoDispositivo.toLowerCase().equals("hub")) {
			newDispositivo = new Hub(nomeDispositivo, numInterfaces);
		}
		if (newDispositivo != null) {
			this.sessoes.get(idRede).addDispositivo(newDispositivo);			
		} else { 
			throw new ImpossivelCriarDispositivoExeption("Não foi possível criar o dispositivo " + nomeDispositivo + ".");
		}
		
		retorno =  "Dispositivo de id " + nomeDispositivo + " criado com sucesso.";
		this.sessoes.get(idRede).getConsole().append(retorno + "\n");
		
		return retorno;
	}
	
	public String alterarPropriedadeDispositivo(String idRede, String nomeDispositivo, String nome, String valor) throws EnderecoIPMalFormadoException, ImpossivelConectarDispositivoExeption {
		String retorno = "";
		Dispositivo disp = this.sessoes.get(idRede).getDispositivos().get(nomeDispositivo);
		
		if (nome.toLowerCase().equals("id")) {
			disp.setId(valor);
		} else if (nome.toLowerCase().equals("numerodeinterfaces")) {
			disp.setNumeroDeInterfaces(Integer.valueOf(valor));
		} else if (nome.toLowerCase().equals("macaddress")) {
			disp.setMacAddress(valor);
		} else if (nome.toLowerCase().equals("ip")) {
			if (disp.getConfiguracao() == null) {
				disp.setConfiguracao(new ConfiguracaoRede());
			}
			disp.getConfiguracao().setIp(new EnderecoIP(valor));
		} else if (nome.toLowerCase().equals("mascara")) {
			if (disp.getConfiguracao() == null) {
				disp.setConfiguracao(new ConfiguracaoRede());
			}
			disp.getConfiguracao().setMascara(new EnderecoIP(valor));
		} else if (nome.toLowerCase().equals("gateway")) {
			Dispositivo dispGateway = this.sessoes.get(idRede).getDispositivos().get(valor);
			if (dispGateway != null) {
				if (disp.getConfiguracao() == null) {
					disp.setConfiguracao(new ConfiguracaoRede());
				}
				disp.getConfiguracao().setGateway(dispGateway);
			} else {
				throw new ImpossivelConectarDispositivoExeption("Não foi possível encontrar o dispositivo gateway de id " + valor + ".");
			}
		}
		retorno = "Propriedade " + nome + " alterada com sucesso.";
		this.sessoes.get(idRede).getConsole().append(retorno + "\n");
		
		return retorno;
	}
	
	public String adicionarRota(String idRede, String nomeDispositivo, String nomeRede, String idDispositivo) throws DispositivoNaoEncontradoException, EnderecoIPMalFormadoException {
		String retorno = "";

		Dispositivo router = this.sessoes.get(idRede).getDispositivos().get(nomeDispositivo);
		if ((router == null) || (!(router instanceof Router))) {
			throw new DispositivoNaoEncontradoException("Não foi possível encontrar o router de id " + nomeDispositivo + ".");
		}
		
		Dispositivo dispositivo = this.sessoes.get(idRede).getDispositivos().get(idDispositivo);
		if (dispositivo == null) {
			throw new DispositivoNaoEncontradoException("Não foi possível encontrar o dispositivo de id " + idDispositivo + ".");
		}

		((Router)router).adicionarRota(new EnderecoIP(nomeRede), dispositivo);
		retorno = "A rota da rede=" + nomeRede + " foi para o dispositivo=" + idDispositivo + " foi adicinada com sucesso.";
		return retorno;
	}
	
	public String conectarDispositivos(String idRede, String nomeDispositivo1, String nomeDispositivo2) throws ImpossivelConectarDispositivoExeption {
		String retorno = "";
		Dispositivo dispositivo1 = this.sessoes.get(idRede).getDispositivos().get(nomeDispositivo1);
		Dispositivo dispositivo2 = this.sessoes.get(idRede).getDispositivos().get(nomeDispositivo2);
		
		if ((dispositivo1 != null) && (dispositivo2 != null)) {
			try {
				dispositivo1.conectarDispositivo(dispositivo2);
				dispositivo2.conectarDispositivo(dispositivo1);
			} catch (ImpossivelCriarDispositivoExeption e) {
				throw new ImpossivelConectarDispositivoExeption("Não foi possível conectar os dispositivos de id " + nomeDispositivo1 +  " e " + nomeDispositivo2 + ".");
			}
		} else {
			throw new ImpossivelConectarDispositivoExeption("Não foi possível conectar os dispositivos de id " + nomeDispositivo1 +  " e " + nomeDispositivo2 + ".");
		}
		retorno = "Dispositivos " + nomeDispositivo1 + " e " + nomeDispositivo2 + " conectados com sucesso.";
		this.sessoes.get(idRede).getConsole().append(retorno + "\n");
		
		return retorno;
	}
	
	public String processarPacote(String idRede, String nomeOrigem, String ipDestino, String conteudo) throws DispositivoNaoEncontradoException, DestinoInvalidoException {
		
		String retorno = "";
		
		Dispositivo dispOrigem = this.sessoes.get(idRede).getDispositivos().get(nomeOrigem);

		if (dispOrigem == null) {
			throw new DispositivoNaoEncontradoException("Não foi possível encontrar o dispositivo de id " + nomeOrigem + ".");
		}
		
		
		try {
			Pacote pacote = new Pacote(conteudo, dispOrigem.getConfiguracao().getIp(), new EnderecoIP(ipDestino));
			pacote.addOnPacoteRecebidoListener(this.sessoes.get(idRede));
			if (!(dispOrigem instanceof Host)) {
				throw new DispositivoNaoEncontradoException("Não foi possível encontrar o Host de id " + nomeOrigem + ".");
			}
			((Host)dispOrigem).enviarPacote(pacote);
			
		} catch (EnderecoIPMalFormadoException e) {
			throw new DestinoInvalidoException("O destino " + ipDestino + " e invalido.");
		}
		
		retorno = "PING - Disparando " + ipDestino + " com dados";
		this.sessoes.get(idRede).getConsole().append(retorno + "\n");
		
		return this.sessoes.get(idRede).getPingConsole().toString();
	}	
	
	public void limparConsole(String idRede) {
		this.sessoes.get(idRede).limparConsole();
	}
	
	public StringBuffer getConsole(String idRede) {
		return this.sessoes.get(idRede).getConsole();
	}
	
	public StringBuffer getPingConsole(String idRede) {
		return this.sessoes.get(idRede).getPingConsole();
	}
	
	private String gerarId() {
		StringBuffer sb = new StringBuffer();  
        Random rand = new Random();  
        for (int i = 0; i < 16; i++) {  
            sb.append(Integer.toString(Math.abs(rand.nextInt()) % 16, 16));  
        } 
        return sb.toString();
	}
	
//	class SessionCollectorTimerTask extends TimerTask {
//
//		@Override
//		public void run() {
//			long hora = new Date().getTime();
//			Collection<Rede> remover = new HashSet<Rede>();
//			for (Rede rede : SessionManager.this.sessoes.values()) {
//				//verifico se a rede ja existe ha mais de SessionManager.TEMPO_DE_VIDA_SESSAO
//				if (hora - (rede.getHoraDeCriacao()) > SessionManager.TEMPO_DE_VIDA_SESSAO) {
//					remover.add(rede);
//				}
//			}
//			for (Rede rede : remover) {
//				SessionManager.this.sessoes.remove(rede);
//			}
//		}
//
//	}
}
