package br.edu.fa7.fightingbet.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.client.SessionsClient;
import br.edu.fa7.fightingbet.util.SessionManager;

public class LogoutTask extends AsyncTask<Activity, Void, Boolean> {

	private Activity activity;
	private ProgressDialog dialog;

	public LogoutTask(Activity activity) {
		this.activity = activity;
		this.dialog = new ProgressDialog(activity);
	}
	
	@Override
	protected void onPreExecute() {
		dialog.setMessage("Efetuando logout...");
	    dialog.show();
	}

	@Override
	protected Boolean doInBackground(Activity... params) {
		activity = params[0];
		SessionManager session = new SessionManager(activity);
		
		try {
			return SessionsClient.getInstace().logout(session.getUsuario().getToken());
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	protected void onPostExecute(Boolean success) {
		if (success) {
			SessionManager session = new SessionManager(activity);
			session.logout();
		} else {
			Toast.makeText(activity, R.string.logout_fail, Toast.LENGTH_SHORT).show();
		}
		
		dialog.dismiss();
	}
}
