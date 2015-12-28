package com.fa7.estagio3.podcastmanager.adapters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fa7.estagio3.podcastmanager.activities.PodcastsSubscribedsActivity;
import com.fa7.estagio3.podcastmanager.entities.Episodio;
import com.fa7.estagio3.podcastmanager.entities.Podcast;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioEpisodio;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioPodcast;
import com.fa7.estagio3.podcastmanager.util.DateTimeUtil;
import com.fa7.estagio3.podcastmanagerapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class PodcastFeedAdapter extends BaseAdapter {	
		
	private Context context;
	private Podcast podcast;
	private List<Podcast> lista;
	private ProgressDialog dialog;
	private String feedUrl;
	private String author;
	private String coverUrl;
	private List<Episodio> episodios = new ArrayList<Episodio>();
	private Button buttonSubscribe;
	private View v;
	
	public PodcastFeedAdapter(Context context, List<Podcast> lista) {
		this.context = context;
		this.lista = lista;						
	}
	public int getCount() {
		return lista.size();
	}
	public Podcast getItem(int posicao) {
		Podcast podcast = lista.get(posicao);	
		return podcast;
	}
	public long getItemId(int posicao) {
		return posicao;
	}
	public View getView(int position, View convertView, ViewGroup parent) {		

		// Recupera o Podcast da posição atual
		final Podcast podcast = lista.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.list_podcast_feed_element, null);

		// Atualiza o valor do Text para o nome do Podcast
		TextView feedTrackName = (TextView) v.findViewById(R.id.feedTrackName);
		feedTrackName.setText(podcast.getTrackName());
		
		buttonSubscribe = (Button) v.findViewById(R.id.button_subscribe_feed_podcast);		
		buttonSubscribe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View vClick) {			
				
				dialog = ProgressDialog.show(context, "Aguarde", "Gravando informações do "+podcast.getTrackName());
				
				feedUrl = podcast.getFeedUrl();
				author = podcast.getAuthor();
				coverUrl = podcast.getCoverUrl();
				
				httpGetFeedPodcastXml(podcast.getFeedUrl());
				
			}
		});
		
		return v;
	}	
	
	public void httpGetFeedPodcastXml(String url) {
		
		url = "http://benjaminstudio.com.br/apppodcastmanager_api/?"+url;
		
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {		 		   
		    	
		    	try {
					JSONObject jsonObject = new JSONObject(response);
					
					JSONObject podcastFeed = jsonObject.getJSONObject("podcast");
					
					podcast = new Podcast();
					podcast.setTitle(podcastFeed.getString("title"));
					podcast.setSiteUrl(podcastFeed.getString("link"));
					podcast.setDescription(podcastFeed.getString("description"));
					podcast.setFeedUrl(feedUrl);
					podcast.setAuthor(author);
					podcast.setCoverUrl(coverUrl);
					
					JSONArray jsonEpisodios = jsonObject.getJSONArray("episodios");
					int qtdEpisodios = jsonEpisodios.length();
					Log.e("episodios",""+qtdEpisodios);
					for (int i = 0; i < qtdEpisodios; i++) {
						JSONObject item = jsonEpisodios.getJSONObject(i);
						Episodio episodio = new Episodio();
						episodio.setPosition(0);
						episodio.setPath("");
						episodio.setTitle(item.getString("title"));
						episodio.setPostUrl(item.getString("link"));
						episodio.setFileUrl(item.getString("url"));
						episodio.setDescription(item.getString("description"));
						episodio.setFileLength(item.getString("length"));
						
						Date d = new Date(item.getString("pubDate"));
		            	episodio.setPubDate(String.valueOf(d.getTime()));				
												
		            	episodio.setDuration(DateTimeUtil.stringTimeToIntegerSeconds(item.getString("duration")));
		            	
		            	episodios.add(episodio);
					}
					
					// tratamento da inserção do podcast
					RepositorioPodcast repositorioPodcast = new RepositorioPodcast(context);
					long idPodcast = repositorioPodcast.insert(podcast);
					podcast.setId(idPodcast);
	
					if(podcast.getId() != null){
						// Tratamento da inserção dos episódios do podcast
						RepositorioEpisodio repositorioEpisodio = new RepositorioEpisodio(context);
						for (Episodio episodio : episodios) {
							episodio.setPodcast(podcast);
							repositorioEpisodio.insert(episodio);
						}
	
						List<Podcast> podcastsInseridos = repositorioPodcast.listPodcasts();
						Log.e("PODCASTSINSERIDOS", "" + podcastsInseridos.size());
	
						List<Episodio> episodiosInseridos = repositorioEpisodio.listEpisodios();
						Log.e("episódiosINSERIDOS", "" + episodiosInseridos.size());
					}	

				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					dialog.dismiss();
		    		
					Intent intent = new Intent(context, PodcastsSubscribedsActivity.class);
					context.startActivity(intent);
				}
		    	
		    }
		});
		
	}

}