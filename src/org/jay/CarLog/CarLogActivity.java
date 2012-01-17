package org.jay.CarLog;

import org.jay.CarLog.ContentProvider.CarListContentProvider;
import org.jay.CarLog.helper.DaoHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        
    }
}