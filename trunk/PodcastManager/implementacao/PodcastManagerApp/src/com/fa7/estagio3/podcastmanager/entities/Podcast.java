package com.fa7.estagio3.podcastmanager.entities;

import java.util.List;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;


public class Podcast {
	
	private Long id;
	private String trackName;
	private String coverUrl;
	private String title;
	private String feedUrl;
	private String siteUrl;
	private String author;
	private String description = "";
	
	private String trackID;
	private List<Episodio> episodios;
	
	public static String[] colunas = new String[] { Podcasts._ID,
													Podcasts.TITLE,
													Podcasts.COVERURL,
													Podcasts.FEEDURL, 
													Podcasts.SITEURL,
													Podcasts.AUTHOR, 
													Podcasts.DESCRIPTION };
	
	public static final String AUTHORITY = "com.fa7.estagio3.podcasmtmanager.entities.podcast";		
	
	public static final class Podcasts implements BaseColumns {
		
		// N�o pode instanciar esta Classe
		private Podcasts() {
		}
	
		// content://com.fa7.estagio3.podcasmtmanager.entities.podcast
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/podcasts");
	
		// Mime Type para todos os podcasts
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.podcasts";
	
		// Mime Type para um único carro
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.podcasts";
	
		// Ordena��o default para inserir no order by
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
		public static final String TITLE = "title";
		public static final String COVERURL = "coverUrl";
		public static final String FEEDURL = "feedUrl";
		public static final String SITEURL = "siteUrl";
		public static final String AUTHOR = "author";		
		public static final String DESCRIPTION = "description";
	
		// Método que constrói uma Uri para um Podcast específico, com o seu id
		public static Uri getUriId(long id) {
			// Adiciona o id na URI default do /podcasts
			Uri uriPodcast = ContentUris.withAppendedId(Podcasts.CONTENT_URI, id);
			return uriPodcast;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackID() {
		return trackID;
	}

	public void setTrackID(String trackID) {
		this.trackID = trackID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFeedUrl() {
		return feedUrl;
	}

	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		this.episodios = episodios;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}	
	
}
