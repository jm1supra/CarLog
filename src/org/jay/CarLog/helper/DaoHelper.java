package org.jay.CarLog.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DaoHelper extends SQLiteOpenHelper 
{
	private static final String DATABASE_NAME = "CARLOG";
	private static final int DATABASE_VERSION = 2;
	
	private static final String CAR_LIST_TABLE = "car_list";
	
	//field list
	private static final String CAR_NAME = "car_name"; 
	private static final String CAR_YEAR = "car_year";
	
	private static final String CAR_LIST_TABLE_CREATE =
             "CREATE TABLE " + CAR_LIST_TABLE + " (" +
            		 CAR_NAME + " TEXT, " +
            		 CAR_YEAR + " TEXT);";

	
	public DaoHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(CAR_LIST_TABLE_CREATE);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
