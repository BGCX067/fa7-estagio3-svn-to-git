package chamadosmobile.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.appcompat.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

	EditText login;
	EditText senha;
	Button botao;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.login);
		login = (EditText) findViewById(R.id.editText1);
		senha = (EditText) findViewById(R.id.editText2);
		botao = (Button) findViewById(R.id.button1);

		botao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(Login.this, MainActivity.class));
			}
		});

	}

}
