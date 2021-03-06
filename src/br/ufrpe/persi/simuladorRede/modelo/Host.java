package br.ufrpe.persi.simuladorRede.modelo;
import java.util.ArrayList;


public class Host extends Dispositivo {
	
	private Pacote pacote;
	
	public Host(String newId, int newNumeroDeInterfaces) {
		super(newId);
		this.dispositivosConectados = new ArrayList<Dispositivo>();
		this.numeroDeInterfaces = newNumeroDeInterfaces;
	}
	
	@Override
	public void processarPacote(Pacote pacote) {
		//Verifica se o dispositivo enviou para ele mesmo
		if(pacote.getDestino().equals(this.getConfiguracao().getIp())){
			this.setPacote(pacote);
			pacote.setEntregue(true);
			pacote.notificarOnPacoteRecebidoListeners();
		}
	}
	
	public void enviarPacote(Pacote pacote) {
		//inicio a contagem do tempo de vida do pacote
		pacote.iniciarContagemTempoDeExpiracao();
		
		// Verifica se o dispositivo esta conectado a outro dispositivo
		pacote.setOrigem(this.getConfiguracao().getIp());
		Dispositivo disp = null;
		if(this.getConfiguracao().getIp().equals(pacote.getDestino())){
			pacote.setEntregue(true);
			pacote.notificarOnPacoteRecebidoListeners();
			return;
		}
		for (int i = 0; i < this.dispositivosConectados.size(); i++) {
			if((disp = this.dispositivosConectados.get(i)) != null){
//				if (disp.getConfiguracao().getIp().equals(pacote.getDestino())) {
					disp.processarPacote(pacote);
//					return;
//				}
			}		
		}
		if ((!pacote.isEntregue()) && (this.getConfiguracao().getGateway() != null)) {
			this.getConfiguracao().getGateway().processarPacote(pacote);
		}
		return;
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
	
}
