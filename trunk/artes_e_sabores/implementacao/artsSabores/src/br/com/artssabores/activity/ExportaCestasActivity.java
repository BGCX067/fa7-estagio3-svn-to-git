package br.com.artssabores.activity;


import br.com.artssabores.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExportaCestasActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exporta_cestas);
	}
	
	public void Replicar_Click(View v) {
		//INICIAR_REPLICAÇÃO é um serviço(ExportasCestasService)
		startService(new Intent("INICIAR_REPLICACAO"));

	}

}
