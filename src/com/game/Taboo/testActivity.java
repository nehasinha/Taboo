package com.game.Taboo;




import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
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
    	
    	//start the countdown timer
    	countDownTimer counter = new countDownTimer(5000,1000);
        counter.start();
    	
    }
   
    
    public void buttonClickHandler(View view) {
    	if(!timeUpFlag) {	
    		switch(view.getId()) {
    		case R.id.correctbutton:
    			System.out.println("correct button");
    			break;
    		case R.id.incorrectbutton:	
    			System.out.println("incorrect button");
    			break;
    		}
    	}
    }
    
  
  //inserting data into the table
    public void initialDatabaseSetup(DatabaseHelper dbhelper) {
    	dbhelper.insertData();
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
