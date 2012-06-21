package br.ufrpe.persi.simuladorRede;

public class FachadaImplements implements Fachada {
	
	private static FachadaImplements instancia;
	
	private FachadaImplements(){
		
	}
	
	public static FachadaImplements getInstancia(){
		
		if(instancia == null ){
			instancia = new FachadaImplements();
		}
		
		return instancia;
	
	}

	@Override
	public void criarObjeto(String id, String tipoObjeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletarObjeto(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ping(String idOrigem, String comando) {
		// TODO Auto-generated method stub

	}

}
