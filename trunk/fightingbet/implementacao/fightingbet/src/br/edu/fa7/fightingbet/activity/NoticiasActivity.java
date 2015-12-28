package br.edu.fa7.fightingbet.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.model.Noticia;
import br.edu.fa7.fightingbet.task.NoticiasListTask;

public class NoticiasActivity extends BaseActivity {

	private ListView noticiasListView;
	private ArrayAdapter<Noticia> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticias);
		
		noticiasListView = (ListView) findViewById(R.id.noticiasListView);
		noticiasListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				Noticia noticia = (Noticia) adapterView.getItemAtPosition(position);
				
				Intent intent = new Intent(getApplicationContext(), NoticiaDetailActivity.class);
			    intent.putExtra("noticia", noticia);
			    startActivity(intent);
			}
		});
		
		new NoticiasListTask(this).execute(this);
	}
	
	public void montarListView(List<Noticia> noticias) {
		if (noticias != null) {
			adapter = new ArrayAdapter<Noticia> (this, android.R.layout.simple_spinner_item, noticias);
			adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
			noticiasListView.setAdapter(adapter);
			
		} else {
			Toast.makeText(this, R.string.noticias_load_fail, Toast.LENGTH_SHORT).show();
		}
	}
}
