package com.rocketeercoders.wotonio;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalTime;
import android.content.Context;

public class Drink {

	
	private int waterCounter = 0;
	
	/*the idea is to keep track of the 
	 * amount of water being drunk
	 * on todayDate so that we may 
	 * start at the the right toastText
	 */
	private int todaysWaterCount = 0;
	
	private String toastText = "";
	private DBInterface db;
	private Context context;

	public Drink(DBInterface db, Context context) {
		this.db = db;
		this.context = context;
	}

	public void updateDatabase() {
		db.openDatabase();
		waterCounter = db.getCount();
		waterCounter++;
		db.addAGlassOfWater(waterCounter);
		db.closeDatabase();
		
	}
	
	public String updateToastText(){
		/*
		 * Testing Joda-time method...
		 */
		
		if (todaysWaterCount == 1) {
			toastText = context.getString(R.string.first_click);
		} else if (todaysWaterCount == 2) {
			toastText = String.format(context.getString(R.string.you_have_had),
					waterCounter);
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
		return toastText;
	}

	public String getToastText() {
		return toastText;

	}
	
	public void notEndOfDay(){
		
		DateTime now = new DateTime();
		DateTime startOfToday = now.toDateMidnight().toInterval().getStart();
		DateTime endOfToday = now.toDateMidnight().toInterval().getEnd();
		Interval today = new Interval(startOfToday, endOfToday);
		
		if(today.contains(now)){
			todaysWaterCount++;
		}
		else{
			todaysWaterCount = 0;
			today = new Interval(startOfToday, endOfToday);
		}
		
	}
}
