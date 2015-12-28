package com.fa7.estagio3.podcastmanager.adapters;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fa7.estagio3.podcastmanager.activities.EpisodioActivity;
import com.fa7.estagio3.podcastmanager.entities.Episodio;
import com.fa7.estagio3.podcastmanagerapp.R;

public class EpisodioListAdapter extends BaseAdapter {	
		
	private Context context;	
	private List<Episodio> lista;
	private ProgressDialog dialog;		
	private View v;
	
	public EpisodioListAdapter(Context context, List<Episodio> lista) {
		this.context = context;
		this.lista = lista;						
	}
	public int getCount() {
		return lista.size();
	}
	public Episodio getItem(int posicao) {
		Episodio episodio = lista.get(posicao);	
		return episodio;
	}
	public long getItemId(int posicao) {		
		return posicao;
	}
	public View getView(int position, View convertView, ViewGroup parent) {		

		// Recupera o Podcast da posição atual
		final Episodio episodio = lista.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.list_episodios_podcast_element, null);

		TextView episodioTitle = (TextView) v.findViewById(R.id.episodioTitle);
		episodioTitle.setText(episodio.getTitle());
		
//		TextView episodioDuration = (TextView) v.findViewById(R.id.episodioDuration);
//		String duration = "Duração: "+episodio.getDurationFormated();
//		episodioDuration.setText(duration);
		
//		TextView episodioPubDate = (TextView) v.findViewById(R.id.episodioPubDate);
//		String pubDate = "Publicado: "+episodio.getPubDateFormated();
//		episodioPubDate.setText(pubDate);
		
//		TextView episodioFileSize = (TextView) v.findViewById(R.id.episodioFileSize);
//		String fileSize = episodio.getFileLengthMBytes()+"MB";
//		episodioFileSize.setText(fileSize);
		
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentEpisodio = new Intent(context, EpisodioActivity.class);
				intentEpisodio.putExtra("episodioId", episodio.getId());	
				context.startActivity(intentEpisodio);				
			}
		});
		
		return v;
	}		
}