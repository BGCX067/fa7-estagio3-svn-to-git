package br.com.pedidoonline.app.service;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.pedidoonline.app.CardapioActivity;
import br.com.pedidoonline.app.ExpAdapter;
import br.com.pedidoonline.app.R;
import br.com.pedidoonline.app.model.Cardapio;

public class CardapioTask extends AsyncTask<CardapioActivity, Void, ExpAdapter> {
	
	private CardapioActivity activity;
	ProgressDialog dialog;
	
	public CardapioTask(CardapioActivity activity) {
		this.activity = activity;
		dialog = new ProgressDialog(activity);
	}

	@Override
	protected void onPreExecute() {
		dialog.setMessage("Carregando");
	    dialog.show();
	}

	@Override
	protected ExpAdapter doInBackground(CardapioActivity... params) {
		activity = params[0];
		try {
			Cardapio cardapio =  PedidoFacade.getInstance().getCardapio();
			ExpAdapter adapter = new ExpAdapter(activity, cardapio);
			return adapter;
		} catch (Exception e) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage(R.string.alerta_falha).setTitle(R.string.falha_carregar_cardapio);
			builder.create();
			return null;
		}
	}

	@Override
	protected void onPostExecute(ExpAdapter adapter) {
		activity.atualizar(adapter);
		 dialog.dismiss();
	}
	
}
