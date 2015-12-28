package br.com.pedidoonline.app.model;

import java.util.ArrayList;

import org.simpleframework.xml.Default;

@Default(required = false)
public class Pedido extends AbstractEntity{

	private Long codigo;
	
	private Conta conta;
	
	private ArrayList<ItemPedido> itens;


	public ArrayList<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemPedido> itens) {
		this.itens = itens;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
