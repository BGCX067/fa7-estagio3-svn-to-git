package com.example.salute2.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RefeicaoTable {
		 
	// Database table
		  public static final String TABLE_REFEICAO = "refeicao";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_CAT = "categoria";
		  public static final String COLUMN_ID_PROD = "produto";
		  

		  // Database creation SQL statement
		  private static final String DATABASE_CREATE = "create table " 
		      + TABLE_REFEICAO
		      + "(" 
		      + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_CAT + " text not null, " 
		      + COLUMN_ID_PROD + " text not null," 
		      + " text not null" 
		      + ");";

		  public static void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
		  }

		  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
		      int newVersion) {
		    Log.w(RefeicaoTable.class.getName(), "Upgrading database from version "
		        + oldVersion + " to " + newVersion
		        + ", which will destroy all old data");
		    database.execSQL("DROP TABLE IF EXISTS " + TABLE_REFEICAO);
		    onCreate(database);
		  }
	} 

