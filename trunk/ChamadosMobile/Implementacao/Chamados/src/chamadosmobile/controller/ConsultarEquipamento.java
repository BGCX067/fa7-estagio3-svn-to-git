package chamadosmobile.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.appcompat.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarEquipamento extends Activity {

	EditText texto;
	Button botao;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.consultar_equipamento);
		texto = (EditText) findViewById(R.id.editText1);
		botao = (Button) findViewById(R.id.button1);

		botao.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(ConsultarEquipamento.this,
						texto.getText().toString(), Toast.LENGTH_LONG).show();
			}
		});

	}
}
