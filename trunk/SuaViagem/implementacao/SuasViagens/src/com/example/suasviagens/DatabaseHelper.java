package com.example.suasviagens;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String BANCO_DADOS = "SuasViagens";
	private static int VERSAO = 1;



	public DatabaseHelper(Context context) {
		super(context, BANCO_DADOS, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {


		db.execSQL("CREATE TABLE viagem (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" destino TEXT, tipo_viagem INTEGER, data_chegada TEXT," +
				" data_saida TEXT, orcamento DOUBLE);");
		
		db.execSQL("CREATE TABLE gasto (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				" tipo_gasto INTEGER, data DATE, valor DOUBLE," +
				" descricao TEXT, local TEXT, viagem_id INTEGER," +
				" FOREIGN KEY(viagem_id) REFERENCES viagem(_id));");
	
	}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {}
	}
