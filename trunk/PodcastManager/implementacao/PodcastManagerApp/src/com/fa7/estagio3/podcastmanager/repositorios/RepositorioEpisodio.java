package com.fa7.estagio3.podcastmanager.repositorios;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fa7.estagio3.podcastmanager.entities.Episodio;
import com.fa7.estagio3.podcastmanager.entities.Episodio.Episodios;
import com.fa7.estagio3.podcastmanager.entities.Podcast.Podcasts;
import com.fa7.estagio3.podcastmanager.entities.Podcast;

public class RepositorioEpisodio {

	private static final String CATEGORIA = "podcast_manager";
	private static final String DB_NAME = "podcast_manager";
	public static final String TABLE_NAME = "episodios";
	protected SQLiteDatabase db;
	
	public RepositorioEpisodio(Context context){
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
	}
	
	public RepositorioEpisodio(){}
	
	public long save(Episodio episodio){
		long id = episodio.getId();
		
		if(id != 0){
			update(episodio);
		}else{
			id = insert(episodio);
		}
		
		return id;
	}
	
	public long insert(Episodio episodio){
		ContentValues values = new ContentValues();			
		values.put(Episodios.ID_PODCAST, episodio.getPodcast().getId());				
		values.put(Episodios.TITLE, episodio.getTitle());		
		values.put(Episodios.DESCRIPTION, episodio.getDescription());						
		values.put(Episodios.PUB_DATE, episodio.getPubDate());						
		values.put(Episodios.DURATION, episodio.getDuration());				
		values.put(Episodios.POSITION, episodio.getPosition());				
		values.put(Episodios.FILE_URL, episodio.getFileUrl());
		values.put(Episodios.FILE_LENGTH, episodio.getFileLength());
		values.put(Episodios.POST_URL, episodio.getPostUrl());				
		values.put(Episodios.LISTENED, episodio.getListened());				
		values.put(Episodios.PATH, episodio.getPath());
					
		
		long id = insert(values);
		return id;		
	}
	
	public long insert(ContentValues values){
		long id = db.insert(TABLE_NAME,"",values);
		return id;
	}
	
