package br.com.pedidoonline.app.model;

import java.util.ArrayList;

public class Mesa extends AbstractEntity {
	
	private ArrayList<Pedido> pedidos;
	private Garcom garcom;

	public Garcom getGarcom() {
		return garcom;
	}

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
