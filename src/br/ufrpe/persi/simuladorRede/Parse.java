package br.ufrpe.persi.simuladorRede;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
	
	private String [] tokens;
	
	//Exemplo: ping 10.193.0.1
	private final String COMANDOVALIDO = "[a-z]+\\s{1,1}\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}\\s{1,1}[-][a-z]{1,1}";

	public Parse() {
		super();
		this.tokens = new String[3];
	}
	
	public void recebeComando(String comando) throws ComandoInvalidoException{
		
		verificaComando(comando);
		
		//Verifica a funcao
		switch (this.tokens[0]) {
		
		case "ping":
			
			System.out.println("ping");
			break;
		
		default:
			throw new ComandoInvalidoException("Funcão não encontrada.");
		}
		
	}
	
	private void separaToken(String comando){
		
		this.tokens = comando.split(" ");
		
	}
	
	private boolean verificaComando(String comando) throws ComandoInvalidoException{
		
		Pattern pattern = Pattern.compile(COMANDOVALIDO);
        Matcher matcher = pattern.matcher(comando);
        
        //Verifica se o comando e valido. Caso sim, separa tokens e retorna true.
        //Caso nao, levanta exception
        if( matcher.matches() ){
        	separaToken(comando);
        	return true;
        }else{
            throw new ComandoInvalidoException("Comando inválido!");
        }
		
	}
	
}
