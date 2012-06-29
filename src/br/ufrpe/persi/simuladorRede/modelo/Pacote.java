package br.ufrpe.persi.simuladorRede.modelo;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.persi.simuladorRede.listeners.OnPacoteRecebidoListener;
import br.ufrpe.persi.simuladorRede.listeners.OnPacoteRecebidoObserver;


public class Pacote implements OnPacoteRecebidoObserver {
	
	private static final long TEMPO_DE_EXPIRACAO = 10000; 
	
	private EnderecoIP origem;
	private EnderecoIP destino;
	private String conteudo;
	private boolean entregue;
	private boolean expirado;
	private List<OnPacoteRecebidoListener> onPacoteRecebidoListeners;
	
	public Pacote(String conteudo) {
		super();
		this.onPacoteRecebidoListeners = new ArrayList<OnPacoteRecebidoListener>();
		this.conteudo = conteudo;
		this.entregue = false;
		this.expirado = false;
	}
	
	public Pacote(String conteudo, EnderecoIP newDestino) {
		this(conteudo);
		this.destino = newDestino;
	}
	
	public Pacote(String conteudo, EnderecoIP newOrigem, EnderecoIP newDestino) {
		this(conteudo, newDestino);
		this.origem = newOrigem;
	}

	public void addOnPacoteRecebidoListener(OnPacoteRecebidoListener newOnPacoteRecebidoListener) {
		this.onPacoteRecebidoListeners.add(newOnPacoteRecebidoListener);
	}
	
	public void removeOnPacoteRecebidoListener(OnPacoteRecebidoListener newOnPacoteRecebidoListener) {
		this.onPacoteRecebidoListeners.remove(newOnPacoteRecebidoListener);
	}
	
	@Override
	public void notificarOnPacoteRecebidoListeners() {		
		for (OnPacoteRecebidoListener oprl : this.onPacoteRecebidoListeners) {			
			oprl.onPacoteRecebido(this);
		}
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

	public boolean isEntregue() {
		return entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

	public boolean isExpirado() {
		return expirado;
	}

	public void setExpirado(boolean expirado) {
		this.expirado = expirado;
	}
	
	public void iniciarContagemTempoDeExpiracao() {
		new Thread(new TempoDeExpiracaoPacoteRunnable()).start();
	}

	class TempoDeExpiracaoPacoteRunnable implements Runnable {
		
		@Override
		public void run() {
			try {
				Thread.sleep(Pacote.TEMPO_DE_EXPIRACAO);				
			} catch (InterruptedException e) {
			}
			if (!Pacote.this.isEntregue()) {
				Pacote.this.setExpirado(true);
				notificarOnPacoteRecebidoListeners();
			}
		}
	}
}
