package br.com.pedidoonline.app.model;

public class Cliente extends AbstractEntity {
	
	private Mesa mesa;

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
