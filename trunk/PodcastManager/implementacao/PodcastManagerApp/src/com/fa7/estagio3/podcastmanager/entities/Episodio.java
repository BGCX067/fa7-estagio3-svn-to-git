package com.fa7.estagio3.podcastmanager.entities;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.fa7.estagio3.podcastmanager.util.DateTimeUtil;
import com.fa7.estagio3.podcastmanager.util.Util;


public class Episodio {

	private long id;
	private Podcast podcast;	
	private String title;
	private String pubDate;
	private String description;	
	private String duration;
	private long position;
	private String fileUrl;
	private String fileLength;
	private String postUrl;
	private int listened;
	private String path;
	
	public static String[] colunas = new String[] { Episodios._ID, 
													Episodios.ID_PODCAST, 
													Episodios.TITLE, 													
													Episodios.DESCRIPTION,
													Episodios.PUB_DATE,
													Episodios.DURATION, 
													Episodios.POSITION, 
													Episodios.FILE_URL,
													Episodios.FILE_LENGTH,
													Episodios.POST_URL, 
													Episodios.LISTENED, 
													Episodios.PATH };
	
	public static final String AUTHORITY = "com.fa7.estagio3.podcasmtmanager.entities.episodio";
	
	public static final class Episodios implements BaseColumns {
		// content://com.fa7.estagio3.podcasmtmanager.entities.episodio
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/episodios");
	
		// Mime Type para todos os episodios
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.episodios";
	
		// Mime Type para um único episodio
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.episodios";
		
		// Ordena��o default para inserir no order by
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		
		public static final String ID_PODCAST = "idPodcast";
		public static final String TITLE = "title";		
		public static final String DESCRIPTION = "description";		
		public static final String PUB_DATE = "pubDate";		
		public static final String DURATION = "duration";
		public static final String POSITION = "position";
		public static final String FILE_URL = "fileUrl";
		public static final String FILE_LENGTH = "fileLength";
		public static final String POST_URL = "postUrl";
		public static final String LISTENED = "listened";
		public static final String PATH = "path";
	
		public static Uri getUriId(long id) {
			// Adiciona o id na URI default do /episodios
			Uri uriEpisodio = ContentUris.withAppendedId(Episodios.CONTENT_URI, id);
			return uriEpisodio;
		}		
	}

	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Podcast getPodcast() {
		return podcast;
	}

	public void setPodcast(Podcast podcast) {
		this.podcast = podcast;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubDate() {
		return pubDate;
	}
	
	public String getPubDateFormated(){		
	    return DateTimeUtil.formatDate(Long.parseLong(this.getPubDate()), "dd/MM/yyyy");	    
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}
	
	public String getDurationFormated(){		
	    return DateTimeUtil.formatDate(Long.parseLong(this.getDuration()), "HH:mm:ss");
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getListened() {
		return listened;
	}

	public void setListened(int listened) {
		this.listened = listened;
	}

	public String getFileLength() {
		return fileLength;
	}
	
	public String getFileLengthMBytes(){
		return Util.convertBytesToMegaBytes(fileLength);
	}

	public void setFileLength(String fileLength) {
		this.fileLength = fileLength;
	}		
	
}