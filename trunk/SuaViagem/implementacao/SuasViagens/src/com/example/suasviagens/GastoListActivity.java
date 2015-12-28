package com.example.suasviagens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.suasviagens.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ListActivity;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.preference.PreferenceManager;

@SuppressWarnings("unused")
public class GastoListActivity extends ListActivity implements
		OnItemClickListener {

	private String dataAnterior = "";

	private List<Map<String, Object>> gastos;

	private DatabaseHelper helper;

	private String viagemId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		helper = new DatabaseHelper(this);

		viagemId = getIntent().getExtras().getString("viagem_id");

		SharedPreferences preferencias = PreferenceManager
				.getDefaultSharedPreferences(this);

		String[] de = {   "valor", "data", "descricao",  "local" };

		int[] para = {   R.id.valor, R.id.data, R.id.descricao,  R.id.local };

		SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(),
				R.layout.lista_gasto, de, para);

		setListAdapter(adapter);

		adapter.setViewBinder(new GastoViewBinder());

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Map<String, Object> map = gastos.get(position);
		String descricao = (String) map.get("descricao");
		String mensagem = "Gasto selecionada: " + descricao;
		Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
	}

	private List<Map<String, Object>> listarGastos() {

		SQLiteDatabase db = helper.getReadableDatabase();

		Cursor cursor = db.rawQuery("SELECT _id,  valor, data, descricao, local FROM gasto WHERE viagem_id = ? ",
				new String[] { viagemId });

		cursor.moveToFirst();

		gastos = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < cursor.getCount(); i++) {

			Map<String, Object> item = new HashMap<String, Object>();

			String id = cursor.getString(0);

			double valor = cursor.getDouble(1);

			String data = cursor.getString(2);

			String descricao = cursor.getString(3);

			String local = cursor.getString(4);

			item.put("id", id);
			
			item.put("data", data);	
			
			item.put("valor", valor);			
			
			item.put("descricao", descricao);			

			item.put("local", local);

			gastos.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return gastos;
	}

	private class GastoViewBinder implements ViewBinder {

		@Override
		public boolean setViewValue(View view, Object data,
				String textRepresentation) {

			if (view.getId() == R.id.data) {

				if (!dataAnterior.equals(data)) {
					TextView textView = (TextView) view;
					textView.setText(textRepresentation);
					dataAnterior = textRepresentation;
					view.setVisibility(View.VISIBLE);
				} else {
					view.setVisibility(View.GONE);
				}
				return true;
			}

			if (view.getId() == R.id.categoria) {
				Integer id = (Integer) data;
				view.setBackgroundColor(getResources().getColor(id));
				return true;
			}
			return false;
		}
	}
}
