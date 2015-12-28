package com.br.papoinbar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.br.papoinbar.modelo.Aperitivo;
import com.br.papoinbar.modelo.Bar;

public class VisualizarBarActivity extends Activity {
	Bar bar;
	DBAdapter barAdapter;
	DBAdapterAperitivo aperitivoAdapter;
	ListView list;
	Button editBar, excluiBar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teste);
		Intent i = getIntent();
		barAdapter = new DBAdapter(this);
		barAdapter.open();
		bar = barAdapter.getBar(i.getLongExtra("bar", 1));
		barAdapter.close();
		this.setTitle(bar.getNome());
		aperitivoAdapter = new DBAdapterAperitivo(this);
		aperitivoAdapter.open();
		Cursor cursor = aperitivoAdapter.getAperitivosFromBar(bar.getId());
		//Cursor cursor = aperitivoAdapter.getAperitivos();
		
		

		
        //TextView txtProduct = (TextView) findViewById(R.id.nomeBar);
       // txtProduct.setText(bar.getNome());
        
        String[] columns = new String[] { "nome", "descricao"};
        int[] to = new int[] {R.id.nomeAperitivo, R.id.valorAperitivo};

        list = (ListView) this.findViewById(R.id.listAperitivos);
        //final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_view_aperitivo, cursor, columns, to,0);
        AperitivoAdapter adapter = new AperitivoAdapter(this.getBaseContext(), cursor, 0);


		// Assign adapter to ListView
		
        
		list.setAdapter(adapter);
		


		Button editarBar = (Button) this.findViewById(R.id.btnEditarBar);

		editarBar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				editBar();

			}
		});

		Button excluiBar = (Button) findViewById(R.id.btnExcluirBar);

		excluiBar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				deleteBar(v);

			}
		});

		Button cadastrarAperitivo = (Button) this
				.findViewById(R.id.addAperitivo);

		cadastrarAperitivo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cadastrarAperitivo();

			}
		});
		aperitivoAdapter.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visualizar_bar, menu);
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

	private void cadastrarAperitivo() {
		startActivityForResult(
				new Intent(this, CadastroAperitivoActivity.class).putExtra(
						"bar", bar.getId()), 1);
	}

	private void editBar() {

	}

	private void deleteBar(View v) {
		barAdapter.open();
		barAdapter.EliminaBar(bar.getId());
		barAdapter.close();
		this.setResult(1);
		this.finish();
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
	        aperitivoAdapter.open();
	        Cursor cursor = aperitivoAdapter.getAperitivosFromBar(bar.getId());
//	        Cursor cursor = aperitivoAdapter.getAperitivos();
	        
	        //Bar
	        
	        String[] columns = new String[] { "nome", "descricao"};
	        int[] to = new int[] {R.id.nomeAperitivo, R.id.valorAperitivo};

	        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_view_aperitivo, cursor, columns, to,0);
	        AperitivoAdapter adapter = new AperitivoAdapter(this.getBaseContext(), cursor, 0);


			// Assign adapter to ListView
			
	        
			list.setAdapter(adapter);


		// Assign adapter to ListView
		list = (ListView) this.findViewById(R.id.listAperitivos);

		list.setAdapter(adapter);
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void increaseQuant(){
	
	}

}
