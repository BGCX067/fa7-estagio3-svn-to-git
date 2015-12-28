package br.edu.fa7.fightingbet.task;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.edu.fa7.fightingbet.activity.NoticiasActivity;
import br.edu.fa7.fightingbet.client.NoticiasClient;
import br.edu.fa7.fightingbet.model.Noticia;
import br.edu.fa7.fightingbet.util.SessionManager;

public class NoticiasListTask extends AsyncTask<NoticiasActivity, Void, List<Noticia>> {

	private NoticiasActivity activity;
	private ProgressDialog dialog;
	
	public NoticiasListTask(NoticiasActivity activity) {
		this.activity = activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	@Override
	protected void onPreExecute() {
		dialog.setMessage("Carregando...");
	    dialog.show();
	}

	@Override
	protected List<Noticia> doInBackground(NoticiasActivity... params) {
		activity = params[0];
		SessionManager session = new SessionManager(activity);
		
		return NoticiasClient.getInstace().list(session.getUsuario().getToken());
	}
	
	@Override
	protected void onPostExecute(List<Noticia> noticias) {
		activity.montarListView(noticias);
		dialog.dismiss();
	}
}
