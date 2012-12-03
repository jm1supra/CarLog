package org.jay.CarLog;

import org.jay.CarLog.Dao.CarListDao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.AdapterView.AdapterContextMenuInfo;


public class CarLogActivity extends Activity 
{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button next = (Button)findViewById(R.id.button1);
        next.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View view) 
            {
                Intent myIntent = new Intent(view.getContext(), ManageVehiclesActivity.class);
                startActivityForResult(myIntent, 10);
            }
        });
        
        Button next2 = (Button)findViewById(R.id.button2);
        next2.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View view) 
            {
                Intent myIntent = new Intent(view.getContext(), ManageLogActivity.class);
                startActivityForResult(myIntent, 10);
            }
        });
        
    }
}