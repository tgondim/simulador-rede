package br.ufrpe.persi.simuladorRede;

public class Pacote {
	
	private EnderecoIP origem;
	private EnderecoIP destino;
	private String conteudo;
	
	public Pacote(String conteudo) {
		super();
		this.conteudo = conteudo;
	}
	
	public Pacote(String conteudo, EnderecoIP newDestino) {
		this(conteudo);
		this.destino = newDestino;
	}
	
	public Pacote(String conteudo, EnderecoIP newOrigem, EnderecoIP newDestino) {
		this(conteudo, newDestino);
		this.origem = newOrigem;
	}

	public EnderecoIP getOrigem() {
		return origem;
	}

	public void setOrigem(EnderecoIP origem) {
		this.origem = origem;
	}

	public EnderecoIP getDestino() {
		return destino;
	}

	public void setDestino(EnderecoIP destino) {
		this.destino = destino;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
