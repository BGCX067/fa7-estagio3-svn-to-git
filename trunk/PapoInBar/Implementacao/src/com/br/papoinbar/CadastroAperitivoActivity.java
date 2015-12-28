package com.br.papoinbar;

import com.br.papoinbar.modelo.Aperitivo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroAperitivoActivity extends Activity {
	EditText nomeAperitivo;
	EditText valorAperitivo;
	Aperitivo aperitivo;
	DBAdapterAperitivo datasource;
	long idBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_aperitivo);
		nomeAperitivo = (EditText)this.findViewById(R.id.nomeAperitivo);
		valorAperitivo = (EditText)this.findViewById(R.id.valorAperitivo);
		datasource = new DBAdapterAperitivo(this);
        datasource.open();
        Intent i = getIntent();
        idBar = i.getLongExtra("bar", 1);
        
        Button cadastrarAperitivo = (Button) this.findViewById(R.id.salvarAperitivo);
        
    	cadastrarAperitivo.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			cadastrar(v);
			
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_aperitivo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void cadastrar(View v){
		try{
		aperitivo = datasource.createAperitivo(nomeAperitivo.getText().toString(), Double.parseDouble(valorAperitivo.getText().toString()), idBar );
				if(aperitivo!=null)
				{
					this.setResult(1);
					this.finish();
				}
		}
		catch(Exception e)
		{
	
		}
	}
}
