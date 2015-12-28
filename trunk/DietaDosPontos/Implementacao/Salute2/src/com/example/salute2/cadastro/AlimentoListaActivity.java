package com.example.salute2.cadastro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AlimentoListaActivity extends ListActivity implements OnItemClickListener{

	//ListView alimento;
	//String[]alimentos={"leite","pão integral","fruta","carne","arroz integral","verdura"}; 
	
	//private List<Map<String,Object>> alimentos;
	
	//private List<Map<String, Object>> alimentos(){
		//alim = new ArrayList<Map<String,Object>>();
	//}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//String[] de={"alimento","calorias"};
		//int[]para={com.example.salute2.R.id.nome_alimento,com.example.salute2.R.id.valor};
		setListAdapter(new ArrayAdapter<String>(this,R.layout.simple_list_item_checked, listarAlimentos()));
		//SimpleAdapter adapter = new SimpleAdapter(this,alimentos,com.example.salute2.R.layout.activity_list_alimento,de,para);
		ListView alimento = getListView();
		alimento.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		TextView textView =(TextView)view;
		Toast.makeText(this,"Alimento Selecionado:"+textView.getText(),Toast.LENGTH_SHORT).show();
	}
	
	private List<String> listarAlimentos(){
		
		return Arrays.asList("leite","pão integral","fruta","carne","arroz integral","verdura");
			
	}
	

}
