package br.com.pedidoonline.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.pedidoonline.app.model.Cardapio;
import br.com.pedidoonline.app.model.Categoria;
import br.com.pedidoonline.app.model.Item;
import br.com.pedidoonline.app.model.ItemPedido;
import br.com.pedidoonline.app.model.Pedido;

public class PedidoFacade {
	
	private static PedidoFacade instance;
	private Cardapio cardapio;
	private Pedido pedido;
	
	
	public static PedidoFacade getInstance() {
		if(instance == null) {
			instance = new PedidoFacade();
		}
		return instance;
	}
	
	private PedidoFacade() {
		pedidoInit();
	}

	public Cardapio getCardapio() throws Exception {
		pedidoInit();
		if(cardapio == null) {
			cardapio = PedidoClient.getInstace().getCardapio();
		}
		return cardapio;
	}

	private void pedidoInit() {
		if(pedido == null){
			pedido = new Pedido();
			pedido.setItens(new ArrayList<ItemPedido>());
		}
	}
	
	public Integer obterQuantidadePorItem(Item item) {
		Integer quantidade = 0;
		for(ItemPedido itemPedido : pedido.getItens()) {
			if(itemPedido.getItem().getCodigo().equals(item.getCodigo())) {
				quantidade = itemPedido.getQuantidade();
				break;
			}
		}
		return quantidade;
	}
	
	public Categoria getCategoria(int index) {
		return cardapio.getCategorias().get(index);
	}
	
	public Item getItem(int indexCategoria, int indexItem) {
		return getCategoria(indexCategoria).getItens().get(indexItem);
	}

	public ArrayList<Categoria> getCategorias() {
		return cardapio.getCategorias();
	}
	
	public ItemPedido obterQuantidadeItemPedido(Item item) {
		ItemPedido itemDoPedido = null;
		for(ItemPedido itemPedido : pedido.getItens()) {
			if(itemPedido.getItem().getCodigo().equals(item.getCodigo())) {
				itemDoPedido = itemPedido;
				break;
			}
		}
		return itemDoPedido;
	}

	public void pedir(Item item, Integer quantidade) {
		ItemPedido itemPedido = obterQuantidadeItemPedido(item);
		if(itemPedido == null) {
			itemPedido = new ItemPedido();
			itemPedido.setItem(item);
			itemPedido.setQuantidade(quantidade);
			pedido.getItens().add(itemPedido);
		} else {
			itemPedido.setQuantidade(quantidade);
		}
	}

	public Pedido efetuarPedido() throws Exception {
		return PedidoClient.getInstace().newPedido(pedido);
	}
	
	public BigDecimal getTotalFatura() {
		
		BigDecimal total= BigDecimal.ZERO;
		
		if(pedido!=null && pedido.getItens()!=null){
		
		for (ItemPedido itemPedido : pedido.getItens()) {
			total = itemPedido.getItem().getValor().multiply(BigDecimal.valueOf(itemPedido.getQuantidade()));
		}
		}
		
		return total;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

}
