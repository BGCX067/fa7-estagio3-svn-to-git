package br.edu.fa7.fightingbet.activity;

import android.os.Bundle;
import android.widget.Spinner;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.task.EventosSpinnerTask;

public class ResultadosActivity extends BaseActivity {

	private Spinner spinnerEventosView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultados);
		
		spinnerEventosView = (Spinner) findViewById(R.id.spinner_eventos);
		
		new EventosSpinnerTask(this, spinnerEventosView).execute(this);
	}
}