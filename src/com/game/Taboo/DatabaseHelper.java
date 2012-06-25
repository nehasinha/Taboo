// ******************* reference for db operations ************************
// ******************* http://codeblow.com/questions/not-able-to-obtain-listview-to-refresh-from-database/


package com.game.Taboo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.game.Taboo.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;


public class DatabaseHelper extends SQLiteOpenHelper {
	public static ArrayList<Integer> usedWords = new ArrayList<Integer>();
	static final String dbName = "TabooDB";
	static final String tableName="WordsTable";
	static final String primaryKey="_id";
	static final String col1="MainWord";
	static final String col2="TabooWord1";
	static final String col3="TabooWord2";
	static final String col4="TabooWord3";
	static final String col5="TabooWord4";
	static final String col6="TabooWord5";
	public static final String temp = null;
	
	public DatabaseHelper(Context context) {
		super(context, dbName, null, 33);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("Inside Database helper - create db");
		String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT )", 
				tableName, primaryKey, col1, col2, col3, col4, col5, col6);
    
		db.execSQL(sql);
	}
	
	@Override  
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
	    // TODO Auto-generated method stub  
	    db.execSQL("drop table if exists " + tableName);  
	    this.onCreate(db);  
	}  
	
	public void insertData(String[] inputString) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		db.delete(DatabaseHelper.tableName, DatabaseHelper.col1+"=?", null);
		Cursor cur = db.query("WordsTable", null,null,null, null, null, null, null); 
		//prevent writing more than 125 words
	    if(cur.getCount() < 125) {    	
	    	cv.put(DatabaseHelper.col1, inputString[1]);	
	    	cv.put(DatabaseHelper.col2, inputString[2]);
	    	cv.put(DatabaseHelper.col3, inputString[3]);
	    	cv.put(DatabaseHelper.col4, inputString[4]);
	    	cv.put(DatabaseHelper.col5, inputString[5]);
	    	cv.put(DatabaseHelper.col6, inputString[6]);
	    
	    	db.insert(DatabaseHelper.tableName, null, cv);
	    	
	    	db.close();
	    }			
	}
		
	
	    


	public Cursor retrieveData(){
		int min = 1;
		int max =125;
		int randomNumber;
		Random r;
		SQLiteDatabase db = this.getReadableDatabase(); 
		
		while(true) {
			r = new Random();
			randomNumber = r.nextInt(max - min + 1) + min;
			if(!usedWords.contains(randomNumber)){
				break;
			}	
		}
	
	//	int randomNumber = r.nextInt(max - min + 1) + min;
		System.out.println(randomNumber);
	      
	    Cursor cur = db.query("WordsTable", null, "_id in ("
	            + randomNumber + ")",null,null, null, null, null); 
	    if(cur == null) {
	    	System.out.println("Null cursor returned");
	    }
	    cur.moveToFirst();
	    int temp = Integer.parseInt(cur.getString(0));
	    usedWords.add(temp);  
	    System.out.println(usedWords);
	    return cur;		
	}
	  
	
	
}

