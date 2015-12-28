package com.example.suasviagens;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.suasviagens.R;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils.StringSplitter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class GastoActivity extends ActionBarActivity {

	// private int ano, mes, dia;
	private EditText dataGasto;

	private DatabaseHelper helper;
	private RadioGroup radioGroup;
	private EditText valor, descricao, local;
	private int viagemId;

	private String DATA_CHEGADA = "data_chegada";
	private String DATA_SAIDA = "data_saida";
	private String TABLE_NAME = "viagem";
	private String _ID = "_id";
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	private String VALOR = "sum(valor)";
	private String TABLE_NAME1 = "gasto";
	private String VIAGEM_ID = "viagem_id";
	private String ORCAMENTO = "orcamento";
	double valorr = 0;
	double orcamentoo = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gasto);

		viagemId = Integer.parseInt(getIntent().getExtras().getString(
				"viagem_id"));

		// Calendar calendar = Calendar.getInstance();
		// ano = calendar.get(Calendar.YEAR);
		// mes = calendar.get(Calendar.MONTH);
		// dia = calendar.get(Calendar.DAY_OF_MONTH);

		dataGasto = (EditText) findViewById(R.id.data);
		dataGasto.addTextChangedListener(Mask.insert("##/##/####", dataGasto));

		valor = (EditText) findViewById(R.id.valor);
		descricao = (EditText) findViewById(R.id.descricao);
		local = (EditText) findViewById(R.id.local);
		radioGroup = (RadioGroup) findViewById(R.id.categoria1);

		helper = new DatabaseHelper(this);

	}

	public void getData() throws Exception {

		SQLiteDatabase db1 = helper.getWritableDatabase();

		String[] columns = { DATA_CHEGADA, DATA_SAIDA };

		Cursor cursor = db1.query(TABLE_NAME, columns, _ID + " = '" + viagemId
				+ "'", null, null, null, null);

		StringBuffer buffer = new StringBuffer();

		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(DATA_CHEGADA);
			int index2 = cursor.getColumnIndex(DATA_SAIDA);

			String data_cheg = cursor.getString(index1);
			String data_sai = cursor.getString(index2);

			Date chegada = null;
			Date saida = null;
			Date atual = null;
			try {
				saida = formatter.parse(data_sai);
				chegada = formatter.parse(data_cheg);
				atual = formatter.parse(dataGasto.getText().toString());
				

			} catch (ParseException e) {
				throw new Exception();
			}

			if ((atual.getTime() < chegada.getTime())
					|| (atual.getTime() > saida.getTime())) {
				throw new Exception();

			}

		}
	}
	
	
	public double getValorGasto() {

		SQLiteDatabase db = helper.getWritableDatabase();

		 String[] columns = { VALOR };

		Cursor cursor = db.query(TABLE_NAME1, columns, VIAGEM_ID + " = '" + viagemId
				+ "'", null, null, null, null);
	
		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(VALOR);

			String valr = cursor.getString(index1);
			
			if(valr != null){
				valorr = Double.parseDouble(valr);
					
			}
			
			
			
		}
		return valorr;
	}
	
	public double getValorOrcamento() {

		SQLiteDatabase db = helper.getWritableDatabase();

		 String[] columns = { ORCAMENTO };

		Cursor cursor = db.query(TABLE_NAME, columns, _ID + " = '" + viagemId
				+ "'", null, null, null, null);
		
		
		while (cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(ORCAMENTO);

			String orcam = cursor.getString(index1);
			
			orcamentoo = Double.parseDouble(orcam);
			
			
		}
		return orcamentoo;
	}
	
	public void CondicaoGastoOrcamento(){
		
		String valorCampo = valor.getText().toString();
		
		double valorCampo1 = Double.parseDouble(valorCampo);
		
		//double valorGasto = getValorGasto();
		
		 double valorGasto = getValorGasto() + valorCampo1;
		
		double valorOrcamento = getValorOrcamento();
		
		if(valorGasto > valorOrcamento){
			Toast.makeText(this, "Valor dos Gastos: "+valorGasto+" ULTRAPASSARAM o Valor do Orçamento: "+valorOrcamento,
					Toast.LENGTH_LONG).show();
			
		}else{
			
		}
			
		
	}
	
	public void registrarGasto(View view) {

		try {
			getData();
			
			CondicaoGastoOrcamento();
			
			SQLiteDatabase db = helper.getWritableDatabase();

			ContentValues values = new ContentValues();

			values.put("viagem_id", viagemId);
			values.put("data", dataGasto.getText().toString());
			values.put("valor", valor.getText().toString());
			values.put("descricao", descricao.getText().toString());
			values.put("local", local.getText().toString());

			int tipo = radioGroup.getCheckedRadioButtonId();

			if (tipo == R.id.alimentacao) {
				values.put("tipo_gasto", Constantes.GASTO_ALIMENTACAO);
			} else {
				values.put("tipo_gasto", Constantes.GASTOS_COMBUSTIVEL);
			}

			long resultado = db.insert("gasto", null, values);

			if (resultado != -1) {
				Toast.makeText(this, getString(R.string.registro_salvo),
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, getString(R.string.erro_salvar),
						Toast.LENGTH_SHORT).show();
			}
			startActivity(new Intent(this, ViagemListActivity.class));
		} catch(Exception e){
			Toast.makeText(this, "Data Fora do Período da Viagem!", Toast.LENGTH_SHORT)
			.show();
		}
		

	}

	public void selecionarData(View view) {
		showDialog(view.getId());
	}

	

	// Falta implementar

	// public boolean onOptionsItemSelected(MenuItem item){

	// }

}
