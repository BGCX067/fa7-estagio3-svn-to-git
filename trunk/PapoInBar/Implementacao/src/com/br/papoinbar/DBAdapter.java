package com.br.papoinbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.br.papoinbar.modelo.Bar;
import com.br.papoinbar.modelo.TipoDeBar;

public class DBAdapter {
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private String[] allColumns = { DbHelper.ID, DbHelper.NOME,
			DbHelper.ENDERECO, DbHelper.TIPO, DbHelper.DESCRICAO };

	public DBAdapter(Context context) {
		dbHelper = new DbHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Bar createBar(String nome, String endereco, String tipo,
			String descricao) {
		ContentValues values = new ContentValues();
		values.put(DbHelper.NOME, nome);
		values.put(DbHelper.ENDERECO, endereco);
		values.put(DbHelper.TIPO, tipo);
		values.put(DbHelper.DESCRICAO, descricao);

		long insertId = database.insert(DbHelper.TABLE_NAME, null, values);
		Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns,
				DbHelper.ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		return cursorToBar(cursor);
	}

	public void EliminaBar(long idBar) {
		// database.delete(DB.TABLE_NAME, "id=?", new String []
		// {Integer.toString(idContacto)});
		database.delete(DbHelper.TABLE_NAME, DbHelper.ID + " = " + idBar, null);
	}

	private Bar cursorToBar(Cursor cursor) {
		Bar bar = new Bar(cursor.getLong(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3),
				cursor.getString(4));
		return bar;
	}

	public Cursor getBares() {
		Cursor cursor = database.rawQuery(
				"select _id, nome,endereco,tipo,descricao from bar", null);
		return cursor;
	}

	public Bar getBar(long idBar) {
		Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns,
				DbHelper.ID + " = " + idBar, null, null, null, null);
		cursor.moveToFirst();
		return cursorToBar(cursor);
	}

}
