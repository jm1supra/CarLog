package org.jay.CarLog;

import org.jay.CarLog.ContentProvider.CarListContentProvider;
import org.jay.CarLog.Dao.CarListDao;
import org.jay.CarLog.helper.DaoHelper;
import org.jay.CarLog.model.Vehicle;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;


public class ManageVehiclesActivity extends ListActivity
{
	private EditText vehicleName;
	
	private CarListDao carListDao;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.manage_vehicles);
         
         Button next = (Button)findViewById(R.id.button1);
         vehicleName = (EditText)findViewById(R.id.editText1);
         
         Vehicle vehicle = new Vehicle();
         vehicle.setId(0);
         vehicle.setYear("1999");
         vehicle.setModel("Acura Integra");
         carListDao = new CarListDao(this);
         carListDao.open();
         
         int[] to = new int[] { android.R.id.text1 };
         String[] from = new String[]{CarListDao.CAR_NAME};
         
 		// Read all comments
 		Cursor c = carListDao.getAll();

 		
 		// Use the SimpleCursorAdapter to show the
 		// elements in a ListView
 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
 				android.R.layout.simple_list_item_1, c, from, to);
 		setListAdapter(adapter);
         
         next.setOnClickListener(new View.OnClickListener() 
         {
             public void onClick(View view) 
             {
             	
             }

         });
    }
    
 }
