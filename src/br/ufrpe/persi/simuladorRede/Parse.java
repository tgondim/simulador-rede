package br.ufrpe.persi.simuladorRede;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufrpe.persi.simuladorRede.modelo.exception.ComandoInvalidoException;
import br.ufrpe.persi.simuladorRede.modelo.exception.EnderecoIPMalFormadoException;

public class Parse {
	
	private String [] token;
	
	public Parse() {
		super();
	}
	
	public void recebeComando(String comando) throws ComandoInvalidoException, EnderecoIPMalFormadoException{
		
		this.token = comando.split(" ");
		
		//Verifica a funcao
		if(this.token[0].equals("ping")){
			
			verificaIp(token[1]);
			
		}else{
			throw new ComandoInvalidoException("Funcão não encontrada.");
		}
		
	}
	
	
	private void verificaIp(String ip) throws EnderecoIPMalFormadoException{
		
		Pattern pattern = Pattern.compile("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}");
        Matcher matcher = pattern.matcher(ip);
        
        //Verifica se o ip e valido. Caso sim, retorna true.
        //Caso nao, levanta exception
        if( !matcher.matches() ){
        	throw new EnderecoIPMalFormadoException("IP inválido");
        }
        
        
        String [] token = ip.split(".");
        
        
        for (int i = 0; i < token.length; i++) {
        	
        	int num = Integer.parseInt(token[i]);
			//Verifica se o token local é maior que 2 ou menor que 0.
        	if(num > 254 || num < 0){
        		 throw new EnderecoIPMalFormadoException("IP inválido");
			}
		}
		
	}
	
}
