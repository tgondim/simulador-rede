package br.ufrpe.persi.simuladorRede.modelo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Rede {

	private String id;
	private Map<String, Dispositivo> dispositivos;
	private long horaDeCriacao;
	
	public Rede(String newId) {
		this.id = newId;
		this.dispositivos = new HashMap<String, Dispositivo>();
		this.horaDeCriacao = new Date().getTime();
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
