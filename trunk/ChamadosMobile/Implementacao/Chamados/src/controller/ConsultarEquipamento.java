package controller;

import pojo.Equipamento;
import retrofit.RestAdapter;
import service.ApiService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.appcompat.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarEquipamento extends Activity {

	EditText texto;
	Button botao;

	@Override
	public void onCreate(final Bundle icicle) {
		super.onCreate(icicle);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		setContentView(R.layout.consultar_equipamento);
		texto = (EditText) findViewById(R.id.editText1);
		botao = (Button) findViewById(R.id.button1);

		botao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint("http://portal.blcm.cialne.com.br:8787/chamados_mobile")
				.build();
				ApiService service = restAdapter.create(ApiService.class);
				Equipamento equipamento = service.consultarEquipamento(texto.getText().toString());
				if (equipamento != null) {
					Intent it = new Intent(view.getContext(), EquipamentoController.class);
					it.putExtra("equipamento", equipamento);
					startActivity(it);
				} else {
					Toast.makeText(view.getContext(), "Equipamento não existe", Toast.LENGTH_LONG).show();;
				}
			}
		});

	}
}
