package br.ufrpe.persi.simuladorRede.modelo;
import java.util.ArrayList;


public class Switch extends Dispositivo {
	
	public Switch(String newId, int newNumeroDeInterfaces) {
		super(newId);
		this.dispositivosConectados = new ArrayList<Dispositivo>();
		this.numeroDeInterfaces = newNumeroDeInterfaces;
	}

	@Override
	public void processarPacote(Pacote pacote) {
		for(int i = 0 ; i < dispositivosConectados.size() ; i++){
			//Dispositivos na mesma rede
			if(dispositivosConectados.get(i).getConfiguracao().getIp().equals(pacote.getDestino())){
				dispositivosConectados.get(i).processarPacote(pacote);
				return;
			}
		}
		return;
	}
}
