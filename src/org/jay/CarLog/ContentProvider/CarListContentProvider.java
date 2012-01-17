package org.jay.CarLog.ContentProvider;

import org.jay.CarLog.helper.DaoHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class CarListContentProvider extends ContentProvider 
{
	// Used for the UriMacher
	private static final int TODOS = 10;
	private static final int TODO_ID = 20;
	
	private static final String AUTHORITY = "org.jay.CarLog.ContentProvider";
	private static final String BASE_PATH = "CarLog";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);
	
	private DaoHelper database;
	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) 
	{
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		
		long id = sqlDB.insert(DaoHelper.CAR_LIST_TABLE, null, values);
			
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
		
	}

	@Override
	public boolean onCreate() 
	{
		database = new DaoHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) 
	{
		// Uisng SQLiteQueryBuilder instead of query() method
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		// Check if the caller has requested a column which does not exists
		//checkColumns(projection);

		// Set the table
		queryBuilder.setTables(DaoHelper.CAR_LIST_TABLE);

		int uriType = sURIMatcher.match(uri);
		switch (uriType) 
		{
			case TODOS:
				break;
			case TODO_ID:
				// Adding the ID to the original query
				//queryBuilder.appendWhere(TodoTable.COLUMN_ID + "="
				//			+ uri.getLastPathSegment());
				break;
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		SQLiteDatabase db = database.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,selectionArgs, null, null, sortOrder);
		// Make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
