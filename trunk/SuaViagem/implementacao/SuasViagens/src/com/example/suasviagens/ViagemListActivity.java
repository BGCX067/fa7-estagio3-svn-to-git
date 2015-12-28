package com.example.suasviagens;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.suasviagens.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceManager;

@SuppressWarnings("unused")
public class ViagemListActivity extends ListActivity implements OnItemClickListener
,OnClickListener {

	private List<Map<String, Object>> viagens;

	private AlertDialog alertDialog;

	private int viagemSelecionada;

	private AlertDialog dialogConfirmacao;

	private DatabaseHelper helper;
	//private SimpleDateFormat dateFormat;
	private Double valorLimite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		helper = new DatabaseHelper(this);
		//dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		SharedPreferences preferencias =
				PreferenceManager.getDefaultSharedPreferences(this);

		String valor = preferencias.getString("valor_limite", "-1");
		valorLimite = Double.valueOf(valor);


		String[] de = {"imagem", "destino", "data_chegada", "data_saida", "total"};

		int[] para = {R.id.tipoViagem, R.id.destino, R.id.data_chegada, R.id.data_saida, R.id.valor};

		SimpleAdapter adapter = new SimpleAdapter(this,listarViagens(),
				R.layout.lista_viagem, de, para);

		setListAdapter(adapter);

		getListView().setOnItemClickListener(this);

		this.alertDialog = criaAlertDialog();

		this.dialogConfirmacao = criaDialogConfirmacao();

		
	}

	private List<Map<String, Object>> listarViagens() {

		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT _id, tipo_viagem, destino, " +
				"data_chegada, data_saida, orcamento FROM viagem",
				null);

		cursor.moveToFirst();

		viagens = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < cursor.getCount(); i++) {

			Map<String, Object> item = new HashMap<String, Object>();

			String id = cursor.getString(0);

			int tipoViagem = cursor.getInt(1);
			String destino = cursor.getString(2);
			String dataChegada = "Chegada: "+cursor.getString(3);
			String dataSaida = "Saída: "+cursor.getString(4);
			double orcamento = cursor.getDouble(5);
			item.put("id", id);

			if (tipoViagem == Constantes.VIAGEM_LAZER) {
				item.put("imagem", R.drawable.lazer);
			} else {
				item.put("imagem", R.drawable.negocio);
			}
			item.put("destino", destino);
			
			item.put("data_chegada", dataChegada);
			item.put("data_saida", dataSaida);
			
			double totalGasto = calcularTotalGasto(db, id);
			item.put("total", "Total de Gastos R$ " + totalGasto);
			double alerta = orcamento * valorLimite / 100;
			Double [] valores =
					new Double[] { orcamento, alerta, totalGasto };
			item.put("barraProgresso", valores);
			viagens.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return viagens;
	}

	private double calcularTotalGasto(SQLiteDatabase db, String id) {
		
		Cursor cursor = db.rawQuery("SELECT SUM(VALOR) FROM GASTO where viagem_id = ?", 
				new String[]{ id });
		
		cursor.moveToFirst();
		double total = cursor.getDouble(0);
		cursor.close();
		return total;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		
		this.viagemSelecionada = position;
		alertDialog.show();
	}



	public void onClick(DialogInterface dialog, int item) {

		Intent intent;
		String id = (String) viagens.get(viagemSelecionada).get("id");

		switch (item) {
		case 0:
			intent = new Intent(this, GastoActivity.class);
			intent.putExtra("viagem_id", id);
			startActivity(intent);
			
			break;
		case 1 :
			intent = new Intent(this, GastoListActivity.class);
			intent.putExtra("viagem_id", id);
			startActivity(intent);
			
			break;
		case 2:
			dialogConfirmacao.show();
			break;
			
		case DialogInterface.BUTTON_POSITIVE:
			viagens.remove(viagemSelecionada);
			removerViagem(id);
			getListView().invalidateViews();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			dialogConfirmacao.dismiss();
			break;
		}
	}

	private void removerViagem(String id) {
		SQLiteDatabase db = helper.getWritableDatabase();
		String where [] = new String[]{ id };
		db.delete("gasto", "viagem_id = ?", where);
		db.delete("viagem", "_id = ?", where);
	}

	private AlertDialog criaAlertDialog() {
		final CharSequence[] items = {
				//getString(R.string.editar),
				getString(R.string.novo_gasto),
				getString(R.string.gastos_realizados),
				getString(R.string.remover) };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.opcoes);
		builder.setItems(items, this);
		return builder.create();
	}

	private AlertDialog criaDialogConfirmacao() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(R.string.confirmacao_exclusao_viagem);
		builder.setPositiveButton(getString(R.string.sim), this);
		builder.setNegativeButton(getString(R.string.nao), this);

		return builder.create();
	}

}
