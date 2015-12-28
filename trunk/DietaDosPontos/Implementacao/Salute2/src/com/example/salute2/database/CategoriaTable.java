package com.example.salute2.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CategoriaTable {
	public static final String TABLE_CATEGORIA = "categoria";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NOME = "nome";
	  
	  

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "create table " 
	      + TABLE_CATEGORIA
	      + "(" 
	      + COLUMN_ID + " integer primary key autoincrement, " 
	      + COLUMN_NOME + " text not null, " 
	      + " text not null" 
	      + ");";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(AlimentoTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
	    onCreate(database);
	  }
	} 

