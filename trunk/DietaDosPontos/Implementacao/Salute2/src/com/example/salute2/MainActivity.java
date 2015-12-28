package com.example.salute2;


import com.example.salute2.cadastro.CadastrarActivity;
import com.example.salute2.cadastro.RefeicaoListaActivity;
import com.example.salute2.database.SaluteDatabaseHelper;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private SaluteDatabaseHelper helper;
	private EditText email;
	private EditText senha;
	private Button salvar;
	private Button limpar;

	private TextView email_;
	private TextView senha_;

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button salvar = (Button) findViewById(R.id.entrar);
		
		this.email = (EditText)findViewById(R.id.email);
		this.senha = (EditText)findViewById(R.id.senha);
		this.salvar = (Button)findViewById(R.id.entrar);
		
		helper = new SaluteDatabaseHelper(this);
		
	}
	
	public void onClick(View v){
		
		//String emailInformado = email.getText().toString();
		//String senhaInformada = senha.getText().toString();
		
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("email", email.getText().toString());
		values.put("senha", senha.getText().toString());
		
		long resultado = db.insert("usuario",null, values);
		
		if(resultado != -1){
			
			Toast.makeText(this, getString(R.string.login_salvo), Toast.LENGTH_SHORT).show(); 
			Intent i = new Intent();// Troca de tela
			i.setClass(MainActivity.this, RefeicaoListaActivity.class);
			startActivity(i);
		
		}else{
			Toast.makeText(this, getString(R.string.erro_autenticacao), Toast.LENGTH_SHORT).show();

		}
	}
	
		@Override 
		protected void onDestroy() { 
			helper.close(); 
			super.onDestroy(); 
			} 
	
}