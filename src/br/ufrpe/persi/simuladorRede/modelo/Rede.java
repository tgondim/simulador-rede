package br.ufrpe.persi.simuladorRede.modelo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.ufrpe.persi.simuladorRede.listeners.OnPacoteRecebidoListener;

public class Rede implements OnPacoteRecebidoListener{

	private String id;
	private Map<String, Dispositivo> dispositivos;
	private long horaDeCriacao;
	private StringBuffer console;
	private StringBuffer pingConsole;
	
	public Rede(String newId) {
		this.id = newId;
		this.dispositivos = new HashMap<String, Dispositivo>();
		this.horaDeCriacao = new Date().getTime();
		this.console = new StringBuffer();
		this.pingConsole = new StringBuffer();
	}
	
	public void addDispositivo(Dispositivo newDispositivo) {
		this.dispositivos.put(newDispositivo.getId(), newDispositivo);
	}
	
	public void removeDispositivo(Dispositivo newDispositivo) {
		this.dispositivos.remove(newDispositivo);
	}
	
	public Map<String, Dispositivo> getDispositivos() {
		return this.dispositivos;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public long getHoraDeCriacao() {
		return this.horaDeCriacao;
	}

	public void limparConsole() {
		this.console = new StringBuffer();
		this.pingConsole = new StringBuffer();
	}
	
	public StringBuffer getConsole() {
		return this.console;
	}
	
	public StringBuffer getPingConsole() {
		return this.pingConsole;
	}
	
	@Override
	public void onPacoteRecebido(Pacote pacote) {
		if (pacote.isEntregue()) {
			this.console.append("PING - Pacote origem=" + pacote.getOrigem() + " destino=" + pacote.getDestino() + " entregue com sucesso.\n");
			this.pingConsole.append("PING - Pacote origem=" + pacote.getOrigem() + " destino=" + pacote.getDestino() + " entregue com sucesso.\n");
		} else if (pacote.isExpirado()) {
			this.console.append("PING - Pacote origem=" + pacote.getOrigem() + " destino=" + pacote.getDestino() + " NÃO foi entregue.\nTempo limite esgotado.\n");
			this.pingConsole.append("PING - Pacote origem=" + pacote.getOrigem() + " destino=" + pacote.getDestino() + " NÃO foi entregue.\nTempo limite esgotado.\n");
		} else {
			//este caso nao deve ocorrer
			throw new NotImplementedException();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Rede)) {
			return false;
		}
		return ((Rede)obj).getId().equals(this.id);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Dispositivo disp : this.dispositivos.values()) {
			sb.append(disp.toString() + "\n");
		}
		return sb.toString();
	}
}
