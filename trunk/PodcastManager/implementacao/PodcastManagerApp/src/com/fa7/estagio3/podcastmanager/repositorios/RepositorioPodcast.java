package com.fa7.estagio3.podcastmanager.repositorios;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fa7.estagio3.podcastmanager.entities.Podcast;
import com.fa7.estagio3.podcastmanager.entities.Podcast.Podcasts;

public class RepositorioPodcast {

	private static final String CATEGORIA = "podcast_manager";
	private static final String DB_NAME = "podcast_manager";
	public static final String TABLE_NAME = "podcasts";
	protected SQLiteDatabase db;
	
	public RepositorioPodcast(Context context){
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
	}
	
	public RepositorioPodcast(){}
	
	public long save(Podcast podcast){
		long id = podcast.getId();
		
		if(id != 0){
			update(podcast);
		}else{
			id = insert(podcast);
		}
		
		return id;
	}
	
	public long insert(Podcast podcast) throws SQLException{
		ContentValues values = new ContentValues();			
		values.put(Podcasts.TITLE, podcast.getTitle());		
		values.put(Podcasts.COVERURL, podcast.getCoverUrl());		
		values.put(Podcasts.FEEDURL, podcast.getFeedUrl());		
		values.put(Podcasts.SITEURL, podcast.getSiteUrl());		
		values.put(Podcasts.AUTHOR, podcast.getAuthor());		
		values.put(Podcasts.DESCRIPTION, podcast.getDescription());
		
		long id = insert(values);
		return id;
	}
	
	public long insert(ContentValues values) throws SQLException{
		long id = db.insert(TABLE_NAME,"",values);
		return id;
	}
	
	public int update(Podcast podcast){
		ContentValues values = new ContentValues();			
		values.put(Podcasts.TITLE, podcast.getTitle());		
		values.put(Podcasts.COVERURL, podcast.getCoverUrl());		
		values.put(Podcasts.FEEDURL, podcast.getFeedUrl());		
		values.put(Podcasts.SITEURL, podcast.getSiteUrl());		
		values.put(Podcasts.AUTHOR, podcast.getAuthor());		
		values.put(Podcasts.DESCRIPTION, podcast.getDescription());
		
		String _id = String.valueOf(podcast.getId());
		
		String where = Podcasts._ID +"=?";
		String[] whereArgs = new String[] {_id};
		
		int count = update(values, where, whereArgs);
		
		return count;
	}
	
	public int update(ContentValues values, String where, String[] whereArgs){
		int count = db.update(TABLE_NAME, values, where, whereArgs);
		Log.i(CATEGORIA,"Atualizou ["+count+"] registros");
		return count;
	}
	
	public int delete(long id){
		String where = Podcasts._ID + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };
		
		int count = delete(where,whereArgs);
		return count;
	}
	
	public int delete(String where, String[] whereArgs){
		int count = db.delete(TABLE_NAME, where, whereArgs);
		Log.i(CATEGORIA, "Deletou [" + count + "] registros");
		return count;
	}
	
	// Retorna um cursor com todos os podcasts
	public Cursor getCursor() {
		try {
			// select * from podcasts
			return db.query(TABLE_NAME, Podcast.colunas, null, null, null, null, null, null);				
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os podcasts: " + e.toString());
			return null;
		}
	}
	
	public Podcast getPodcastById(long id){
		try {
			
			String where = Podcasts._ID + "=?";
			
			String _id = String.valueOf(id);
			String[] whereArgs = new String[] { _id };
			
			// select * from podcasts
			Cursor cursor = db.query(TABLE_NAME, Podcast.colunas, where, whereArgs, null, null, null, null);
			
			if(cursor.moveToFirst()){
				Podcast podcast = new Podcast();
				
				int idxId = cursor.getColumnIndex(Podcasts._ID);				
				int idxTitle = cursor.getColumnIndex(Podcasts.TITLE);
				int idxCoverUrl = cursor.getColumnIndex(Podcasts.COVERURL);
				int idxFeedUrl = cursor.getColumnIndex(Podcasts.FEEDURL);
				int idxSiteUrl = cursor.getColumnIndex(Podcasts.SITEURL);
				int idxAuthor = cursor.getColumnIndex(Podcasts.AUTHOR);
				int idxDescription = cursor.getColumnIndex(Podcasts.DESCRIPTION);
				
				podcast.setId(cursor.getLong(idxId));					
				podcast.setTitle(cursor.getString(idxTitle));
				podcast.setCoverUrl(cursor.getString(idxCoverUrl));
				podcast.setFeedUrl(cursor.getString(idxFeedUrl));
				podcast.setSiteUrl(cursor.getString(idxSiteUrl));
				podcast.setAuthor(cursor.getString(idxAuthor));
				podcast.setDescription(cursor.getString(idxDescription));
				
				return podcast;
				
			}
			
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os podcast: " + e.toString());
			return null;
		}
		
		return null;
	}
	
	// Retorna uma lista com todos os podcasts
	public List<Podcast> listPodcasts() {
		Cursor c = getCursor();

		List<Podcast> podcasts = new ArrayList<Podcast>();

		if (c.moveToFirst()) {

			// Recupera os �ndices das colunas
			int idxId = c.getColumnIndex(Podcasts._ID);				
			int idxTitle = c.getColumnIndex(Podcasts.TITLE);
			int idxCoverUrl = c.getColumnIndex(Podcasts.COVERURL);
			int idxFeedUrl = c.getColumnIndex(Podcasts.FEEDURL);
			int idxSiteUrl = c.getColumnIndex(Podcasts.SITEURL);
			int idxAuthor = c.getColumnIndex(Podcasts.AUTHOR);
			int idxDescription = c.getColumnIndex(Podcasts.DESCRIPTION);				

			// Loop até o final
			do {
				Podcast podcast = new Podcast();
				podcasts.add(podcast);

				// recupera os atributos de carro
				podcast.setId(c.getLong(idxId));					
				podcast.setTitle(c.getString(idxTitle));
				podcast.setCoverUrl(c.getString(idxCoverUrl));
				podcast.setFeedUrl(c.getString(idxFeedUrl));
				podcast.setSiteUrl(c.getString(idxSiteUrl));
				podcast.setAuthor(c.getString(idxAuthor));
				podcast.setDescription(c.getString(idxDescription));					

			} while (c.moveToNext());
		}

		return podcasts;
	}
	
	// Fecha o banco
	public void fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
}
