package org.jay.CarLog.Dao;

import java.util.ArrayList;

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
		
		values.put(CAR_YEAR, vehicle.getYear());
		values.put(CAR_NAME, vehicle.getModel());
		
		database.insert(TABLE_NAME, null, values);
	}
	
	public Cursor getCar(long id)
	{
		return database.query(TABLE_NAME, new String[]{"car_id _id", CAR_ID, CAR_YEAR, CAR_NAME}, null, null, null, null, null);
	}
	
	public void editCar(Vehicle vehicle)
	{
		ContentValues updateCountry = new ContentValues();
		
		updateCountry.put(CAR_YEAR, vehicle.getYear());
		updateCountry.put(CAR_NAME, vehicle.getModel());
		   
		database.update(TABLE_NAME, updateCountry, CarListDao.CAR_NAME+"=?", new String[]{vehicle.getModel()});
	}
	
	public Cursor getAll()
	{
		return database.query(TABLE_NAME, new String[]{"car_id _id", CAR_ID, CAR_YEAR, CAR_NAME}, null, null, null, null, null);
	}
	
	public void remove(String name)
	{
		database.delete(TABLE_NAME, CAR_NAME + "=?", new String[]{name});
	}
	
	public void remove(long id)
	{
		database.delete(TABLE_NAME, CAR_ID + "=?", new String[]{id+""});
	}
	
	public ArrayList<Vehicle> getAllList()
	{
		ArrayList<Vehicle> mArrayList = new ArrayList<Vehicle>();
		Cursor mCursor=this.getAll();
		
		for(mCursor.moveToFirst(); mCursor.moveToNext(); mCursor.isAfterLast()) 
		{
		    // The Cursor is now set to the right position
		    mArrayList.add(new Vehicle(mCursor.getInt(0), mCursor.getString(1), mCursor.getString(2)));
		}

		return mArrayList;
	}

}
