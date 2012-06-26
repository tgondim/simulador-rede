package br.ufrpe.persi.simuladorRede.modelo;
import java.util.List;

import br.ufrpe.persi.simuladorRede.modelo.exception.ImpossivelCriarDispositivoExeption;


public abstract class Dispositivo {

	protected String id;
	protected ConfiguracaoRede configuracao;
	protected List<Dispositivo> dispositivosConectados;
	protected int numeroDeInterfaces;
	protected String macAddress;
	
	public abstract void processarPacote(Pacote pacote);

	public void conectarDispositivo(Dispositivo dispositivo) throws ImpossivelCriarDispositivoExeption {
		if (this.dispositivosConectados.size() < this.getNumeroDeInterfaces()) {
			this.dispositivosConectados.add(dispositivo);
		} else {
			throw new ImpossivelCriarDispositivoExeption("Não há interface de rede disponível.");
		}
	}
	
	public Dispositivo(String newId) {
		this.id = newId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Dispositivo> getDispositivosConectados() {
		return dispositivosConectados;
	}

	public void setDispositivosConectados(List<Dispositivo> dispositivosConectados) {
		this.dispositivosConectados = dispositivosConectados;
	}

	public void setNumeroDeInterfaces(int numeroDeInterfaces) {
		this.numeroDeInterfaces = numeroDeInterfaces;
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
	
	@Override
	public String toString() {
		return this.id;
	}
}
