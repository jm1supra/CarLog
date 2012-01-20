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
         
         //create dialog to input the car information.
         dialog = new Dialog(this);
         dialog.setTitle("Add Information");
         dialog.setContentView(R.layout.vehicle_input);
         
         modelName = (TextView)dialog.findViewById(R.id.name_entry);
         yearList = (Spinner)dialog.findViewById(R.id.year_list);
         
         //load data for the years.
         ArrayList<String> list = new ArrayList<String>();
         
         int i = 1950;
         int year = Calendar.getInstance().get(Calendar.YEAR);
         
         while(i < year)
         {
        	 list.add(i+"");
        	 i++;
         }
         
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
 				R.layout.vehicle_input, R.id.year_list, list);
         
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         yearList.setAdapter(adapter);

         Button submitButton = (Button)dialog.findViewById(R.id.submit);
         Button cancelButton = (Button)dialog.findViewById(R.id.cancel);
         
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
         
         cancelButton.setOnClickListener(new View.OnClickListener() 
         {
			public void onClick(View v) 
			{
				modelName.setText("");
				dialog.dismiss();
			}
		});
         
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
