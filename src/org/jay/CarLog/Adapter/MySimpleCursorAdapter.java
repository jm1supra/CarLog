package org.jay.CarLog.Adapter;

import android.app.ActionBar;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.SimpleCursorAdapter;

public class MySimpleCursorAdapter extends SimpleCursorAdapter
{
	private ActionBar actionbar;
	
	public MySimpleCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
        View v = super.getView(position, convertView, parent);
        v.setOnClickListener(new OnClickListener() 
        {
            public void onClick(View v) 
            {
            	actionbar.show();
            }
         });
         return v;
    }

	public ActionBar getActionbar() {
		return actionbar;
	}

	public void setActionbar(ActionBar actionbar) {
		this.actionbar = actionbar;
	}
}
