package br.ufrpe.persi.simuladorRede;

public interface Fachada {
	
	//Metodo executado ao arastar e soltar um objeto
	public void criarObjeto(String id,String tipoObjeto);
	//Metodo executado ao arastar um objeto para lixeira
	public void deletarObjeto(String id);
	//Enviar como parametro o id do objeto selecionado e o mando enviado por ele
	//nesse caso: ping xxx.xxx.xxx.xxx
	public void ping(String idOrigem, String comando);

}
