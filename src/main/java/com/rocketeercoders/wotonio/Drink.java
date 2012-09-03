package com.rocketeercoders.wotonio;

import java.sql.Date;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import android.content.Context;

public class Drink {

	private DBInterface db;
	private Context context;

	public Drink(DBInterface db, Context context) {
		this.db = db;
		this.context = context;
	}

	public void updateDatabase() {
		db.openDatabase();
		int waterCounter = db.getCount();
		waterCounter++;
		db.addAGlassOfWater(waterCounter);
		db.closeDatabase();
	}
	
	public String getToastText(){
		/*
		 * Testing Joda-time method...
		 */
		String toastText = "";
		db.openDatabase();
		int todaysWaterCount = db.getDrunkOnThisCalendarDay();
		
		if (todaysWaterCount == 1) {
			toastText = context.getString(R.string.first_click);
		} else if (todaysWaterCount == 2) {
			toastText = String.format(context.getString(R.string.you_have_had),
					todaysWaterCount);
		} else if (todaysWaterCount == 3) {
			toastText = context.getString(R.string.third_glass_of_water);
		} else if (todaysWaterCount == 4) {
			toastText = context.getString(R.string.fouth_glass_of_water);
		} else if (todaysWaterCount == 8) {
			toastText = context.getString(R.string.super_glass);
		} else if (todaysWaterCount == 9) {
			toastText = context.getString(R.string.over_ten_glasses_of_water);
		} else {
			toastText = String.format(context.getString(R.string.you_have_had),
					todaysWaterCount);
		}
		db.closeDatabase();
		return toastText;
	}
	
}
