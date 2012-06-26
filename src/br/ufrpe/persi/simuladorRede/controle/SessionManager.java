package br.ufrpe.persi.simuladorRede.controle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.ufrpe.persi.simuladorRede.modelo.Dispositivo;
import br.ufrpe.persi.simuladorRede.modelo.Host;
import br.ufrpe.persi.simuladorRede.modelo.Hub;
import br.ufrpe.persi.simuladorRede.modelo.Rede;
import br.ufrpe.persi.simuladorRede.modelo.Router;
import br.ufrpe.persi.simuladorRede.modelo.Switch;

public class SessionManager {

	private static SessionManager instance;
	
	private Map<String, Rede> sessoes;
	
	static {
		SessionManager.instance = new SessionManager();
	}
	
	private SessionManager() {
		super();
		this.sessoes = new HashMap<String, Rede>();		
	}
	
	public static SessionManager getInstance() {
		return instance;
	}
	
	public String criarNovaRede() {
		String newId = gerarId();
		this.sessoes.put(newId, new Rede(newId));
		return newId;
	}
	
	public Rede getRede(String id) {
		return this.sessoes.get(id);
	}
	
	public String criarDispositivo(String idRede, String nomeDispositivo, String tipoDispositivo, int numInterfaces) {
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
			//tratar aqui caso nao consiga criar o dispositivo
		}
		return "Objeto criado com sucesso";
	}
	
	public String alterarPropriedadeDispositivo(String idRede, String nomeDispositivo, String nome, String valor) {
		//TODO implementar alterar propriedades
		System.out.println("idRede=" + idRede + " nomeDispositivo=" + nomeDispositivo + " nome=" + nome + " valor=" + valor);
		return "";
	}
	
	public String processarPacote(String idRede, String nomeOrigem, String ipDestino, String conteudo) {
		//TODO implementar enviar pacote
		System.out.println("idRede=" + idRede + " nomeOrigem=" + nomeOrigem + " ipDestino=" + ipDestino + " conteudo=" + conteudo);
		return "";
	}
	
	private String gerarId() {
		StringBuffer sb = new StringBuffer();  
        Random rand = new Random();  
        for (int i = 0; i < 16; i++) {  
            sb.append(Integer.toString(Math.abs(rand.nextInt()) % 16, 16));  
        } 
        return sb.toString();
	}
}
