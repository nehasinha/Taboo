// ******************* reference for db operations ************************
// ******************* http://codeblow.com/questions/not-able-to-obtain-listview-to-refresh-from-database/


package com.game.Taboo;

import com.game.Taboo.ScoreDatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;


public class ScoreDatabaseHelper extends SQLiteOpenHelper {
	
	static final String dbName = "TabooDB";
	static final String tableName="WordsTable";
	static final String primaryKey="_id";
	static final String col1="MainWord";
	static final String col2="TabooWord1";
	static final String col3="TabooWord2";
	static final String col4="TabooWord3";
	static final String col5="TabooWord4";
	static final String col6="TabooWord5";
	
	public ScoreDatabaseHelper(Context context) {
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
	
	public void insertData() {
		System.out.println("Inside insert");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		db.delete(ScoreDatabaseHelper.tableName, ScoreDatabaseHelper.col1+"=?", null);
		System.out.println("Deleted db if exists");
		
		String[][] wordsList = new String[5][7];
		
		wordsList[0][0] = "OCEAN";
		wordsList[0][1] = "sea";
		wordsList[0][2] = "atlantic";
		wordsList[0][3] = "pacific";
		wordsList[0][4] = "salt water";
		wordsList[0][5] = "beach";
					
		wordsList[1][0] = "OUT";
		wordsList[1][1] = "go";
		wordsList[1][2] = "leave";
		wordsList[1][3] = "exit";
		wordsList[1][4] = "in";
		wordsList[1][5] = "door";
		
		wordsList[2][0] = "LOOK";
		wordsList[2][1] = "see";
		wordsList[2][2] = "eyes";
		wordsList[2][3] = "clothes";
		wordsList[2][4] = "fashion";
		wordsList[2][5] = "watch";
					
		wordsList[3][0] = "MILK";
		wordsList[3][1] = "cow";
		wordsList[3][2] = "dairy";
		wordsList[3][3] = "cereal";
		wordsList[3][4] = "cookies";
		wordsList[3][5] = "drink";
		
		wordsList[4][0] = "AMERICAN";
		wordsList[4][1] = "citizen";
		wordsList[4][2] = "USA";
		wordsList[4][3] = "States";
		wordsList[4][4] = "Canada";
		wordsList[4][5] = "country";

		int i = 0;
		while (i < 5) {
			System.out.println(wordsList[i][0].toString());
			cv.put(ScoreDatabaseHelper.col1, wordsList[i][0]);
			cv.put(ScoreDatabaseHelper.col2, wordsList[i][1]);
			cv.put(ScoreDatabaseHelper.col3, wordsList[i][2]);
			cv.put(ScoreDatabaseHelper.col4, wordsList[i][3]);
			cv.put(ScoreDatabaseHelper.col5, wordsList[i][4]);
			cv.put(ScoreDatabaseHelper.col6, wordsList[i][5]);
			
			System.out.println("FInished adding data to variables");
			db.insert(ScoreDatabaseHelper.tableName, null, cv);
			System.out.println("FInished inserting");
			
			i = i + 1;
		}
	    db.close();
	    System.out.println("About to exit frominsert");
	}

	public Cursor retrieveData(){
		SQLiteDatabase db = this.getReadableDatabase();  
	      
	    Cursor cur = db.query("WordsTable", null,null,null, null, null, null, null); 
	    if(cur == null) {
	    	System.out.println("Null cursor returned");
	    }
	      System.out.println("created cursor");
	      int rowsCount = cur.getCount();
	      System.out.println("No of rows");
	      System.out.println(rowsCount);  
	      cur.moveToFirst();
	   return cur;		
	}
	  
	
	
}

