package br.com.eiService.enums;

public enum TipoMarcacaoEnum {
	ELETRICIDADE(0), BURACO(1), SANEAMENTO(2), LIXO(3);

	private int desc;

	TipoMarcacaoEnum(int desc) {
		this.setDesc(desc);
	}

	public int getDesc() {
		return desc;
	}

	public void setDesc(int desc) {
		this.desc = desc;
	}

}
