package com.example.salute2.database;


import java.util.ArrayList;
import java.util.List;

import com.example.salute2.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioTable {

  // Database table
  public static final String TABLE_USUARIO = "usuario";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NOME = "nome";
  public static final String COLUMN_EMAIL = "email";
  public static final String COLUMN_SENHA = "senha";
  public static final String COLUMN_SEXO = "sexo";
  public static final String COLUMN_IDADE = "idade";
  public static final String COLUMN_PESO = "peso";

  // Database creation SQL statement
  private static final String DATABASE_CREATE = "create table " 
      + TABLE_USUARIO
      + "(" 
      + COLUMN_ID + " integer primary key autoincrement, " 
      + COLUMN_NOME + " text not null, " 
      + COLUMN_EMAIL + " text not null,"
      + COLUMN_SENHA + " text not null,"
      + COLUMN_SEXO + " text not null,"
      + COLUMN_IDADE + " text not null,"
      + COLUMN_PESO + " text not null" 
      + ");";

  public static void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
      int newVersion) {
    Log.w(AlimentoTable.class.getName(), "Upgrading database from version "
        + oldVersion + " to " + newVersion
        + ", which will destroy all old data");
    database.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
    onCreate(database);
  }
  
  public static int count(SaluteDatabaseHelper database)
  {
	  return UsuarioTable.count(database);
  }
  
  private static UsuarioTable instance;
  
  private SQLiteDatabase  dataBase = null;
  
  public static UsuarioTable getInstance(Context context){
	  if(instance == null)
		  instance = new UsuarioTable(context);
	  return instance;
  }
  private UsuarioTable(Context context){
	  SaluteDatabaseHelper saluteDatabaseHelper = SaluteDatabaseHelper.getInstance(context);
	  dataBase = saluteDatabaseHelper.getWritableDatabase();
  }
  
  public void salvar(Usuario usuario){
	  ContentValues values = gerarContentValuesUsuario(usuario);
	  dataBase.insert(TABLE_USUARIO, null, values);
  }
  
  public List<Usuario> recuperarTodos(){
	  String queryReturnAll = "SELECT * FROM" + TABLE_USUARIO;
	  Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
	  List<Usuario> usuario = construirUsuarioPorCursor(cursor);
	  
	  return usuario;
  }
  
  public void deletar(Usuario usuario){
	  String[] valoresParaSubstituir = {
			  String.valueOf(usuario.getId())
	  };
	  dataBase.delete(TABLE_USUARIO, COLUMN_ID + " = ?", valoresParaSubstituir);
  }
  
  public void fecharConexao(){
	  if(dataBase != null && dataBase.isOpen())
		  dataBase.close();
  }
  public List<Usuario> construirUsuarioPorCursor(Cursor cursor){
	  List<Usuario> usuarios = new ArrayList<Usuario>();
	  if(cursor == null)
		  return usuarios;
	  
	  try{
		  if(cursor.moveToFirst()){
			  do{
				  int indexId = cursor.getColumnIndex(COLUMN_ID);
				  int indexNome = cursor.getColumnIndex(COLUMN_NOME);
				  int indexEmail = cursor.getColumnIndex(COLUMN_EMAIL);
				  int indexSenha = cursor.getColumnIndex(COLUMN_SENHA);
				  int indexSexo = cursor.getColumnIndex(COLUMN_SEXO);
				  int indexPeso = cursor.getColumnIndex(COLUMN_PESO);
				  int indexIdade = cursor.getColumnIndex(COLUMN_IDADE);
				  
				  int id = cursor.getInt(indexId);
				  String nome = cursor.getString(indexNome);
				  String email = cursor.getString(indexEmail);
				  String senha = cursor.getString(indexSenha);
				  String sexo = cursor.getString(indexSexo);
				  String peso = cursor.getString(indexPeso);
				  String idade = cursor.getString(indexIdade);
				  
				  Usuario usuario = new Usuario(id, nome, email, senha, sexo, peso, idade);
				  usuarios.add(usuario);
			  } while (cursor.moveToNext());
		  }
	  }finally{
		  cursor.close();
	  }
	  return usuarios;
  }
  
  private ContentValues gerarContentValuesUsuario(Usuario usuario){
	  ContentValues values = new ContentValues();
	  values.put(COLUMN_ID, usuario.getId());
	  values.put(COLUMN_NOME, usuario.getNome());
	  values.put(COLUMN_EMAIL, usuario.getEmail());
	  values.put(COLUMN_SENHA, usuario.getSenha());
	  values.put(COLUMN_SEXO, usuario.getSexo());
	  values.put(COLUMN_PESO, usuario.getPeso());
	  values.put(COLUMN_IDADE, usuario.getIdade());
	  
	  return values;
  }
}





  /*public Usuario select(String userLogin) throws Exception{
		Usuario usuario = null;
		Cursor cursor = null;
		
		SQLiteDatabase sqlLite = new SaluteDatabaseHelper(context).getReadableDatabase();
		 
		String where = "EMAIL = ?";

		String[] colunas = new String[] { COLUMN_NOME, COLUMN_EMAIL, COLUMN_SENHA, COLUMN_PESO, COLUMN_SEXO, COLUMN_IDADE};

		String argumentos[] = new String[] { userLogin};

		cursor = sqlLite.query(UsuarioTable.TABLE_USUARIO, colunas, where, argumentos, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			usuario = new Usuario();
			usuario.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_NOME)));
			usuario.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
			usuario.setSenha(cursor.getString(cursor.getColumnIndex(COLUMN_SENHA)));
			usuario.setPeso(cursor.getString(cursor.getColumnIndex(COLUMN_PESO)));
			usuario.setSenha(cursor.getString(cursor.getColumnIndex(COLUMN_SEXO)));
			usuario.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_IDADE)));
		}

		if (cursor != null)
			cursor.close();

		return usuario;
	}
  public long insert(Usuario usuario) throws Exception {
		SQLiteDatabase sqlLite = new SaluteDatabaseHelper(context).getWritableDatabase();

		ContentValues content = new ContentValues();

		content.put(COLUMN_NOME, usuario.getNome());
		content.put(COLUMN_EMAIL, usuario.getEmail());
		content.put(COLUMN_SENHA, usuario.getSenha());
		content.put(COLUMN_PESO, usuario.getPeso());
		content.put(COLUMN_SEXO, usuario.getSexo());
		content.put(COLUMN_IDADE, usuario.getIdade());
		
		
		return sqlLite.insert(UsuarioTable.TABLE_USUARIO, null, content);
	}
	public int update(Usuario usuario) throws Exception {
		SQLiteDatabase sqlLite = new SaluteDatabaseHelper(context).getWritableDatabase();

		ContentValues content = new ContentValues();

		content.put(COLUMN_NOME, usuario.getNome());
		content.put(COLUMN_EMAIL, usuario.getEmail());
		content.put(COLUMN_SENHA, usuario.getSenha());
		content.put(COLUMN_PESO, usuario.getPeso());
		content.put(COLUMN_SEXO, usuario.getSexo());
		content.put(COLUMN_IDADE, usuario.getIdade());

              String where = "EMAIL = ?";

              String argumentos[] { usuario.getEmail()};
            
              
		return sqlLite.update(UsuarioTable.TABLE_USUARIO, content, where, argumentos);
	}
	public int delete(String email) {
        SQLiteDatabase sqlLite = new SaluteDatabaseHelper(context).getWritableDatabase();  

        String where = "EMAIL = ? ";

        String[] args = new String[] { email };

        return sqlLite.delete(UsuarioTable.TABLE_USUARIO, where, args);
}

} */