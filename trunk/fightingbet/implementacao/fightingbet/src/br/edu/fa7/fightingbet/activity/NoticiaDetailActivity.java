package br.edu.fa7.fightingbet.activity;

import android.os.Bundle;
import android.widget.TextView;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.model.Noticia;

public class NoticiaDetailActivity extends BaseActivity {

	TextView tituloView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noticia_detail);
		
		Noticia noticia = (Noticia) getIntent().getParcelableExtra("noticia");
		
		tituloView = (TextView) findViewById(R.id.tituloNoticia);
		tituloView.setText(noticia.getTitulo());
	}
}