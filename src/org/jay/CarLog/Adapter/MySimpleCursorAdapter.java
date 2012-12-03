package org.jay.CarLog.Adapter;

import java.util.HashMap;
import java.util.Map;

import org.jay.CarLog.ManageVehiclesActivity;
import org.jay.CarLog.R;
import org.jay.CarLog.Dao.CarListDao;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.PopupMenu;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MySimpleCursorAdapter extends SimpleCursorAdapter
{
	private HashMap<String, Integer> checkedItems = new HashMap<String, Integer>();
	private final static String TAG = "MySimpleCursorAdapter";	
	private TextView tv;
	private Context ct;
	private boolean flag = false;
	
	public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) 
	{
		super(context, layout, c, from, to, flags);
		
		this.ct = context;
	}

	@Override   
	public void bindView(View view, Context context, Cursor cursor) 
	{
		tv = (TextView)view.findViewById(R.id.vehicle_entry_name);
		tv.setText(cursor.getString(cursor.getColumnIndex(CarListDao.CAR_NAME)));
		
	    tv.setOnLongClickListener(new OnLongClickListener() 
	    { 
	        public boolean onLongClick(View v) 
	        {
	        	//display the long
	        	
	        	
	            // TODO Auto-generated method stub
	            return true;
	        }
	    });
	    
	    
	    tv.setOnLongClickListener(new OnLongClickListener()
	    {
			public boolean onLongClick(View v) 
			{
				// TODO Auto-generated method stub
				return false;
			}});
	    
	    
	    /**
	    cb.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	    {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
			{
				if(isChecked)
				{
					checkedItems.put(buttonView.getText().toString(), 1);
				}
				else
				{
					checkedItems.remove(buttonView.getText().toString());
				}
				
				//view.setSelected(true);

				((ManageVehiclesActivity)ct).startActionMode(mActionModeCallback);
				((ManageVehiclesActivity)ct).openOptionsMenu();
			}           
	    });
	}
	
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() 
	{
		// Called when the action mode is created; startActionMode() was called
		public boolean onCreateActionMode(ActionMode mode, Menu menu) 
		{
			if(!flag)
			{
				// Inflate a menu resource providing context menu items
				MenuInflater inflater = mode.getMenuInflater();
				
				// Assumes that you have "contexual.xml" menu resources
				inflater.inflate(R.menu.mange_select_vehicles, menu);
				flag = true;
				return true;
			}
			
			return false;
		}

		// Called each time the action mode is shown. Always called after
		// onCreateActionMode, but
		// may be called multiple times if the mode is invalidated.
		
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) 
		{
			//Toast.makeText(((ManageVehiclesActivity)ct), "onPrepareActionMode", Toast.LENGTH_LONG).show();
			return false; // Return false if nothing is done
		}
		

		// Called when the user selects a contextual menu item
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) 
		{
			switch (item.getItemId()) 
			{
			case R.id.menu_delete:
				String list = "list: ";
				//get all the items selected...
				for(Map.Entry<String, Integer> entry: checkedItems.entrySet())
				{
					list += entry.getKey() + " ";
				}
				
				Toast.makeText(((ManageVehiclesActivity)ct), list, Toast.LENGTH_LONG).show();
				mode.finish(); // Action picked, so close the CAB
				return true;
			default:
				return true;
			}
		}

		public void onDestroyActionMode(ActionMode mode) 
		{
			// TODO Auto-generated method stub
			//Toast.makeText(((ManageVehiclesActivity)ct), "onDestroy", Toast.LENGTH_LONG).show();
			flag = false;
		}*/
	    
	};
	
}
