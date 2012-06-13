package br.ufrpe.persi.simuladorRede;

import java.util.StringTokenizer;

public class EnderecoIP {

	private int[] endereco;
	
	public EnderecoIP() {
		this.endereco = new int[4];
	}

	public EnderecoIP(String newEndereco) throws EnderecoIPMalFormadoException {
		this();
		this.endereco = parseEnderecoIP(newEndereco);
	}

	public EnderecoIP(int[] newEndereco) {
		this();
		this.endereco = newEndereco;
	}
	
	public int[] getEndereco() {
		return this.endereco;
	}

	public void setEndereco(int[] newEndereco) {
		this.endereco = newEndereco;
	}
	
	public static int[] parseEnderecoIP(String newEndereco) throws EnderecoIPMalFormadoException {
		int[] enderecoAux = new int[4];
		
		StringTokenizer st = new StringTokenizer(newEndereco, ".");
		if (st.countTokens() > 4) {
			throw new EnderecoIPMalFormadoException("Endereço IP com formato incorreto.");
		}
		int i = 0;
		while (st.hasMoreTokens()) {
			enderecoAux[i] = Integer.valueOf(st.nextToken());
			i++;
		}
		return enderecoAux;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EnderecoIP)) {
			return false;
		}
		EnderecoIP auxObj = (EnderecoIP)obj;
		for (int i = 0; i <= 3; i++) {
			if (this.endereco[i] != auxObj.endereco[i]) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0 ; i <= 3 ; i++) {
			sb.append(this.endereco[i]);
			if (i < 3) { 
				sb.append(".");
			}
		}
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (int fragmentoIp : this.endereco) {
			hashCode += fragmentoIp;
		}
		return hashCode;
	}
}
