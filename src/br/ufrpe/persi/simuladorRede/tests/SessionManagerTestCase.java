package br.ufrpe.persi.simuladorRede.tests;

import junit.framework.TestCase;
import br.ufrpe.persi.simuladorRede.controle.SessionManager;

public class SessionManagerTestCase extends TestCase {

	private String sessionId;
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		this.sessionId = SessionManager.getInstance().criarNovaRede();
		
		if (sessionId != null) {
			//crio os dispositivos
			try {
				SessionManager.getInstance().criarDispositivo(this.sessionId, "host1", "host", 1);
				SessionManager.getInstance().criarDispositivo(this.sessionId, "host2", "host", 1);
				SessionManager.getInstance().criarDispositivo(this.sessionId, "host3", "host", 1);
				SessionManager.getInstance().criarDispositivo(this.sessionId, "router1", "router", 32);
				SessionManager.getInstance().criarDispositivo(this.sessionId, "switch1", "switch", 8);
				SessionManager.getInstance().criarDispositivo(this.sessionId, "switch2", "switch", 8);				
			} catch (Exception e) {
				fail(e.getMessage());
			}
			
			//configuro os dispositivos
			try {
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "host1", "ip", "10.0.0.100");
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "host1", "mascara", "255.255.255.0");
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "host1", "gateway", "router1");
				
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "host2", "ip", "10.0.0.101");
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "host2", "mascara", "255.255.255.0");
				
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "host3", "ip", "10.1.0.100");
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "host3", "mascara", "255.255.255.0");
	
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "router1", "ip", "10.0.0.99");
				SessionManager.getInstance().alterarPropriedadeDispositivo(this.sessionId, "router1", "mascara", "255.255.255.0");
				SessionManager.getInstance().adicionarRota(this.sessionId, "router1", "10.1.0.0", "host3");
				
				//TODO Falta adicionar a rota
			} catch (Exception e) {
				fail(e.getMessage());
			}
			
			//conecto os dispositivos
			try {
				SessionManager.getInstance().conectarDispositivos(this.sessionId, "host1", "switch1");
				SessionManager.getInstance().conectarDispositivos(this.sessionId, "host2", "switch1");
				SessionManager.getInstance().conectarDispositivos(this.sessionId, "host3", "switch2");
				
				SessionManager.getInstance().conectarDispositivos(this.sessionId, "switch1", "router1");
				SessionManager.getInstance().conectarDispositivos(this.sessionId, "switch2", "router1");
			} catch (Exception e) {
				fail(e.getMessage());
			}
		} else {
			fail("Não foi possível obter uma nova sessão.");
		}
	}

	public void testEncaminharPacoteViaSwitch() {
		try {
			String retorno = SessionManager.getInstance().processarPacote(this.sessionId, "host1", "10.0.0.101", "ping");
			assertEquals(retorno, "Pacote de origem=10.0.0.100 e destino=10.0.0.101 enviado");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEncaminharPacoteViaRouter() {
		try {
			String retorno = SessionManager.getInstance().processarPacote(this.sessionId, "host1", "10.1.0.100", "ping");
			System.out.println(SessionManager.getInstance().getConsole());
			assertEquals(retorno, "Pacote de origem=10.0.0.100 e destino=10.1.0.100 enviado");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
