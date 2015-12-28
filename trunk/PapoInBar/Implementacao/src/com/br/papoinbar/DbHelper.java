package com.br.papoinbar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "bar";
	public static final String TABLE_APERITIVO = "aperitivo";
	private static final String DATABASE_NAME = "papoinbar.db";
	
	public static final String PRECO = "preco";
	public static final String BAR = "bar";		
	public static final String ID = "_id";
	public static final String NOME = "nome";
	public static final String ENDERECO = "endereco";
	public static final String TIPO = "tipo";
	public static final String DESCRICAO = "descricao";
	public static final String QUANTIDADE = "quantidade";

	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NAME + "( " + ID
			+ " integer primary key autoincrement, " + NOME
			+ " text not null, " + ENDERECO + " text not null, " 
			+ TIPO + " text not null, "
			+ DESCRICAO  + " text not null);";
	
	private static final String DATABASE_CREATE_APERITIVO = "create table "
			+ TABLE_APERITIVO + "( " + ID
			+ " integer primary key autoincrement, " + NOME
			+ " text not null, " + PRECO + " double, " 
			+ BAR + " long,"
			+ QUANTIDADE + " integer, "
			+ DESCRICAO  + " text null);";
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//super()
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE_APERITIVO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "	+ newVersion + ", which will destroy all old data");
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_APERITIVO);
		onCreate(db);
	}


}
