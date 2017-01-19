package com.manoj.fitnessdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "myapp", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table User(User_id integer primary key autoincrement,pname text,ppwd text,pmail text,pphno text);");

		db.execSQL("create table Stepscounter(Steps_id integer primary key autoincrement,numberofsteps text, created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " +db);
		onCreate(db);
	}

}
