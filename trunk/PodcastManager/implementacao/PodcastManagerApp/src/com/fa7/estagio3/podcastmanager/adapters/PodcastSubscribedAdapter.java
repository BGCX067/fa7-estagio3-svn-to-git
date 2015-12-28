package com.fa7.estagio3.podcastmanager.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fa7.estagio3.podcastmanager.activities.EpisodiosListActivity;
import com.fa7.estagio3.podcastmanager.activities.PodcastsSubscribedsActivity;
import com.fa7.estagio3.podcastmanager.entities.Podcast;
import com.fa7.estagio3.podcastmanager.util.ImageLoader;
import com.fa7.estagio3.podcastmanagerapp.R;

public class PodcastSubscribedAdapter extends BaseAdapter {	
		
	private Context context;
	private List<Podcast> listPodcasts;
	int layoutResourceId;
	
	public PodcastSubscribedAdapter(Context context, int layoutResourceId, List<Podcast> listPodcasts) {
		this.context = context;
		this.layoutResourceId = layoutResourceId;  
		this.listPodcasts = listPodcasts;
	}
	
	public int getCount() {
		return listPodcasts.size();
	}
	
	public Object getItem(int position) {
		Podcast podcast = listPodcasts.get(position);	
		return podcast;
	}
	
	public long getItemId(int position) {		
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {		

//		// Recupera o Podcast da posição atual
		final Podcast podcast = listPodcasts.get(position);
		
		View row = convertView;
		RecordHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new RecordHolder();			
			holder.coverPodcast = (ImageView) row.findViewById(R.id.podcastCover);
			holder.titlePodcast = (TextView) row.findViewById(R.id.podcastTitle);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}		
		
		holder.titlePodcast.setText(podcast.getTitle());		
		ImageLoader imageLoader = new ImageLoader(context);							
		imageLoader.DisplayImage(podcast.getCoverUrl(), holder.coverPodcast);
		
		holder.coverPodcast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intentEpisodios = new Intent(context, EpisodiosListActivity.class);
				intentEpisodios.putExtra("podcastId", podcast.getId());	
				context.startActivity(intentEpisodios);
			}
		});
		
        return row;
	}		

	static class RecordHolder {
		TextView titlePodcast;
		ImageView coverPodcast;
	}	
	
}
