package org.jay.CarLog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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
        
        Button next3 = (Button)findViewById(R.id.button3);
        next3.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View view) 
            {
                Intent myIntent = new Intent(view.getContext(), ListTestActivity.class);
                startActivityForResult(myIntent, 10);
            }
        });
        
    }
}