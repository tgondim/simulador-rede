package br.ufrpe.persi.simuladorRede.modelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Router extends Dispositivo {
	
	private Map<EnderecoIP, Dispositivo> tabelaRoteamento;
	
	public Router(String newId, int newNumeroDeInterfaces) {
		super(newId);
		this.dispositivosConectados = new ArrayList<Dispositivo>();
		this.tabelaRoteamento = new HashMap<EnderecoIP, Dispositivo>();
		this.numeroDeInterfaces = newNumeroDeInterfaces;
	}

	@Override
	public void processarPacote(Pacote pacote) {
		if (this.getConfiguracao().mesmaRede(pacote.getDestino())) {				
			for (Dispositivo disp : this.dispositivosConectados) {
//				if(disp.getConfiguracao().getIp().equals(pacote.getDestino())) {
					disp.processarPacote(pacote);
//					return;
//				}
			}
		} else {
			EnderecoIP nomeRede = this.configuracao.getNomeRede(pacote.getDestino());
			if (this.tabelaRoteamento.containsKey(nomeRede)) {
				this.tabelaRoteamento.get(nomeRede).processarPacote(pacote);
			} else {
				//TODO tratar o caso de não haver rota
			}
		}
	}
	
	public void adicionarRota(EnderecoIP newRede, Dispositivo newDispositivo) {
		this.tabelaRoteamento.put(newRede, newDispositivo);
	}
}
