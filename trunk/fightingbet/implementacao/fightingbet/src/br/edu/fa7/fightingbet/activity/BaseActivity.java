package br.edu.fa7.fightingbet.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import br.edu.fa7.fightingbet.R;
import br.edu.fa7.fightingbet.task.LogoutTask;

public class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
	        case R.id.menu_main:
	        {
	        	Intent main = new Intent(this, MainActivity.class);
	        	startActivity(main);
	        }
            case R.id.menu_apostas:
            {
            	Intent apostas = new Intent(this, ApostasActivity.class);
	        	startActivity(apostas);
            }
            case R.id.menu_resultados:
            {
            	Intent resultados = new Intent(this, ResultadosActivity.class);
        		startActivity(resultados);
            }
            case R.id.menu_ranking:
            {
            	Intent ranking = new Intent(this, RankingActivity.class);
	        	startActivity(ranking);
            }
            case R.id.menu_noticias:
            {
            	Intent noticias = new Intent(this, NoticiasActivity.class);
	        	startActivity(noticias);
            }
            case R.id.menu_sair:
            {
            	new LogoutTask(BaseActivity.this).execute(BaseActivity.this);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
