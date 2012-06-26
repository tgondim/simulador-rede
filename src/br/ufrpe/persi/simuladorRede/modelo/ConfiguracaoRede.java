package br.ufrpe.persi.simuladorRede.modelo;


public class ConfiguracaoRede {
	
	private EnderecoIP ip;
	private EnderecoIP mascara;
	private Dispositivo gateway;
//	private Dispositivo dns1;
//	private Dispositivo dns2;
	
	public ConfiguracaoRede(EnderecoIP ip, EnderecoIP mascara, Dispositivo gateway,
			Dispositivo dns1, Dispositivo dns2) {
		super();
		this.ip = ip;
		this.mascara = mascara;
		this.gateway = gateway;
//		this.dns1 = dns1;
//		this.dns2 = dns2;
	}
	
	public ConfiguracaoRede() {
		super();
	}

	public EnderecoIP getNomeRede(EnderecoIP enderecoIP) {
		int[] auxNomeRede = new int[4];
		for (int i = 0; i <= 3; i++) {
			auxNomeRede[i] = (enderecoIP.getEndereco()[i] & this.mascara.getEndereco()[i]);
		}		
		return new EnderecoIP(auxNomeRede);
	}
	
	public boolean mesmaRede(EnderecoIP enderecoIP) {
		int[] auxNomeRede = new int[4];
		for (int i = 0; i <= 3; i++) {
			auxNomeRede[i] = (enderecoIP.getEndereco()[i] & this.mascara.getEndereco()[i]);
		}			
		return new EnderecoIP(auxNomeRede).equals(this.getNomeRede(this.ip));
	}
	
	public EnderecoIP getIp() {
		return ip;
	}

	public void setIp(EnderecoIP ip) {
		this.ip = ip;
	}

	public EnderecoIP getMascara() {
		return mascara;
	}

	public void setMascara(EnderecoIP mascara) {
		this.mascara = mascara;
	}

	public Dispositivo getGateway() {
		return gateway;
	}

	public void setGateway(Dispositivo gateway) {
		this.gateway = gateway;
	}

//	public Dispositivo getDns1() {
//		return dns1;
//	}
//
//	public void setDns1(Dispositivo dns1) {
//		this.dns1 = dns1;
//	}
//
//	public Dispositivo getDns2() {
//		return dns2;
//	}
//
//	public void setDns2(Dispositivo dns2) {
//		this.dns2 = dns2;
//	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConfiguracaoRede) {
			return this.getIp().equals(((ConfiguracaoRede)obj).getIp());
		}
		return false;
	}
}
