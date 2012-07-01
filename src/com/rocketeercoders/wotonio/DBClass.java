package com.rocketeercoders.wotonio;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBClass {
	private static final String DATABASE_NAME = "KeepingTrackOfWaterDrunk";
	public static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "waterTable";
	private static final String KEY_COUNT = "count";
	private static final String KEY_TIMESTAMP = "timestamp";

	private DbHelper ourHelper;
	private Context buttonHasBeenPushed;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_COUNT
					+ " INTEGER, " + KEY_TIMESTAMP + " INTEGER)");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);

		}

	}

	public DBClass(Context c) {
		buttonHasBeenPushed = c;
	}

	public DBClass openDatabse() {
		ourHelper = new DbHelper(buttonHasBeenPushed);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void closeDatabase() {
		ourHelper.close();
	}

	public int getCount() {
		int dbValue = 0;
		String[] columns = new String[] { KEY_COUNT, KEY_TIMESTAMP };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			dbValue = c.getInt(0);
		}

		return dbValue;
	}
	

	public void addAGlassOfWater(int waterCounter) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_COUNT, waterCounter);
		cv.put(KEY_TIMESTAMP, System.currentTimeMillis()/1000L);
		ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public void clearDBStructure() {
		ourHelper.onUpgrade(ourDatabase, 0, 0);

	}

}
