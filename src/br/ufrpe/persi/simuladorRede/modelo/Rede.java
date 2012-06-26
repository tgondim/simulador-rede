package br.ufrpe.persi.simuladorRede.modelo;

import java.util.ArrayList;

public class Rede {

	private String id;
	private ArrayList<Dispositivo> dispositivos;
	
	public Rede(String newId) {
		this.id = newId;
		this.dispositivos = new ArrayList<Dispositivo>();
	}
	
	public void addDispositivo(Dispositivo newDispositivo) {
		this.dispositivos.add(newDispositivo);
	}
	
	public void removeDispositivo(Dispositivo newDispositivo) {
		this.dispositivos.remove(newDispositivo);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		for (Dispositivo disp : this.dispositivos) {
			sb.append(disp.toString() + "\n");
		}
		return sb.toString();
	}
}
