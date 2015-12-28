package com.example.salute2.contentprovider;

import java.util.Arrays;
import java.util.HashSet;

import com.example.salute2.database.SaluteDatabaseHelper;

import com.example.salute2.database.UsuarioTable;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class UsuarioContentProvider extends ContentProvider {

  // database
  private SaluteDatabaseHelper database;

  // used for the UriMacher
  private static final int USUARIO = 10;
  private static final int USUARIO_ID = 20;

  private static final String AUTHORITY = "com.example.salute2.contentprovider";

  private static final String BASE_PATH = "USUARIO";
  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
      + "/" + BASE_PATH);

  public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
      + "/USUARIO";
  public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
      + "/todo";

  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
  static {
    sURIMatcher.addURI(AUTHORITY, BASE_PATH, USUARIO);
    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", USUARIO_ID);
  }

  @Override
  public boolean onCreate() {
    database = new SaluteDatabaseHelper(getContext());
    return false;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {

    // Uisng SQLiteQueryBuilder instead of query() method
    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

    // check if the caller has requested a column which does not exists
    checkColumns(projection);

    // Set the table
    queryBuilder.setTables(UsuarioTable.TABLE_USUARIO);

    int uriType = sURIMatcher.match(uri);
    switch (uriType) {
    case USUARIO:
      break;
    case USUARIO_ID:
      // adding the ID to the original query
      queryBuilder.appendWhere(UsuarioTable.COLUMN_ID + "="
          + uri.getLastPathSegment());
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    SQLiteDatabase db = database.getWritableDatabase();
    Cursor cursor = queryBuilder.query(db, projection, selection,
        selectionArgs, null, null, sortOrder);
    // make sure that potential listeners are getting notified
    cursor.setNotificationUri(getContext().getContentResolver(), uri);

    return cursor;
  }

  @Override
  public String getType(Uri uri) {
    return null;
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsDeleted = 0;
    long id = 0;
    switch (uriType) {
    case USUARIO:
      id = sqlDB.insert(UsuarioTable.TABLE_USUARIO, null, values);
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return Uri.parse(BASE_PATH + "/" + id);
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsDeleted = 0;
    switch (uriType) {
    case USUARIO:
      rowsDeleted = sqlDB.delete(UsuarioTable.TABLE_USUARIO, selection,
          selectionArgs);
      break;
    case USUARIO_ID:
      String id = uri.getLastPathSegment();
      if (TextUtils.isEmpty(selection)) {
        rowsDeleted = sqlDB.delete(UsuarioTable.TABLE_USUARIO,
        		UsuarioTable.COLUMN_ID + "=" + id, 
            null);
      } else {
        rowsDeleted = sqlDB.delete(UsuarioTable.TABLE_USUARIO,
        		UsuarioTable.COLUMN_ID + "=" + id 
            + " and " + selection,
            selectionArgs);
      }
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsDeleted;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection,
      String[] selectionArgs) {

    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsUpdated = 0;
    switch (uriType) {
    case USUARIO:
      rowsUpdated = sqlDB.update(UsuarioTable.TABLE_USUARIO, 
          values, 
          selection,
          selectionArgs);
      break;
    case USUARIO_ID:
      String id = uri.getLastPathSegment();
      if (TextUtils.isEmpty(selection)) {
        rowsUpdated = sqlDB.update(UsuarioTable.TABLE_USUARIO, 
            values,
            UsuarioTable.COLUMN_ID + "=" + id, 
            null);
      } else {
        rowsUpdated = sqlDB.update(UsuarioTable.TABLE_USUARIO, 
            values,
            UsuarioTable.COLUMN_ID + "=" + id 
            + " and " 
            + selection,
            selectionArgs);
      }
      break;
    default:
      throw new IllegalArgumentException("Unknown URI: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsUpdated;
  }

  private void checkColumns(String[] projection) {
    String[] available = { UsuarioTable.COLUMN_ID,
    		UsuarioTable.COLUMN_NOME, UsuarioTable.COLUMN_EMAIL,
    		UsuarioTable.COLUMN_IDADE,UsuarioTable.COLUMN_PESO,
    		UsuarioTable.COLUMN_SENHA,UsuarioTable.COLUMN_SEXO};
    if (projection != null) {
      HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
      HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
      // check if all columns which are requested are available
      if (!availableColumns.containsAll(requestedColumns)) {
        throw new IllegalArgumentException("Unknown columns in projection");
      }
    }
  }

} 

