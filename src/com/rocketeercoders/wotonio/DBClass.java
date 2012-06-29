package com.rocketeercoders.wotonio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBClass
{
	private static final String DATABASE_NAME = "KeepingTrackOfWaterDrunk";
	public static final int DATABASE_VERSION = 0;
	private static final String DATABASE_TABLE = "waterTable";
	private static final String KEY_COUNT = "count";
	
	private DbHelper ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class DbHelper extends SQLiteOpenHelper
	{
		public DbHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_COUNT + " INTEGER");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			
		}
		
	}
	
	public DBClass (Context c){
		ourContext = c;
	}
	
	public DBClass openDatabse(){
		ourHelper =new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void closeDatabase(){
		ourHelper.close();
	}


	public long continueCounting(int count)
	{
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_COUNT, count);
		return ourDatabase.insert(DATABASE_NAME, null, cv);
	}
}
