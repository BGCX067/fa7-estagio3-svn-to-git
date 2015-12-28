package com.br.papoinbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.br.papoinbar.modelo.Aperitivo;

public class DBAdapterAperitivo {
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private String[] allColumns = { DbHelper.ID, DbHelper.NOME, DbHelper.PRECO,
			DbHelper.BAR, DbHelper.QUANTIDADE, DbHelper.DESCRICAO };

	public DBAdapterAperitivo(Context context) {
		dbHelper = new DbHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Aperitivo createAperitivo(String nome, Double preco, long idBar) {
		ContentValues values = new ContentValues();
		values.put(DbHelper.NOME, nome);
		values.put(DbHelper.PRECO, preco);
		values.put(DbHelper.BAR, idBar);
		values.put(DbHelper.QUANTIDADE, 1);
		String formattedString = String.format("R$%8.2f X %d = R$%8.2f", preco, 1, preco); 
		values.put(DbHelper.DESCRICAO,formattedString );
		

		long insertId = database.insert(DbHelper.TABLE_APERITIVO, null, values);
		Cursor cursor = database.query(DbHelper.TABLE_APERITIVO, allColumns,
				DbHelper.ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		return cursorToAperitivo(cursor);
	}

	public void EliminaAperitivo(int idAperitivo) {
		// database.delete(DB.TABLE_NAME, "id=?", new String []
		// {Integer.toString(idContacto)});
		database.delete(DbHelper.TABLE_APERITIVO, DbHelper.ID + " = " + idAperitivo, null);
	}

	private Aperitivo cursorToAperitivo(Cursor cursor) {
		Aperitivo aperitivo = new Aperitivo(cursor.getLong(0), cursor.getString(1),
				cursor.getDouble(2), cursor.getLong(3));
		return aperitivo;
	}

	public Cursor getAperitivos() {
		Cursor cursor = database.rawQuery(
				"select _id, nome,preco,bar,quantidade,descricao from aperitivo", null);
		return cursor;
	}
	
	public Cursor getAperitivosFromBar(long idBar){
		Cursor cursor = database.rawQuery(
				"select  _id, nome,preco,bar,quantidade,descricao from aperitivo where bar="+idBar, null);
		return cursor;
	}

	public Aperitivo getAperitivo(int idAperitivo) {
		Cursor cursor = database.query(DbHelper.TABLE_APERITIVO, allColumns,
				DbHelper.ID + " = " + idAperitivo, null, null, null, null);
		cursor.moveToFirst();
		return cursorToAperitivo(cursor);
	}

}
