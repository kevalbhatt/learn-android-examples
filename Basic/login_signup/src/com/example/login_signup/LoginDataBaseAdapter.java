package com.example.login_signup;

import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class LoginDataBaseAdapter {

	static final String DATABASE_NAME = "login.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 4;

	static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS "+"LOGIN"+
			"( " +"ID integer primary key autoincrement,USERNAME  VARCHAR,PASSWORD  VARCHAR,REPASSWORD VARCHAR,SECURITYHINT VARCHAR) ";

	public  SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;

	public  LoginDataBaseAdapter(Context _context) 
	{
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

	}
	public  LoginDataBaseAdapter open() throws SQLException 
	{
		db = dbHelper.getWritableDatabase();
		return this;
	}
	public void close() 
	{
		db.close();
	}

	public  SQLiteDatabase getDatabaseInstance()
	{
		return db;
	}

	public void insertEntry(String username,String password,String repassword,String securityhint)
	{
		ContentValues newValues = new ContentValues();
		newValues.put("USERNAME", username);
		newValues.put("PASSWORD", password);
		newValues.put("REPASSWORD",repassword);
		newValues.put("SECURITYHINT",securityhint);

		db.insert("LOGIN", null, newValues);
	}

/*	public int deleteEntry(String password)
	{
		String where="PASSWORD=?";
		int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{password}) ;
		return numberOFEntriesDeleted;
	}*/	

	public Boolean getSinlgeEntry(String username,String password)
	{
		Cursor cursor=db.query("LOGIN", null, "USERNAME=? and PASSWORD=?", new String[]{username,password}, null, null, null);
		if(cursor.getCount()<1) // UserName Not Exist
		{
			cursor.close();
			return false;
		}else{
			cursor.moveToFirst();
			cursor.close();
			return true;
			
		}
		
		//String repassword= cursor.getString(cursor.getColumnIndex("REPASSWORD"));
		
						
	}

	public String getAllTags(String a) {


		Cursor c = db.rawQuery("SELECT * FROM " + "LOGIN" + " where SECURITYHINT = '" +a + "'" , null);
		String str = null;
		if (c.moveToFirst()) {
			do {
				str = c.getString(c.getColumnIndex("PASSWORD"));
			} while (c.moveToNext());
		}
		return str;
	}


	public void  updateEntry(String username,String password,String repassword)
	{
		ContentValues updatedValues = new ContentValues();
		updatedValues.put("USERNAME", username);
		updatedValues.put("PASSWORD", password);
		updatedValues.put("REPASSWORD",repassword);
		updatedValues.put("SECURITYHINT",repassword);

		String where="USERNAME = ?";
		db.update("LOGIN",updatedValues, where, new String[]{password});			   
	}	



	public HashMap<String, String> getAnimalInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		String selectQuery = "SELECT * FROM LOGIN where SECURITYHINT='"+id+"'";
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				wordList.put("PASSWORD", cursor.getString(1));
			} while (cursor.moveToNext());
		}				    
		return wordList;
	}	
}
