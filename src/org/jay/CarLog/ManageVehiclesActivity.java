package org.jay.CarLog;

import java.util.ArrayList;
import java.util.Calendar;

import org.jay.CarLog.ContentProvider.CarListContentProvider;
import org.jay.CarLog.Dao.CarListDao;
import org.jay.CarLog.helper.DaoHelper;
import org.jay.CarLog.model.Vehicle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Spinner;
import android.widget.TextView;


public class ManageVehiclesActivity extends ListActivity
{
	private CarListDao carListDao;
	private final static String TAG = "ManageVehiclesActivity"; 
	private SimpleCursorAdapter adapter;
	private TextView modelName;
	private Spinner yearList;
	private Dialog dialog;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.manage_vehicles);
         
         Button next = (Button)findViewById(R.id.btn_launchVehicleAddDialog);
         
         carListDao = new CarListDao(this);
         carListDao.open();
         
         this.fillData();
         Log.i(TAG, "1");
         //create dialog to input the car information.
         dialog = new Dialog(this);
         dialog.setTitle("Add Information");
         dialog.setContentView(R.layout.vehicle_input);
         Log.i(TAG, "2");
         modelName = (TextView)dialog.findViewById(R.id.model);
         yearList = (Spinner)dialog.findViewById(R.id.year_list);
         Log.i(TAG, "3");
         //load data for the years.
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
         
         int i = 1950;
         int year = Calendar.getInstance().get(Calendar.YEAR);
         
         Log.i(TAG, "4");
         
         while(i < year)
         {
        	 adapter.add(i+"");
        	 i++;
         }
         
         Log.i(TAG, "5");
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         yearList.setAdapter(adapter);
         Log.i(TAG, "6");
         Button submitButton = (Button)dialog.findViewById(R.id.submit);
         Button cancelButton = (Button)dialog.findViewById(R.id.cancel);
         Log.i(TAG, "7");
         submitButton.setOnClickListener(new View.OnClickListener() 
         {
			public void onClick(View v) 
			{
				addNewVehicle(modelName.getText().toString(), yearList.getSelectedItem().toString());
				fillData();
				
				//hide the dialog box
				dialog.dismiss();
			}
		 });
         Log.i(TAG, "8");
         cancelButton.setOnClickListener(new View.OnClickListener() 
         {
			public void onClick(View v) 
			{
				modelName.setText("");
				dialog.dismiss();
			}
		});
         Log.i(TAG, "9");
         next.setOnClickListener(new View.OnClickListener() 
         {
             public void onClick(View view) 
             {
            	 //clear the data selected from previous session
            	 if(modelName.getText() != "")
            		 modelName.setText("");
            	 
            	 //display the dialog box.
            	 dialog.show();
             }
         });
         Log.i(TAG, "10");
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
