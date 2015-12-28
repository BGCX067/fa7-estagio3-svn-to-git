package com.br.papoinbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.br.papoinbar.modelo.Bar;
import com.br.papoinbar.modelo.TipoDeBar;

public class CadastroBarActivity extends Activity {
	EditText nomeBar;
	EditText enderecoBar;
	String nome, tipo, endereco;
	Spinner spinner;
	Bar bar;
	DBAdapter datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_bar);

		spinner = (Spinner) findViewById(R.id.tiposBar);
		spinner.setAdapter(new ArrayAdapter<TipoDeBar>(this,
					      android.R.layout.simple_spinner_item, TipoDeBar.values()));
		nomeBar = (EditText)this.findViewById(R.id.nomeBar);
		enderecoBar = (EditText)this.findViewById(R.id.enderecoBar);
		datasource = new DBAdapter(this);
        datasource.open();
	
		
	}
	public void cadastroManual(View v){
		bar = datasource.createBar(nomeBar.getText().toString(), enderecoBar.getText().toString(), spinner.getSelectedItem().toString(), "null");
		if(bar!=null)
		{
			this.setResult(1);
			this.finish();
		}
	}

	
	
}
