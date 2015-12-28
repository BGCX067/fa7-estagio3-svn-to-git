package com.br.papoinbar.modelo;

public enum TipoDeBar {
	RS("Restaurante"),
	BR("Bar"),
	BT("Boteco"),
	CR("Cabaré"),
	CH("Chopperia"),
	HM("Hamburgueria"),
	SR("Sorveteria");

	private String tipoDeBar;
	
	TipoDeBar(String tipo) {
		tipoDeBar = tipo;
		  }
	
	@Override public String toString() {
	    return tipoDeBar;
	  }
}

