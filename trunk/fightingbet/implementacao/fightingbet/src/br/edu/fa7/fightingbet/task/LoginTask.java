package br.edu.fa7.fightingbet.task;

import org.apache.commons.lang3.StringUtils;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.activity.LoginActivity;
import br.edu.fa7.fightingbet.client.SessionsClient;
import br.edu.fa7.fightingbet.model.Usuario;
import br.edu.fa7.fightingbet.util.SessionManager;

public class LoginTask extends AsyncTask<LoginActivity, Void, Usuario> {

	private LoginActivity activity;
	private ProgressDialog dialog;

	private String email;
	private String password;
	
	public LoginTask(LoginActivity activity, String email, String password) {
		this.activity = activity;
		
		this.email = email;
		this.password = password;
		
		this.dialog = new ProgressDialog(activity);
	}
	
	@Override
	protected void onPreExecute() {
		dialog.setMessage("Efetuando login...");
	    dialog.show();
	}

	@Override
	protected Usuario doInBackground(LoginActivity... params) {
		activity = params[0];
		return SessionsClient.getInstace().login(email, password);
	}
	
	@Override
	protected void onPostExecute(Usuario usuario) {
		if (usuario != null && usuario.getToken() != null) {
			SessionManager session = new SessionManager(activity);
			session.criarSessionLogin(usuario);
			activity.finish();
		
		} else if (StringUtils.isNotBlank(usuario.getError())) {
			Toast.makeText(activity, usuario.getError(), Toast.LENGTH_SHORT).show();
		
		} else {
			Toast.makeText(activity, R.string.login_fail, Toast.LENGTH_SHORT).show();
		}

		dialog.dismiss();
	}
}
