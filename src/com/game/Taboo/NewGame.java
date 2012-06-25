package com.game.Taboo;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent; 
import android.view.View;
import android.view.View.OnTouchListener; 
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;

public class NewGame extends ListActivity {
	//StringBuilder builder = new StringBuilder();
	TextView textView, text1;
	String words[] = {"look", "see", "watch", "clothes", "eye","fashion" };

public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setListAdapter(new ArrayAdapter<String>(this, R.layout.new_game, android.R.id.text1, words));
	
	
	
	System.out.println("inside new game");
	System.out.println("Hello Neha SInha");
	System.out.println("empty");
	String testname = "testActivity";
	try {
		Class clazz = Class.forName("com.game.Taboo." + testname);
		Intent intent = new Intent(this, clazz);
		startActivity(intent);
} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
