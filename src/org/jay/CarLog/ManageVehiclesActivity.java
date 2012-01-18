package org.jay.CarLog;

import org.jay.CarLog.ContentProvider.CarListContentProvider;
import org.jay.CarLog.Dao.CarListDao;
import org.jay.CarLog.helper.DaoHelper;
import org.jay.CarLog.model.Vehicle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;


public class ManageVehiclesActivity extends ListActivity
{
	private EditText vehicleName;
	private CarListDao carListDao;
	private final static String TAG = "ManageVehiclesActivity"; 
	private SimpleCursorAdapter adapter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.manage_vehicles);
         
         Button next = (Button)findViewById(R.id.button1);
         vehicleName = (EditText)findViewById(R.id.editText1);
         
         carListDao = new CarListDao(this);
         carListDao.open();
         
         this.fillData();

         next.setOnClickListener(new View.OnClickListener() 
         {
             public void onClick(View view) 
             {
            	 //add the vehicle
            	 //fill data again.
            	 addNewVehicle(vehicleName.getText().toString(), "2000");
            	 fillData();
             }
         });

    }
    
    public void fillData()
    {
    	String[] from = new String[]{CarListDao.CAR_NAME};
        int[] to = new int[] { R.id.name_entry };

        Cursor c = carListDao.getAll();
        if(c == null)
        	Log.v(TAG, "c is null");
        
        Log.v(TAG, "Number of row  is  = " +c.getCount());
        Log.v(TAG, "2");
        
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		adapter = new SimpleCursorAdapter(this, R.layout.manage_vehicles_entry, c, from, to, 0);
		Log.v(TAG, "5");
		setListAdapter(adapter);
    }
    
    public void addNewVehicle(String model, String year)
    {
    	Vehicle vehicle = new Vehicle();
        //vehicle.setId(0);
        vehicle.setYear(year);
        vehicle.setModel(model);
        
        carListDao.addCar(vehicle);
        
        fillData();
    }
    
 }
