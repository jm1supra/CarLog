package org.jay.CarLog;

import java.util.Calendar;

import org.jay.CarLog.Adapter.MySimpleCursorAdapter;
import org.jay.CarLog.Dao.CarListDao;
import org.jay.CarLog.model.Vehicle;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class ManageVehiclesActivity extends ListActivity
{
	private CarListDao carListDao;
	private final static String TAG = "ManageVehiclesActivity"; 
	private MySimpleCursorAdapter adapter;
	private TextView modelName;
	private Spinner yearList;
	private Dialog dialog;
	private ActionBar actionBar;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mange_select_vehicles, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
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
         
         modelName = (TextView)dialog.findViewById(R.id.model);
         yearList = (Spinner)dialog.findViewById(R.id.year_list);
         
         //load data for the years.
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
         
         int year = Calendar.getInstance().get(Calendar.YEAR);
         
         while(year > 1950)
         {
        	 adapter.add(year+"");
        	 year--;
         }
         
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         yearList.setAdapter(adapter);
         
         actionBar = this.getActionBar();
         
         if(actionBar == null)
        	 Log.d(TAG, "actionbar is null");
         
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
         
        ListView vehicleListView = this.getListView();
        vehicleListView.setTextFilterEnabled(true);
 		
 		vehicleListView.setOnTouchListener(new OnTouchListener() 
 		{
 			public boolean onTouch(View v, MotionEvent event) 
 			{
 				Log.v(TAG, "On Touch event fired");
 				return false;
 			}
 	    });
    }
    
    public void fillData()
    {
    	String[] from = new String[]{CarListDao.CAR_NAME};
        int[] to = new int[] { R.id.vehicle_entry_checkbox};

        Cursor c = carListDao.getAll();
        
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		adapter = new MySimpleCursorAdapter(this, R.layout.manage_vehicles_entry, c, from, to, 0);
		adapter.setActionbar(actionBar);
		adapter.setActivity(this);
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
