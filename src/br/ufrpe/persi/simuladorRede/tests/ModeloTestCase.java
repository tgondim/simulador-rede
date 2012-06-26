package br.ufrpe.persi.simuladorRede.tests;


import junit.framework.TestCase;
import br.ufrpe.persi.simuladorRede.modelo.ConfiguracaoRede;
import br.ufrpe.persi.simuladorRede.modelo.EnderecoIP;
import br.ufrpe.persi.simuladorRede.modelo.Host;
import br.ufrpe.persi.simuladorRede.modelo.Pacote;
import br.ufrpe.persi.simuladorRede.modelo.Router;
import br.ufrpe.persi.simuladorRede.modelo.Switch;
import br.ufrpe.persi.simuladorRede.modelo.exception.EnderecoIPMalFormadoException;
import br.ufrpe.persi.simuladorRede.modelo.exception.ImpossivelCriarDispositivoExeption;

public class ModeloTestCase extends TestCase{

	
	private Host host1;
	private Host host2;
	private Host host3;
	private Switch switch1;
	private Switch switch2;
	private Router router1;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		host1 = new Host("host1", 1);
		host1.setConfiguracao(new ConfiguracaoRede());
		host1.getConfiguracao().setIp(new EnderecoIP("10.0.0.100"));
		host1.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));
		
		host2 = new Host("host2", 1);
		host2.setConfiguracao(new ConfiguracaoRede());
		host2.getConfiguracao().setIp(new EnderecoIP("10.0.0.101"));
		host2.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));

		host3 = new Host("host3", 1);
		host3.setConfiguracao(new ConfiguracaoRede());
		host3.getConfiguracao().setIp(new EnderecoIP("10.1.0.100"));
		host3.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));
		
		router1 = new Router("router1", 32);
		router1.setConfiguracao(new ConfiguracaoRede());
		router1.getConfiguracao().setIp(new EnderecoIP("10.0.0.99"));
		router1.getConfiguracao().setMascara(new EnderecoIP("255.255.255.0"));
		router1.adicionarRota(new EnderecoIP("10.1.0.0"), host3);
		host1.getConfiguracao().setGateway(router1);
		
		switch1 = new Switch("switch1", 8);
		switch2 = new Switch("switch2", 8);
		
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
			fail(e.getMessage());
		}
	}
	
	public void testEncaminharPacoteViaSwitch() {
		Pacote pacote;
		try {
			pacote = new Pacote("Pacote enviado pelo switch", new EnderecoIP("10.0.0.101"));
			host1.enviarPacote(pacote);
		} catch (EnderecoIPMalFormadoException e) {
			fail(e.getMessage());
		}
		assertEquals(host2.getPacote().getConteudo(), "Pacote enviado pelo switch");
	}

	public void testEncaminharPacoteViaRouter() {
		Pacote pacote;
		try {
			pacote = new Pacote("Pacote enviado pelo router", new EnderecoIP("10.1.0.100"));
			host1.enviarPacote(pacote);
		} catch (EnderecoIPMalFormadoException e) {
			fail(e.getMessage());
		}
		assertEquals(host3.getPacote().getConteudo(), "Pacote enviado pelo router");
	}
}
