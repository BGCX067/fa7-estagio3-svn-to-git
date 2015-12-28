package br.com.pedidoonline.app;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.pedidoonline.app.model.ItemPedido;
import br.com.pedidoonline.app.model.Pedido;
import br.com.pedidoonline.app.service.PedidoFacade;

public class PedidoListActivity extends ListActivity {
	public final static String TOTAL_FATURA = "TOTAL_FATURA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedidolist);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listarPedidos()));
		ListView listView = getListView();
	}

	public List<String> listarPedidos() {
		Pedido pedido = PedidoFacade.getInstance().getPedido();
		List<String> pedidos = new ArrayList<String>();
		for(ItemPedido itemPedido : pedido.getItens()) {
			String pedidoDescricao = itemPedido.getQuantidade()+" "+itemPedido.getItem().getNome();
			pedidos.add(pedidoDescricao);
		}
		
		return pedidos;
	}
	
	public void OnClickVisualizarFaturaParcial(View view) {
		Intent intent = new Intent(this, FaturaListActivity.class);
		startActivity(intent);

	}

}
