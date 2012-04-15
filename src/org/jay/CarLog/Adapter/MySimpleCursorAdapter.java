package org.jay.CarLog.Adapter;

import org.jay.CarLog.ManageVehiclesActivity;
import org.jay.CarLog.R;
import org.jay.CarLog.Dao.CarListDao;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MySimpleCursorAdapter extends SimpleCursorAdapter
{
	private final static String TAG = "MySimpleCursorAdapter";	
	private CheckBox cb;
	private Context ct;
	
	public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) 
	{
		super(context, layout, c, from, to, flags);
		
		this.ct = context;
	}

	@Override   
	public void bindView(View view, Context context, Cursor cursor) 
	{
		cb = (CheckBox)view.findViewById(R.id.vehicle_entry);
		cb.setText(cursor.getString(cursor.getColumnIndex(CarListDao.CAR_NAME)));

	    cb.setChecked(false);
	    
	    cb.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	    {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
			{
				if(isChecked)
				{
					//Toast.makeText(ct, "isChecked: "+buttonView.getText().toString(), Toast.LENGTH_LONG).show();
					
					((ManageVehiclesActivity)ct).openOptionsMenu();
				}
			}           
	    });
	    
	}
}
