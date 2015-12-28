package br.com.pedidoonline.app.service;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.pedidoonline.app.CardapioActivity;
import br.com.pedidoonline.app.R;
import br.com.pedidoonline.app.model.Pedido;

public class PedidoTask extends AsyncTask<Void, Void, Pedido> {
	
	private CardapioActivity activity;
	ProgressDialog dialog;
	
	public PedidoTask(CardapioActivity activity) {
		this.activity = activity;
		dialog = new ProgressDialog(activity);
	}

	@Override
	protected void onPreExecute() {
		dialog.setMessage("Realizando pedido...");
	    dialog.show();
	}

	@Override
	protected Pedido doInBackground(Void... params) {
		try {
			Pedido pedido = PedidoFacade.getInstance().efetuarPedido();
			return pedido;
		} catch (Exception e) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage(R.string.alerta_falha).setTitle(R.string.falha_realizar_pedido);
			builder.create();
			return null;
		}
	}

	@Override
	protected void onPostExecute(Pedido pedido) {
		activity.visualizarPedido(pedido);
		dialog.dismiss();
	}
	
}
