package com.example.suasviagens;

import java.util.Calendar;

import com.example.suasviagens.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Build;

@SuppressWarnings("unused")
public class ViagemActivity extends ActionBarActivity {

	//private int ano, mes, dia;
	private EditText dataChegada;
	private EditText dataSaida;

	private DatabaseHelper helper;
	private EditText destino, quantidadePessoas, orcamento;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viagem);


		//Calendar calendar = Calendar.getInstance();
		//ano = calendar.get(Calendar.YEAR);
		//mes = calendar.get(Calendar.MONTH);
		//dia = calendar.get(Calendar.DAY_OF_MONTH);

		dataChegada = (EditText) findViewById(R.id.dataChegada);
		dataChegada.addTextChangedListener(Mask.insert("##/##/####", dataChegada));
		dataSaida = (EditText) findViewById(R.id.dataSaida);
		dataSaida.addTextChangedListener(Mask.insert("##/##/####", dataSaida));

		destino = (EditText) findViewById(R.id.destino);
		
		orcamento = (EditText) findViewById(R.id.orcamento);
		radioGroup = (RadioGroup) findViewById(R.id.tipoViagem);

		// prepara acesso ao banco de dados
		helper = new DatabaseHelper(this);

	}

	public void salvarViagem(View view){

		SQLiteDatabase db = helper.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("destino", destino.getText().toString());
		values.put("data_chegada", dataChegada.getText().toString());
		values.put("data_saida", dataSaida.getText().toString());
		values.put("orcamento", orcamento.getText().toString());
		

		int tipo = radioGroup.getCheckedRadioButtonId();

		if(tipo == R.id.lazer) {
			values.put("tipo_viagem", Constantes.VIAGEM_LAZER);
		} else {
			values.put("tipo_viagem", Constantes.VIAGEM_NEGOCIOS);
		}

		long resultado = db.insert("viagem", null, values);
		
		if(resultado != -1 ){
			Toast.makeText(this, getString(R.string.registro_salvo),
					Toast.LENGTH_SHORT).show();
			
		}else{
			Toast.makeText(this, getString(R.string.erro_salvar),
					Toast.LENGTH_SHORT).show();
		}

		startActivity(new Intent(this, DashboardActivity.class));
	}



	public void selecionarData(View view){
		showDialog(view.getId());
	}

	
	
	
}
