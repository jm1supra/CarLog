package org.jay.CarLog;

import org.jay.CarLog.Dao.CarListDao;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class ManageLogActivity extends Activity 
{	
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.manage_log);
    	
    	Spinner spinner = (Spinner)findViewById(R.id.car_list_spinner);
    	
    	String[] from = new String[]{CarListDao.CAR_NAME};
    	int[] to = new int[] { android.R.id.text1 };
        
        CarListDao carListDao = new CarListDao(this);
        carListDao.open();

        Cursor c = carListDao.getAll();
        
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, c, from, to);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mAdapter);
        
        spinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) 
			{
				// TODO Auto-generated method stub
				//update the view, using the item selected.
			}

			public void onNothingSelected(AdapterView<?> arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});
        
        
    	//Selected Car or All - drop down...
    	//List View of
    		// Date, Car, Title...

    	
    	//Add button shows up in the menu.
    	//edit & delete button on context menu.
    	
    }
}
