package com.game.Taboo;


import android.os.Bundle;
import android.app.ListActivity; 
import android.content.Intent; 
import android.view.View;
import android.widget.ArrayAdapter; 
import android.widget.ListView;

public class TabooActivity extends ListActivity {
	String options[] = {"NewGame", "Resume Game", "Stop Game" };
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
   //     setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
    	 setListAdapter(new ArrayAdapter<String>(this, R.layout.main, android.R.id.text1, options));
     	
    }
    
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
    	super.onListItemClick(list, view, position, id); 
    	String testName = options[position];
    	try {
    			Class clazz = Class.forName("com.game.Taboo." + testName);
    			Intent intent = new Intent(this, clazz);
    			startActivity(intent);
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace(); 
    	}
    } 
 }
