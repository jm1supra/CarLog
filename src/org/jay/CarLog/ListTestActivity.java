package org.jay.CarLog;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListTestActivity extends ListActivity
{
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list);
       
        ListView listView1 = getListView();
       
        String[] items = {
            "AAAAAAAA",
            "BBBBBBBB",
            "CCCCCCCC",
            "DDDDDDDD",
            "EEEEEEEE",
            "FFFFFFFF"
        };
       
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listView1.setAdapter(adapter);
        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
     
        //listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new OnItemClickListener() 
        {
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) 
			{
				String item = ((TextView)view).getText().toString() + "position: " + position + " & id = " + id;
				Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
			}
        });
    }

}
