package br.com.pedidoonline.app;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.pedidoonline.app.model.ItemPedido;
import br.com.pedidoonline.app.model.Pedido;
import br.com.pedidoonline.app.service.PedidoFacade;

public class FaturaListActivity extends ListActivity {
	
	private NumberFormat formato;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faturalist);
		formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listarPedidos()));
		
		TextView tv = (TextView)findViewById(R.id.total_fatura);
		BigDecimal dou = PedidoFacade.getInstance().getTotalFatura();
		String str = "O valor da fatura é: "+ String.valueOf(dou);
		tv.setText(str);
		ListView listView = getListView();

	}
	
	public List<String> listarPedidos() {
		Pedido pedido = PedidoFacade.getInstance().getPedido();
		List<String> pedidos = new ArrayList<String>();
		pedidos.add("Quantidade - Descrição - Preço unitário - Preço Total");
		for(ItemPedido itemPedido : pedido.getItens()) {
			String pedidoDescricao = itemPedido.getQuantidade()+" - "+itemPedido.getItem().getNome() + " - " + 
							formato.format(itemPedido.getItem().getValor()) +" - "+ formato.format(itemPedido.getItem().getValor().doubleValue() * itemPedido.getQuantidade());
			pedidos.add(pedidoDescricao);
		}
		
		pedidos.add("---------------------------------");
		
		return pedidos;
	}

}
