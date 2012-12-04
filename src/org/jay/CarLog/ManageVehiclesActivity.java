package org.jay.CarLog;

import java.util.Calendar; 

import org.jay.CarLog.Adapter.MySimpleCursorAdapter;
import org.jay.CarLog.Dao.CarListDao;
import org.jay.CarLog.model.Vehicle;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ManageVehiclesActivity extends ListActivity
{
	private CarListDao carListDao;
	private final static String TAG = "ManageVehiclesActivity"; 
	//private MySimpleCursorAdapter adapter;
	private SimpleCursorAdapter adapter;
	private TextView modelName;
	private Spinner yearList;
	private Dialog dialog;
	private ListView vehicleListView;
	private Cursor c;
	
	@Override 
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) 
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mange_select_vehicles, menu);
	 }
	 
	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		System.out.println("id deleted is " + info.id);
		
		switch (item.getItemId()) 
		{
			case R.id.menu_delete:
				c.moveToPosition(info.position);
				//carListDao.remove(c.getString(c.getColumnIndex(CarListDao.CAR_NAME)));
				carListDao.remove(info.id);
				fillData();
				return true;
			case R.id.menu_edit:
				//dialog.setTitle("Edit Information");
				 
				//modelName = (TextView)dialog.findViewById(R.id.model);
				//yearList = (Spinner)dialog.findViewById(R.id.year_list);
				return false;
		}
		 return false;
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {	
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.manage_vehicles); //layout
          
         Button next = (Button)findViewById(R.id.btn_launchVehicleAddDialog);
         
         carListDao = new CarListDao(this);
         carListDao.open();
         
         this.fillData();
         
         //create dialog to input the car information. start
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
         
         //create dialog to input the car information. end
         
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
         
        vehicleListView = this.getListView();
        
        registerForContextMenu(vehicleListView);
        
        /**
        // Then you can create a listener like so: 
        vehicleListView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener()
        { 
        	public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) 
        	{ 
        		onLongListItemClick(v,pos,id); 
        		return true; 
        	} 
	     }); 
         */
        
        /**
        
        vehicleListView.setClickable(true);
        vehicleListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 		
        vehicleListView.setOnLongClickListener(new OnLongClickListener() 
        { 
            public boolean onLongClick(View v) 
            {
                // TODO Auto-generated method stub
            	Log.v(TAG, "On Touch event fired");
 				System.out.println("On item click event fired");
 				
                return true;
            }
        });
        
 		vehicleListView.setOnTouchListener(new OnTouchListener() 
 		{
 			public boolean onTouch(View v, MotionEvent event) 
 			{
 				System.out.println("Long Click");
 				return false;
 			}
 	    });
         */
 		/**
 		vehicleListView.setMultiChoiceModeListener(new MultiChoiceModeListener() 
 		{
 		    public void onItemCheckedStateChanged(ActionMode mode, int position,long id, boolean checked) 
 		    {

 		    }

 		    public boolean onActionItemClicked(ActionMode mode, MenuItem item) 
 		    {
 		        // Respond to clicks on the actions in the CAB
 		        switch (item.getItemId()) 
 		        {
 		            case R.id.menu_delete:
 		                //deleteSelectedItems();
 		                mode.finish(); // Action picked, so close the CAB
 		                return true;
 		            default:
 		                return false;
 		        }
 		    }

 		    public boolean onCreateActionMode(ActionMode mode, Menu menu) 
 		    {
 		        // Inflate the menu for the CAB
 		        MenuInflater inflater = mode.getMenuInflater();
 		        inflater.inflate(R.menu.action_bar, menu);
 		        return true;
 		    }

 		    public void onDestroyActionMode(ActionMode mode) {
 		        // Here you can make any necessary updates to the activity when
 		        // the CAB is removed. By default, selected items are deselected/unchecked.
 		    }

 		    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
 		        // Here you can perform updates to the CAB due to
 		        // an invalidate() request
 		        return false;
 		    }
 		});
 		*/
 		
 		/**
 		vehicleListView.setOnItemSelectedListener(new OnItemSelectedListener()
 		{

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{
			
				Toast.makeText(getBaseContext(), "onItemSelected" + arg2 + " " + arg3, Toast.LENGTH_LONG).show();
			}

			public void onNothingSelected(AdapterView<?> arg0) 
			{
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "onNothingSelected", Toast.LENGTH_LONG).show();
			}
 			
 		});

 		vehicleListView.setOnItemClickListener(new OnItemClickListener()
 		{
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) 
			{
				Toast.makeText(getBaseContext(), "hello " + position + " " + arg3, Toast.LENGTH_LONG).show();
			}
 			
 		});
 		*/

    }
    
    protected void onLongListItemClick(View v, int pos, long id) 
    {
		// TODO Auto-generated method stub
		Log.i(TAG, "asdf "+ pos + " " + id);
	}

	public void fillData()
    {
    	String[] from = new String[]{CarListDao.CAR_ID, CarListDao.CAR_NAME};
        int[] to = new int[] { R.id.vehicle_entry_name, R.id.vehicle_entry_name};

        c = carListDao.getAll();
        
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		adapter = new SimpleCursorAdapter(this, R.layout.manage_vehicles_entry, c, from, to, 0);
		
		
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
