package br.ufrpe.persi.simuladorRede;
import java.util.ArrayList;

public class Hub extends Dispositivo {
	
	public Hub(int newNumeroDeInterfaces) {
		super();
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
