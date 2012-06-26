package br.ufrpe.persi.simuladorRede;

import br.ufrpe.persi.simuladorRede.controle.SessionManager;
import br.ufrpe.persi.simuladorRede.modelo.ConfiguracaoRede;
import br.ufrpe.persi.simuladorRede.modelo.EnderecoIP;
import br.ufrpe.persi.simuladorRede.modelo.Host;
import br.ufrpe.persi.simuladorRede.modelo.Pacote;
import br.ufrpe.persi.simuladorRede.modelo.Router;
import br.ufrpe.persi.simuladorRede.modelo.Switch;
import br.ufrpe.persi.simuladorRede.modelo.exception.EnderecoIPMalFormadoException;
import br.ufrpe.persi.simuladorRede.modelo.exception.ImpossivelCriarDispositivoExeption;

public class Main {

	public static void main(String[] args) throws EnderecoIPMalFormadoException {
		
		String id = SessionManager.getInstance().criarNovaRede();
		System.out.println(id);
		
//		Rede rede = SessionManager.getInstance().getRede(id);
		
//		System.out.println(rede);

		Host host1 = new Host("host1", 1);
		host1.setConfiguracao(new ConfiguracaoRede());
		host1.getConfiguracao().setIp(new EnderecoIP("10.0.0.100"));
		host1.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));
		
		Host host2 = new Host("host2", 1);
		host2.setConfiguracao(new ConfiguracaoRede());
		host2.getConfiguracao().setIp(new EnderecoIP("10.0.0.101"));
		host2.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));

		Host host3 = new Host("host3", 1);
		host3.setConfiguracao(new ConfiguracaoRede());
		host3.getConfiguracao().setIp(new EnderecoIP("10.1.0.100"));
		host3.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));
		
		Router router1 = new Router("router1", 32);
		router1.setConfiguracao(new ConfiguracaoRede());
		router1.getConfiguracao().setIp(new EnderecoIP("10.0.0.99"));
		router1.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));
		router1.adicionarRota(new EnderecoIP("10.1.0.0"), host3);
		host1.getConfiguracao().setGateway(router1);
		
		Switch switch1 = new Switch("switch1", 8);
		Switch switch2 = new Switch("switch2", 8);
		
		try {
			switch1.conectarDispositivo(host1);
			host1.conectarDispositivo(switch1);
			
			switch1.conectarDispositivo(host2);
			host2.conectarDispositivo(switch1);
			
			switch2.conectarDispositivo(host3);
			host3.conectarDispositivo(switch2);
			
			router1.conectarDispositivo(switch1);
			switch1.conectarDispositivo(router1);
			
			router1.conectarDispositivo(switch2);
			switch2.conectarDispositivo(router1);
		} catch (ImpossivelCriarDispositivoExeption e) {
			e.printStackTrace();
		}
		
		Pacote pacote1 = new Pacote("Pacote enviado pelo switch", new EnderecoIP("10.0.0.101"));
		Pacote pacote2 = new Pacote("Pacote enviado pelo router", new EnderecoIP("10.1.0.100"));
		host1.enviarPacote(pacote1);
		host1.enviarPacote(pacote2);
		System.out.println(host2.getPacote().getConteudo());
		System.out.println(host3.getPacote().getConteudo());
		
		////////////////////////////////////////////////////
	}
}
