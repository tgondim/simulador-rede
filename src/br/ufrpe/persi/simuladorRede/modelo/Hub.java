package br.ufrpe.persi.simuladorRede.modelo;
import java.util.ArrayList;


public class Hub extends Dispositivo {
	
	public Hub(String newId, int newNumeroDeInterfaces) {
		super(newId);
		this.dispositivosConectados = new ArrayList<Dispositivo>();
		this.numeroDeInterfaces = newNumeroDeInterfaces;
	}

	@Override
	public void processarPacote(Pacote pacote) {
		for(Dispositivo disp : dispositivosConectados){
			disp.processarPacote(pacote);
		}
	}
}
