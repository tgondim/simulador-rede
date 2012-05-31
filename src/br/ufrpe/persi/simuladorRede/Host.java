package br.ufrpe.persi.simuladorRede;
import java.util.ArrayList;

public class Host extends Dispositivo {
	
	private Pacote pacote;
	
	public Host(int newNumeroDeInterfaces) {
		super();
		this.dispositivosConectados = new ArrayList<Dispositivo>();
		this.numeroDeInterfaces = newNumeroDeInterfaces;
	}
	
	@Override
	public void processarPacote(Pacote pacote) {
		//Verifica se o dispositivo enviou para ele mesmo
		if(pacote.getDestino().getConfiguracao().getIp().equals(this.getConfiguracao().getIp())){
			this.setPacote(pacote);
		}
	}
	
	public void enviarPacote(Pacote pacote) {
		// Verifica se o dispositivo esta conectado a outro dispositivo
		pacote.setOrigem(this);
		Dispositivo disp = null;
		for (int i = 0; i < this.dispositivosConectados.size(); i++) {
			if((disp = this.dispositivosConectados.get(i)) != null){
				disp.processarPacote(pacote);
				return;
			}		
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
