package br.com.pedidoonline.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import br.com.pedidoonline.app.model.Pedido;
import br.com.pedidoonline.app.service.CardapioTask;
import br.com.pedidoonline.app.service.PedidoTask;

import com.parse.ParsePush;

public class CardapioActivity extends Activity {

	private ExpandableListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardapio);
		listView = (ExpandableListView) findViewById(R.id.cardapioExpandableListView);
		new CardapioTask(this).execute(this);
	}

	public void atualizar(ExpAdapter expAdapter) {
		listView.setAdapter(expAdapter);
	}

	public void OnClickEfetuarPedido(View view) {
		new PedidoTask(this).execute();
		ParsePush push = new ParsePush();
		push.setChannel("Garcom");
		push.setMessage("Cliente realizou o pedido");
		push.sendInBackground();

	}
	
	public void visualizarPedido(Pedido pedido) {
		startActivity(new Intent(this, PedidoListActivity.class));
	}

}
