package br.ufrpe.persi.simuladorRede;

public class Main {

	public static void main(String[] args) {
		
		Host host1 = new Host(1);
		ConfiguracaoRede configuracaoHost1 = new ConfiguracaoRede();
		configuracaoHost1.setIp("10.0.0.100");
		configuracaoHost1.setMascara("255.255.255.0");
		host1.setConfiguracao(configuracaoHost1);
		
		Host host2 = new Host(1);
		ConfiguracaoRede configuracaoHost2 = new ConfiguracaoRede();
		configuracaoHost2.setIp("10.0.0.101");
		configuracaoHost2.setMascara("255.255.255.0");
		host2.setConfiguracao(configuracaoHost2);

		Host host3 = new Host(1);
		ConfiguracaoRede configuracaoHost3 = new ConfiguracaoRede();
		configuracaoHost3.setIp("10.1.0.100");
		configuracaoHost3.setMascara("255.255.255.0");
		host3.setConfiguracao(configuracaoHost3);
		
		Router router1 = new Router(32);
		ConfiguracaoRede configuracaoRouter = new ConfiguracaoRede();
		configuracaoRouter.setIp("10.0.0.99");
		configuracaoRouter.setMascara("255.255.255.0");
		router1.setConfiguracao(configuracaoRouter);
		router1.adicionarRota(Long.valueOf("10.1.0.100".replace(".", "")), host3);
		
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
		
		Pacote pacote1 = new Pacote("Pacote enviado pelo switch", host2);
		Pacote pacote2 = new Pacote("Pacote enviado pelo router", host3);
		host1.enviarPacote(pacote1);
		host1.enviarPacote(pacote2);
		System.out.println(host2.getPacote().getConteudo());
		System.out.println(host3.getPacote().getConteudo());
		
		////////////////////////////////////////////////////
	}
}
