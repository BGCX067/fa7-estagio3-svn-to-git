package br.com.pedidoonline.app.model;


public class ItemPedido extends AbstractEntity {
	
	private Item item;
	
	private Integer quantidade;
	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	

}
