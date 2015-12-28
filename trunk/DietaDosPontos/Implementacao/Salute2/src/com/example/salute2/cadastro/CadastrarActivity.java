package com.example.salute2.cadastro;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.salute2.MainActivity;
import com.example.salute2.R;
import com.example.salute2.contentprovider.UsuarioContentProvider;
import com.example.salute2.database.SaluteDatabaseHelper;
import com.example.salute2.database.UsuarioTable;

public class CadastrarActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar);
		Button btn = (Button) findViewById(R.id.cadastrar);
		btn.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
			String nome = ((EditText) findViewById(R.id.editNome)).getText().toString();
	        String email = ((EditText)findViewById(R.id.editEmail)).getText().toString();
	        String senha = ((EditText)findViewById(R.id.editSenha)).getText().toString();
	                             
	        RadioGroup sexo_group = (RadioGroup)findViewById(R.id.sexo);
	        String sexo = "";
				switch (sexo_group.getCheckedRadioButtonId()){
				case R.id.rbFeminino:
					sexo = "Feminino";
					break;
				case R.id.rbMasculino:
					sexo = "Masculino";			        
			        break;
				}
			
			String idade = ((EditText)findViewById(R.id.editIdade)).getText().toString();
			String peso = ((EditText)findViewById(R.id.editPeso)).getText().toString();
			
	        ContentValues values = new ContentValues();
	        values.put(UsuarioTable.COLUMN_NOME, nome);
	        values.put(UsuarioTable.COLUMN_EMAIL, email);
	        values.put(UsuarioTable.COLUMN_SENHA, senha);
	        values.put(UsuarioTable.COLUMN_SEXO, sexo);
	        values.put(UsuarioTable.COLUMN_IDADE, idade);
	        values.put(UsuarioTable.COLUMN_PESO, peso);

	        getContentResolver().insert(UsuarioContentProvider.CONTENT_URI, values);
	        	        			
	    	Intent i = new Intent ();//Troca de tela	
	    	i.setClass(CadastrarActivity.this, RefeicaoListaActivity.class);
	    	startActivity(i);
	    	

			}
			    
		});
	
		}
}