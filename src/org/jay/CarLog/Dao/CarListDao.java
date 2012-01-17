package org.jay.CarLog.Dao;

import org.jay.CarLog.helper.DaoHelper;
import org.jay.CarLog.model.Vehicle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class CarListDao 
{
	public static final String CAR_NAME = "car_name"; 
	public static final String CAR_YEAR = "car_year";
	public static final String CAR_ID = "car_id";
	
	private static final String TABLE_NAME = "car_list";
	
	private Context context;
	private SQLiteDatabase database;
	private DaoHelper dbHelper;
	
	public CarListDao(Context context)
	{
		this.context = context;
	}
	
	public CarListDao open() throws SQLException
	{
		dbHelper = new DaoHelper(this.context);
		database = dbHelper.getWritableDatabase();
		
		return this;
	}
	
	public void addCar(Vehicle vehicle)
	{
		ContentValues values = new ContentValues();
		
		values.put(CAR_ID, 0);
		values.put(CAR_YEAR, vehicle.getYear());
		values.put(CAR_NAME, vehicle.getModel());
		
		database.insert(TABLE_NAME, null, values);
	}
	
	public Cursor getAll()
	{
		return database.query(TABLE_NAME, new String[]{CAR_ID, CAR_YEAR, CAR_NAME}, null, null, null, null, null);
	}

}