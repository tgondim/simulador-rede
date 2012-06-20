package br.ufrpe.persi.simuladorRede;

public class Main {

	public static void main(String[] args) throws EnderecoIPMalFormadoException {
		
		Host host1 = new Host(1);
		ConfiguracaoRede configuracaoHost1 = new ConfiguracaoRede();
		configuracaoHost1.setIp(new EnderecoIP("10.0.0.100"));
		configuracaoHost1.setMascara(new EnderecoIP("255.255.255.0"));
		host1.setConfiguracao(configuracaoHost1);
		
		Host host2 = new Host(1);
		ConfiguracaoRede configuracaoHost2 = new ConfiguracaoRede();
		configuracaoHost2.setIp(new EnderecoIP("10.0.0.101"));
		configuracaoHost2.setMascara(new EnderecoIP("255.255.255.0"));
		host2.setConfiguracao(configuracaoHost2);

		Host host3 = new Host(1);
		ConfiguracaoRede configuracaoHost3 = new ConfiguracaoRede();
		configuracaoHost3.setIp(new EnderecoIP("10.1.0.100"));
		configuracaoHost3.setMascara(new EnderecoIP("255.255.255.0"));
		host3.setConfiguracao(configuracaoHost3);
		
		Router router1 = new Router(32);
		ConfiguracaoRede configuracaoRouter = new ConfiguracaoRede();
		configuracaoRouter.setIp(new EnderecoIP("10.0.0.99"));
		configuracaoRouter.setMascara(new EnderecoIP("255.255.255.0"));
		router1.setConfiguracao(configuracaoRouter);
		router1.adicionarRota(new EnderecoIP("10.1.0.100"), host3);
		
		Switch switch1 = new Switch(8);
		Switch switch2 = new Switch(8);
		
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
		} catch (ImpossivelConectarDispositivoExeption e) {
			e.printStackTrace();
		}
		
		Pacote pacote1 = new Pacote("Pacote enviado pelo switch", configuracaoHost2.getIp());
		Pacote pacote2 = new Pacote("Pacote enviado pelo router", configuracaoHost3.getIp());
		host1.enviarPacote(pacote1);
		host1.enviarPacote(pacote2);
		System.out.println(host2.getPacote().getConteudo());
		System.out.println(host3.getPacote().getConteudo());
		
		////////////////////////////////////////////////////
	}
}
