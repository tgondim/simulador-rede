package br.ufrpe.persi.simuladorRede.listeners;

public interface OnPacoteRecebidoObserver {

	public void addOnPacoteRecebidoListener(OnPacoteRecebidoListener newOnPacoteRecebidoListener);
	
	public void removeOnPacoteRecebidoListener(OnPacoteRecebidoListener newOnPacoteRecebidoListener);
	
	public void notificarOnPacoteRecebidoListeners();
	
}
