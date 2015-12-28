package com.fa7.estagio3.podcastmanager.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Implementacao de SQLiteOpenHelper
 * 
 * Classe utilitária para abrir, criar, e atualizar o banco de dados
 * 
 * @author ricardo
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	private static final String CATEGORIA = "podcastmanager";
	
	private String[] scriptSQLCreate;
	private String[] scriptSQLDelete;

	/**
	 * Cria uma instância de SQLiteHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versão do banco de dados (se for diferente é para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	public SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptSQLDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}

	@Override
	// Criar novo banco...
	public void onCreate(SQLiteDatabase db) {
		Log.e(CATEGORIA, "Criando banco com sql");
		int qtdeScripts = scriptSQLCreate.length;

		// Executa cada sql passado como par�metro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			Log.e(CATEGORIA, sql);
			// Cria o banco de dados executando o script de cria��o
			db.execSQL(sql);
		}
	}

	@Override
	// Mudou a vers�o...
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		Log.e(CATEGORIA, "Atualizando da versão " + versaoAntiga + " para " + novaVersao + ". Todos os registros ser�o deletados.");
		int qtdeScripts = scriptSQLDelete.length;
		
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLDelete[i];
			Log.e(CATEGORIA, sql);
			// Cria o banco de dados executando o script de cria��o
			db.execSQL(sql);
		}
		
		onCreate(db);
	}
}