	public int update(Episodio episodio){
		ContentValues values = new ContentValues();			
		values.put(Episodios.ID_PODCAST, episodio.getPodcast().getId());				
		values.put(Episodios.TITLE, episodio.getTitle());		
		values.put(Episodios.DESCRIPTION, episodio.getDescription());						
		values.put(Episodios.PUB_DATE, episodio.getPubDate());						
		values.put(Episodios.DURATION, episodio.getDuration());				
		values.put(Episodios.POSITION, episodio.getPosition());				
		values.put(Episodios.FILE_URL, episodio.getFileUrl());
		values.put(Episodios.FILE_LENGTH, episodio.getFileLength());
		values.put(Episodios.POST_URL, episodio.getPostUrl());				
		values.put(Episodios.LISTENED, episodio.getListened());				
		values.put(Episodios.PATH, episodio.getPath());
		
		String _id = String.valueOf(episodio.getId());
		
		String where = Episodios._ID +"=?";
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
		String where = Episodios._ID + "=?";
		
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
	
	// Retorna um cursor com todos os episodios
		public Cursor getCursor() {
			try {
				// select * from episodios
				return db.query(TABLE_NAME, Episodio.colunas, null, null, null, null, null, null);				
			} catch (SQLException e) {
				Log.e(CATEGORIA, "Erro ao buscar os episodios: " + e.toString());
				return null;
			}
		}
		
		// Retorna uma lista com todos os episodios
		public List<Episodio> listEpisodios() {
			Cursor c = getCursor();

			List<Episodio> episodios = new ArrayList<Episodio>();

			if (c.moveToFirst()) {
				
				// Recupera os índices das colunas
				int idxId = c.getColumnIndex(Episodios._ID);
				int idxIdPodcast = c.getColumnIndex(Episodios.ID_PODCAST);
				int idxTitle = c.getColumnIndex(Episodios.TITLE);
				int idxDescription = c.getColumnIndex(Episodios.DESCRIPTION);
				int idxPubDate = c.getColumnIndex(Episodios.PUB_DATE);
				int idxDuration = c.getColumnIndex(Episodios.DURATION);
				int idxPosition = c.getColumnIndex(Episodios.POSITION);				
				int idxFileUrl = c.getColumnIndex(Episodios.FILE_URL);
				int idxFileLength = c.getColumnIndex(Episodios.FILE_LENGTH);
				int idxPostUrl = c.getColumnIndex(Episodios.POST_URL);				
				int idxListened = c.getColumnIndex(Episodios.LISTENED);				
				int idxPath = c.getColumnIndex(Episodios.PATH);				

				// Loop até o final
				do {
					Episodio episodio = new Episodio();
					episodios.add(episodio);
					
					Podcast podcast = new Podcast();
					podcast.setId(c.getLong(idxIdPodcast));
					
					// recupera os atributos de episodio
					episodio.setId(c.getLong(idxId));
					episodio.setPodcast(podcast);
					episodio.setTitle(c.getString(idxTitle));
					episodio.setDescription(c.getString(idxDescription));
					episodio.setPubDate(c.getString(idxPubDate));
					episodio.setDuration(c.getString(idxDuration));
					episodio.setPosition(c.getInt(idxPosition));
					episodio.setFileUrl(c.getString(idxFileUrl));
					episodio.setFileLength(c.getString(idxFileLength));
					episodio.setPostUrl(c.getString(idxPostUrl));
					episodio.setListened(c.getInt(idxListened));					
					episodio.setPath(c.getString(idxPath));
					

				} while (c.moveToNext());
			}

			return episodios;
		}
		
		public Episodio getEpisodioById(long id){
			Episodio episodio = new Episodio();
			
			try {
				
				String where = Episodios._ID + "=?";
				
				String _id = String.valueOf(id);
				String[] whereArgs = new String[] { _id };
				
				// select * from podcasts
				Cursor c = db.query(TABLE_NAME, Episodio.colunas, where, whereArgs, null, null, null, null);
				
				if(c.moveToFirst()){
					
					
					int idxId = c.getColumnIndex(Episodios._ID);
					int idxIdPodcast = c.getColumnIndex(Episodios.ID_PODCAST);
					int idxTitle = c.getColumnIndex(Episodios.TITLE);
					int idxDescription = c.getColumnIndex(Episodios.DESCRIPTION);
					int idxPubDate = c.getColumnIndex(Episodios.PUB_DATE);
					int idxDuration = c.getColumnIndex(Episodios.DURATION);
					int idxPosition = c.getColumnIndex(Episodios.POSITION);		
					int idxFileUrl = c.getColumnIndex(Episodios.FILE_URL);
					int idxFileLength = c.getColumnIndex(Episodios.FILE_LENGTH);
					int idxPostUrl = c.getColumnIndex(Episodios.POST_URL);				
					int idxListened = c.getColumnIndex(Episodios.LISTENED);				
					int idxPath = c.getColumnIndex(Episodios.PATH);
					
					Podcast podcast = new Podcast();
					podcast.setId(c.getLong(idxIdPodcast));
					
					episodio.setId(c.getLong(idxId));
					episodio.setPodcast(podcast);
					episodio.setTitle(c.getString(idxTitle));
					episodio.setDescription(c.getString(idxDescription));
					episodio.setPubDate(c.getString(idxPubDate));
					episodio.setDuration(c.getString(idxDuration));
					episodio.setPosition(c.getInt(idxPosition));
					episodio.setFileUrl(c.getString(idxFileUrl));
					episodio.setFileLength(c.getString(idxFileLength));
					episodio.setPostUrl(c.getString(idxPostUrl));
					episodio.setListened(c.getInt(idxListened));					
					episodio.setPath(c.getString(idxPath));
					
				}
				
			} catch (SQLException e) {
				Log.e(CATEGORIA, "Erro ao buscar os episodio: " + e.toString());				
			}
			
			return episodio;
		}
		
		public List<Episodio> listEpisodiosByPodcast(Podcast podcast){
			
			List<Episodio> episodios = new ArrayList<Episodio>();
			
			try {
				
				String where = Episodios.ID_PODCAST + "=?";
				
				String idPodcast = String.valueOf(podcast.getId());				
				String[] whereArgs = new String[] { idPodcast };
				String orderBy = Episodios.PUB_DATE + " DESC";
				
				// select * from episodes
				Cursor cursor = db.query(TABLE_NAME, Episodio.colunas, where, whereArgs, null, null, orderBy, null);
				
				if(cursor.getCount() > 0){
					while(cursor.moveToNext()){
						
						int idxId = cursor.getColumnIndex(Episodios._ID);
						int idxIdPodcast = cursor.getColumnIndex(Episodios.ID_PODCAST);
						int idxTitle = cursor.getColumnIndex(Episodios.TITLE);
						int idxDescription = cursor.getColumnIndex(Episodios.DESCRIPTION);
						int idxPubDate = cursor.getColumnIndex(Episodios.PUB_DATE);
						int idxDuration = cursor.getColumnIndex(Episodios.DURATION);
						int idxPosition = cursor.getColumnIndex(Episodios.POSITION);				
						int idxFileUrl = cursor.getColumnIndex(Episodios.FILE_URL);				
						int idxFileLength = cursor.getColumnIndex(Episodios.FILE_LENGTH);				
						int idxPostUrl = cursor.getColumnIndex(Episodios.POST_URL);				
						int idxListened = cursor.getColumnIndex(Episodios.LISTENED);				
						int idxPath = cursor.getColumnIndex(Episodios.PATH);
						
						Episodio episodio = new Episodio();						
						
						Podcast podcastEpisodio = new Podcast();
						podcastEpisodio.setId(cursor.getLong(idxIdPodcast));
						
						// recupera os atributos de episodio
						episodio.setId(cursor.getLong(idxId));
						episodio.setPodcast(podcast);
						episodio.setTitle(cursor.getString(idxTitle));
						episodio.setDescription(cursor.getString(idxDescription));
						episodio.setPubDate(cursor.getString(idxPubDate));
						episodio.setDuration(cursor.getString(idxDuration));
						episodio.setPosition(cursor.getInt(idxPosition));
						episodio.setFileUrl(cursor.getString(idxFileUrl));
						episodio.setFileLength(cursor.getString(idxFileLength));
						episodio.setPostUrl(cursor.getString(idxPostUrl));
						episodio.setListened(cursor.getInt(idxListened));					
						episodio.setPath(cursor.getString(idxPath));
						
						episodios.add(episodio);
					}
				}
				
				return episodios;
				
			} catch (SQLException e) {
				Log.e(CATEGORIA, "Erro ao buscar os episódiosk: " + e.toString());				
			}
			
			return null;
		}
		
		// Fecha o banco
		public void fechar() {
			// fecha o banco de dados
			if (db != null) {
				db.close();
			}
		}
}
