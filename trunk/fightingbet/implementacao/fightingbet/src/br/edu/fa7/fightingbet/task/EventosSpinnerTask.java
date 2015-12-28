package br.edu.fa7.fightingbet.task;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.client.EventosClient;
import br.edu.fa7.fightingbet.model.Evento;
import br.edu.fa7.fightingbet.util.SessionManager;

public class EventosSpinnerTask extends AsyncTask<Activity, Void, List<Evento>> {

	private Activity activity;
	private ProgressDialog dialog;
	private Spinner spinner;
	
	public EventosSpinnerTask(Activity activity, Spinner spinner) {
		this.activity = activity;
		this.spinner = spinner;
		
		this.dialog = new ProgressDialog(activity);
	}
	
	@Override
	protected void onPreExecute() {
		dialog.setMessage("Carregando...");
	    dialog.show();
	}

	@Override
	protected List<Evento> doInBackground(Activity... params) {
		activity = params[0];
		SessionManager session = new SessionManager(activity);
		
		return EventosClient.getInstace().list(session.getUsuario().getToken());
	}
	
	@Override
	protected void onPostExecute(List<Evento> eventos) {
		if (eventos != null) {
			ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento> (activity, android.R.layout.simple_spinner_item, eventos);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
			
		} else {
			Toast.makeText(activity, R.string.eventos_load_fail, Toast.LENGTH_SHORT).show();
		}

		dialog.dismiss();
	}
}
