package br.ufrpe.persi.simuladorRede;

public interface Fachada {
	
	public void criarObjeto(String id,String tipoObjeto);
	public void deletarObjeto(String id);
	public void ping(String idOrigem, String ipDestino);

}
