package br.ufrpe.persi.simuladorRede;

public class ConfiguracaoRede {
	
	private String ip;
	private String mascara;
	private String gateway;
	private String dns1;
	private String dns2;
	
	public ConfiguracaoRede(String ip, String mascara, String gateway,
			String dns1, String dns2) {
		super();
		this.ip = ip;
		this.mascara = mascara;
		this.gateway = gateway;
		this.dns1 = dns1;
		this.dns2 = dns2;
	}
	
	public ConfiguracaoRede() {
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getDns1() {
		return dns1;
	}

	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}

	public String getDns2() {
		return dns2;
	}

	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConfiguracaoRede) {
			return this.getIp().equals(((ConfiguracaoRede)obj).getIp());
		}
		return false;
	}
}
