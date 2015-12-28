package com.fa7.estagio3.podcastmanager.activities;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fa7.estagio3.podcastmanager.adapters.PodcastFeedAdapter;
import com.fa7.estagio3.podcastmanager.entities.Podcast;
import com.fa7.estagio3.podcastmanager.repositorios.RepositorioPodcast;
import com.fa7.estagio3.podcastmanager.util.Util;
import com.fa7.estagio3.podcastmanagerapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class PodcastSearchFeedActivity extends Activity implements OnClickListener {

	private ProgressDialog dialog;	
	private EditText inputSearchFeedPodcast;
	private TextView msgRetorno;
	private ListView listReturnSearhFeed = null;
	private List<Podcast> listFeedsPodcasts = new ArrayList<Podcast>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_podcast_search_feed);
		Button b = (Button) findViewById(R.id.button_search_feed_podcast);
		b.setOnClickListener(this);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_podcast_search_feed, container, false);        
        return rootView;
    }

	@Override
	public void onClick(View view) {

		inputSearchFeedPodcast = (EditText) findViewById(R.id.input_search_feed_podcast);
		listReturnSearhFeed = (ListView) findViewById(R.id.listRestultSearchFeeds);
		msgRetorno = (TextView) findViewById(R.id.msgReturnSearchFeeds);
		
		String valueSearch = inputSearchFeedPodcast.getText().toString();
		
		if(!valueSearch.equals("")){
			Map<String, String> paramsSearch = new HashMap<String, String>();
			paramsSearch.put("media", "podcast");
			paramsSearch.put("country", "BR");
			paramsSearch.put("term", valueSearch.replaceAll(" ", "+"));			
			
			dialog = ProgressDialog.show(this, "Exemplo legal", "Buscando feeds...");

			httpGetFeedsPodcasts("http://itunes.apple.com/search?"+Util.mapParamsToString(paramsSearch));
			
		}else{						
			
			Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Presta atenção!");
			alert.setMessage("Escreve alguma coisa aí criatura!");
			alert.setPositiveButton("OK",null);
			alert.show();
			
			msgRetorno.setText(null);
			clearLists();
		}
				
	}
	
	public void populateListFeedsPodcasts(String stringFileJson){
							
		try {
			JSONObject jsonObject = new JSONObject(stringFileJson);
			int resultCount = jsonObject.getInt("resultCount");
			
			clearLists();
			
			if(resultCount > 0){																
				
				//monta a lista dos feeds de podcasts
				msgRetorno.setText("Quantidade encontrada: "+resultCount);														
								
				JSONArray results = jsonObject.getJSONArray("results");				
				
				RepositorioPodcast repositorioPodcast  = new RepositorioPodcast(this);
				for(int i = 0; i < resultCount; i++){
					JSONObject feedPodcast = results.getJSONObject(i);
					Podcast podcast = new Podcast();
					podcast.setTrackName(feedPodcast.getString("trackName"));
					podcast.setAuthor(feedPodcast.getString("artistName"));
					podcast.setCoverUrl(feedPodcast.getString("artworkUrl600"));
					podcast.setFeedUrl(feedPodcast.getString("feedUrl"));					
					
					listFeedsPodcasts.add(podcast);
				}
				
				PodcastFeedAdapter podcastAdapter = new PodcastFeedAdapter(this, listFeedsPodcasts);
				podcastAdapter.notifyDataSetChanged();
								
				listReturnSearhFeed.setAdapter(podcastAdapter);
				
			}else{
				msgRetorno.setText("Nenhum podcast foi encontrado :(");
			}
			
			
		}catch (JSONException j){
			j.printStackTrace();
		}finally{
			dialog.dismiss();
		}
	}
	
	private void httpGetFeedsPodcasts(String url) {
		
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new AsyncHttpResponseHandler() {
		    @Override
		    public void onSuccess(String response) {
		    	populateListFeedsPodcasts(response);
		    }
		});
		
	}
	
	public void clearLists(){
		listReturnSearhFeed.setAdapter(null);
		listFeedsPodcasts.clear();
	}
}
