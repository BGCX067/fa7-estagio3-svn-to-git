package com.example.salute2.database;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SaluteDatabaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "salute.db";
  private static final int DATABASE_VERSION = 1;

  private static SaluteDatabaseHelper instance; 
  
  public SaluteDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public static SaluteDatabaseHelper getInstance(Context context){
	  if(instance == null)
		  instance = new SaluteDatabaseHelper(context);
	  
	  return instance;
  }
  public SaluteDatabaseHelper(Context context, String string, Object object, int i) {
	// TODO Auto-generated constructor stub
	  super(context, DATABASE_NAME, null, DATABASE_VERSION);	  
}

// Method is called during creation of the database
  @Override
  public void onCreate(SQLiteDatabase database) {
    AlimentoTable.onCreate(database);
    UsuarioTable.onCreate(database);
    RefeicaoTable.onCreate(database);
    CategoriaTable.onCreate(database);
 }
  

  // Method is called during an upgrade of the database,
  // e.g. if you increase the database version
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion,
      int newVersion) {
    AlimentoTable.onUpgrade(database, oldVersion, newVersion);
    UsuarioTable.onUpgrade(database, oldVersion, newVersion);
    RefeicaoTable.onUpgrade(database, oldVersion, newVersion);
    CategoriaTable.onUpgrade(database, oldVersion, newVersion);
  }
  
  public SQLiteDatabase getDatabase(){
	  return this.getWritableDatabase();
  }
}
 
