package br.edu.fa7.fightingbet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.task.LogoutTask;
import br.edu.fa7.fightingbet.util.SessionManager;

public class MainActivity extends BaseActivity {

    private SessionManager session;
    
    private Button btnApostas;
    private Button btnResultados;
    private Button btnRanking;
    private Button btnNoticias;
    private Button btnLogout;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
         
        btnApostas 		= (Button) findViewById(R.id.btnApostas);
        btnResultados 	= (Button) findViewById(R.id.btnResultados);
        btnRanking 		= (Button) findViewById(R.id.btnRanking);
        btnNoticias 	= (Button) findViewById(R.id.btnNoticias);
        btnLogout 		= (Button) findViewById(R.id.btnLogout);
        
        btnApostas.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent apostas = new Intent(MainActivity.this, ApostasActivity.class);
        		startActivity(apostas);
        	}
        });
        
        btnResultados.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent resultados = new Intent(MainActivity.this, ResultadosActivity.class);
        		startActivity(resultados);
        	}
        });
        
        btnRanking.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent ranking = new Intent(MainActivity.this, RankingActivity.class);
        		startActivity(ranking);
        	}
        });
         
        btnNoticias.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent noticias = new Intent(MainActivity.this, NoticiasActivity.class);
				startActivity(noticias);
			}
		});
        
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            	new LogoutTask(MainActivity.this).execute(MainActivity.this);
            }
        });
    }
}
