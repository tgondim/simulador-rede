package br.ufrpe.persi.simuladorRede;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
	
	private String [] token;
	
	public Parse() {
		super();
	}
	
	public void recebeComando(String comando) throws ComandoInvalidoException, EnderecoIPMalFormadoException{
		
		this.token = comando.split(" ");
		
		//Verifica a funcao
		switch (this.token[0]) {
			
		case "ping":
			
			verificaIp(token[1]);
			
			
			break;
		
		default:
			throw new ComandoInvalidoException("Funcão não encontrada.");
		}
		
	}
	
	
	private boolean verificaIp(String ip) throws EnderecoIPMalFormadoException{
		
		Pattern pattern = Pattern.compile("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}");
        Matcher matcher = pattern.matcher(ip);
        
        //Verifica se o ip e valido. Caso sim, retorna true.
        //Caso nao, levanta exception
        if( matcher.matches() ){
        	return true;
        }else{
            throw new EnderecoIPMalFormadoException("IP inválido");
        }
		
	}
	
}
