package br.ufrpe.persi.simuladorRede;

public class Pacote {
	
	private Dispositivo origem;
	private Dispositivo destino;
	private String conteudo;
	
	public Pacote(String conteudo) {
		super();
		this.conteudo = conteudo;
	}
	
	public Pacote(String conteudo, Dispositivo newDestino) {
		this(conteudo);
		this.destino = newDestino;
	}
	
	public Pacote(String conteudo, Dispositivo newOrigem, Dispositivo newDestino) {
		this(conteudo, newDestino);
		this.origem = newOrigem;
	}

	public Dispositivo getOrigem() {
		return origem;
	}

	public void setOrigem(Dispositivo origem) {
		this.origem = origem;
	}

	public Dispositivo getDestino() {
		return destino;
	}

	public void setDestino(Dispositivo destino) {
		this.destino = destino;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
