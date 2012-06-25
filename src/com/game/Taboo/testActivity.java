package com.game.Taboo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;



import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class testActivity extends Activity {
	DatabaseHelper dbhelper = new DatabaseHelper(this);
	boolean timeUpFlag = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.test);
    	
    	//setup the database
    	initialDatabaseSetup(dbhelper);
    
    	//display the retrieved data
    	displayRetrievedData(); 
    	
    	try {Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	
    	displayRetrievedData(); 
    	
    	
    	//start the countdown timer
    	countDownTimer counter = new countDownTimer(5000,1000);
        counter.start();
       
    	
    }
   
    
    public void buttonClickHandler(View view) {
    	if(!timeUpFlag) {	
    		switch(view.getId()) {
    		case R.id.correctbutton:
    			System.out.println("correct button");
    			//write code to get team id and increment score	
    			break;
    		case R.id.incorrectbutton:	
    			System.out.println("incorrect button");
    			//write code to get team id and increment score
    			break;
    		}
    	}
    }
    
  
  //inserting data into the table
    public void initialDatabaseSetup(DatabaseHelper dbhelper) {
    	String line;
    	BufferedReader reader;
    	InputStream csvStream = null;
    	String[] inputString;
    	
    	AssetManager aMgr = this.getAssets();
    	
		try {
			csvStream = aMgr.open("cards.csv");
			
			reader = new BufferedReader(new InputStreamReader(csvStream));
			
			while ((line = reader.readLine()) != null) {   
				line = line.replaceAll("\"", "");
				inputString = line.split(",");
				dbhelper.insertData(inputString);
			}
		} catch (FileNotFoundException e) {
			      e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
  //display retrieved data
    public void displayRetrievedData(){
    	String temp;
    	
    	Cursor cur = dbhelper.retrieveData();
    	if(cur != null) {
    		cur.moveToFirst();
    	}
	
    	for (int i = 0; i < 6; i++) {
            int resId = getResources().getIdentifier("word"+i, "id", getPackageName());
            TextView tv = (TextView)findViewById(resId);
            temp = cur.getString(i+1);
            tv.setText(temp);
    	}
 	
  	}
    
   
    public class countDownTimer extends CountDownTimer {
    	
		TextView countdowntimer = (TextView) findViewById(R.id.timer);
    	public countDownTimer(long millisInFuture, long countDownInterval) {
    		super(millisInFuture, countDownInterval);
    	}
    	
        @Override
        public void onTick(long millisUntilFinished) {
            countdowntimer.setText("TIME REMAINING : " + millisUntilFinished / 1000);
        }
  
        @Override
        public void onFinish() {
        	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        	v.vibrate(300);
            countdowntimer.setText("TIME UP!!");
            timeUpFlag = true;
        }
    }
	
	

    
    
}
