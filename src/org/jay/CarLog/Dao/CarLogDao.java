package org.jay.CarLog.Dao;

import java.util.List;

import org.jay.CarLog.helper.DaoHelper;
import org.jay.CarLog.model.LogEntry;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CarLogDao 
{
	private Context context;
	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	
	public static String LOG_TABLE = "car_log";
	
	public CarLogDao(Context context)
	{
		this.context = context;
	}
	
	public CarLogDao open() throws SQLException
	{
		dbHelper = new DaoHelper(this.context);
		database = dbHelper.getWritableDatabase();
		
		return this;
	}
	
	public void add(LogEntry entry)
	{
		
	}
	
	public List<String> getLogs(String name)
	{
		//return database.query(TABLE_NAME, new String[]{"car_id _id", CAR_ID, CAR_YEAR, CAR_NAME}, null, null, null, null, null);
		//database.query(LOG_TABLE, new String[]{}, "", selectionArgs, groupBy, having, orderBy)
		return null;
	}
	
	

}
