package com.fa7.estagio3.podcastmanager.activities;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.fa7.estagio3.podcastmanager.adapters.PodcastSubscribedAdapter;
import com.fa7.estagio3.podcastmanager.entities.Episodio;
import com.fa7.estagio3.podcastmanager.entities.Podcast;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioEpisodio;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioPodcast;
import com.fa7.estagio3.podcastmanager.repositorios.scripts.RepositorioScript;
import com.fa7.estagio3.podcastmanagerapp.R;

public class PodcastsSubscribedsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_podcasts_assinados);
		
		//cria e atualiza banco de dados
        RepositorioScript repositorioScript = new RepositorioScript(this);
//        repositorioScript.clearDb();
        
        RepositorioPodcast repositorioPodcast = new RepositorioPodcast(this);        
        List<Podcast> podcasts = repositorioPodcast.listPodcasts();
        
        if(podcasts.size() == 0){
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setMessage("Você ainda não possui nenhum podcast assinado, vamos lá procurar :)")
        	       .setCancelable(false)
        	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	                Intent intent = new Intent(getApplicationContext(),PodcastSearchFeedActivity.class);
        	                startActivity(intent);
        	           }
        	       });
        	AlertDialog alert = builder.create();
        	alert.show();
        }else{       
        	
        	GridView gridview = (GridView) findViewById(R.id.gridviewPodcastsSubscribeds);
            gridview.setAdapter(new PodcastSubscribedAdapter(this, R.layout.list_podcast_assinado_element, podcasts));
         
        }
        
        
        RepositorioEpisodio repositorioEpisodio = new RepositorioEpisodio(this);
        List<Episodio> episodiosInseridos = repositorioEpisodio.listEpisodios();                
        Log.e("Podcasts", ""+podcasts.size());
        Log.e("Episódios", ""+episodiosInseridos.size());
        
        for(Episodio episodio : episodiosInseridos){
			Log.e(episodio.getTitle(),episodio.getPath());
		}
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.option_search_feed_podcast:
        	Intent intent = new Intent(this, PodcastSearchFeedActivity.class);
            this.startActivity(intent);
            break;                
        default:
        	return super.onOptionsItemSelected(item);
        }

        return true;
    }
}