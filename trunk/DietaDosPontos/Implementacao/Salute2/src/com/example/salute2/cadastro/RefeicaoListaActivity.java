package com.example.salute2.cadastro;

import java.util.Arrays;
import java.util.List;

import com.example.salute2.R;
import com.example.salute2.database.SaluteDatabaseHelper;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class RefeicaoListaActivity extends ListActivity implements OnItemClickListener{
	
	private SaluteDatabaseHelper helper;
	ListView list;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		helper = new SaluteDatabaseHelper(this);
		
		SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);

		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listarRefeicao()));
		ListView list = getListView();
		list.setOnItemClickListener(this);
		
		
		//setContentView(R.layout.activity_refeicao_list);
		//list =(ListView) findViewById(R.id.list_item);
		//ArrayAdapter<String> array = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,refeicao);
		//list.setAdapter(array);
	}
	
	private List<String> listarRefeicao(){
		return Arrays.asList("Café da Manhã", "Lanche da Manhã", "Almoço","Lanche da Tarde","Jantar");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.listmenu, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		TextView textView = (TextView)view;
		String mensagem = "Refeicao Selecionada"+textView.getText();
		Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this,AlimentoListaActivity.class));	
	}
}
