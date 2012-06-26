package br.ufrpe.persi.simuladorRede.modelo;


public class ConfiguracaoRede {
	
	private EnderecoIP ip;
	private EnderecoIP mascara;
	private Dispositivo gateway;
	
	public ConfiguracaoRede(EnderecoIP ip, EnderecoIP mascara, Dispositivo gateway,
			Dispositivo dns1, Dispositivo dns2) {
		super();
		this.ip = ip;
		this.mascara = mascara;
		this.gateway = gateway;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConfiguracaoRede) {
			return this.getIp().equals(((ConfiguracaoRede)obj).getIp());
		}
		return false;
	}
}
