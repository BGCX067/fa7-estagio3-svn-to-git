package com.fa7.estagio3.podcastmanager.repositorios.scripts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fa7.estagio3.podcastmanager.util.SQLiteHelper;

public class RepositorioScript {
	
	protected SQLiteDatabase db;
	private SQLiteHelper dbHelper;
	private static final String NOME_BANCO = "podcast_manager";
	private static final int VERSAO_BANCO = 7;
	
	private static final String[] SCRIPT_DATABASE_DELETE  = new String[] {
		"DROP TABLE IF EXISTS podcasts;",
		"DROP TABLE IF EXISTS episodios;"
	};
	
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"create table podcasts ( _id integer primary key autoincrement, title text not null, coverUrl text, feedUrl text not null, siteUrl text not null, author text not null, description text not null);",
		"create table episodios ( _id integer primary key autoincrement, idPodcast integer not null, title text not null, description text not null, pubDate text not null, duration text not null, position integer not null, fileUrl text not null, fileLength text not null, postUrl text not null, listened integer not null, path text not null);"
	};

	public RepositorioScript(Context ctx) {
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, RepositorioScript.NOME_BANCO, RepositorioScript.VERSAO_BANCO,
				RepositorioScript.SCRIPT_DATABASE_CREATE, RepositorioScript.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar também
		db = dbHelper.getWritableDatabase();

	}

	public void clearDb() {
		
		int qtdeScriptsDelete = SCRIPT_DATABASE_DELETE.length;
		
		for (int i = 0; i < qtdeScriptsDelete; i++) {
			String sql = SCRIPT_DATABASE_DELETE[i];			
			// Cria o banco de dados executando o script de cria��o
			db.execSQL(sql);
		}
		
		int qtdeScriptsCreate = SCRIPT_DATABASE_CREATE.length;

		// Executa cada sql passado como par�metro
		for (int i = 0; i < qtdeScriptsCreate; i++) {
			String sql = SCRIPT_DATABASE_CREATE[i];			
			// Cria o banco de dados executando o script de cria��o
			db.execSQL(sql);
		}
	}
	
	public void fechar() {
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
	
}
