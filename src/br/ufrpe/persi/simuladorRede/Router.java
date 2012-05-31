package br.ufrpe.persi.simuladorRede;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Router extends Dispositivo {
	
	private Map<Long, Dispositivo> tabelaRoteamento;
	
	public Router(int newNumeroDeInterfaces) {
		super();
		this.dispositivosConectados = new ArrayList<Dispositivo>();
		this.tabelaRoteamento = new HashMap<Long, Dispositivo>();
		this.numeroDeInterfaces = newNumeroDeInterfaces;
	}

	@Override
	public void processarPacote(Pacote pacote) {
		if (pacote.getOrigem().getNomeRede() == pacote.getDestino().getNomeRede()) {
			for (Dispositivo disp : this.dispositivosConectados) {
				if(disp.getConfiguracao().getIp().equals(pacote.getDestino().getConfiguracao().getIp())) {
					disp.processarPacote(pacote);
					return;
				}
			}
		} else {
			if (this.tabelaRoteamento.containsKey(pacote.getDestino().getNomeRede())) {
				this.tabelaRoteamento.get(pacote.getDestino().getNomeRede()).processarPacote(pacote);
			} else {
				//TODO tratar o caso de não haver rota
			}
		}
	}
	
	public void adicionarRota(Long newRede, Dispositivo newDispositivo) {
		this.tabelaRoteamento.put(newRede, newDispositivo);
	}
}
