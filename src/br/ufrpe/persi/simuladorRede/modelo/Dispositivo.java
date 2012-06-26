package br.ufrpe.persi.simuladorRede.modelo;
import java.util.List;

import br.ufrpe.persi.simuladorRede.ImpossivelConectarDispositivoExeption;

public abstract class Dispositivo {

	protected String id;
	protected ConfiguracaoRede configuracao;
	protected List<Dispositivo> dispositivosConectados;
	protected int numeroDeInterfaces;
	protected String macAddress;
	
	public abstract void processarPacote(Pacote pacote);

	public void conectarDispositivo(Dispositivo dispositivo) throws ImpossivelConectarDispositivoExeption {
		if (this.dispositivosConectados.size() < this.getNumeroDeInterfaces()) {
			this.dispositivosConectados.add(dispositivo);
		} else {
			throw new ImpossivelConectarDispositivoExeption("Não há interface de rede disponível.");
		}
	}
	
	public Dispositivo(String newId) {
		this.id = newId;
	}
	
	public boolean desconectarDispositivo(Dispositivo dispositivo) {
		return this.dispositivosConectados.remove(dispositivo);
	}
	
	public int getNumeroDeInterfaces() {
		return this.numeroDeInterfaces;
	}
	
	public ConfiguracaoRede getConfiguracao() {
		return configuracao;
	}
	
	public void setConfiguracao(ConfiguracaoRede configuracao) {
		this.configuracao = configuracao;
	}
	
	public String getMacAddress() {
		return macAddress;
	}
	
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dispositivo) {
			return this.id.equals(((Dispositivo)obj).id);		
		}
		return false;
	}	
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (int fragmentoIp : this.getConfiguracao().getIp().getEndereco()) {
			hashCode += fragmentoIp;
		}
		return hashCode;
	}
}
