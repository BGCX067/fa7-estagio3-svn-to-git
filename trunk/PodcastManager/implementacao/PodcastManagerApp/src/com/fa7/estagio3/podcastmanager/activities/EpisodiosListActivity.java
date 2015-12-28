package com.fa7.estagio3.podcastmanager.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fa7.estagio3.podcastmanager.adapters.EpisodioListAdapter;
import com.fa7.estagio3.podcastmanager.entities.Episodio;
import com.fa7.estagio3.podcastmanager.entities.Podcast;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioEpisodio;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioPodcast;
import com.fa7.estagio3.podcastmanager.util.ImageLoader;
import com.fa7.estagio3.podcastmanagerapp.R;

public class EpisodiosListActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_episodios_list);
		
		Intent intentPodcast = getIntent();
		long podcastId = intentPodcast.getLongExtra("podcastId", 0);		
		
		//Get Info Podcasts
		RepositorioPodcast repositorioPodcast = new RepositorioPodcast(this);
		Podcast podcast = repositorioPodcast.getPodcastById(podcastId);		
		
		//Get List Episodes
		RepositorioEpisodio repositorioEpisodio = new RepositorioEpisodio(this);
		List<Episodio> episodios = repositorioEpisodio.listEpisodiosByPodcast(podcast);				
		
		ImageView coverPodcast = (ImageView) findViewById(R.id.podcastCover);
		ImageLoader imageLoader = new ImageLoader(this);							
		imageLoader.DisplayImage(podcast.getCoverUrl(), coverPodcast);
		
		TextView podcastTitle = (TextView) findViewById(R.id.podcastTitle);
		podcastTitle.setText(podcast.getTitle());
		
		TextView episodiosQtd = (TextView) findViewById(R.id.episodiosQtd);
		episodiosQtd.setText("Episódios: "+episodios.size());
		
		ListView listEpisodiosPodcast = (ListView) findViewById(R.id.listEpisodiosPodcast);				
		
		EpisodioListAdapter episodiosListAdapter = new EpisodioListAdapter(this, episodios);
		episodiosListAdapter.notifyDataSetChanged();
						
		listEpisodiosPodcast.setAdapter(episodiosListAdapter);
		
	}
	
}
