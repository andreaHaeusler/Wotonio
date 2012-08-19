package com.rocketeercoders.wotonio;

import android.content.Context;

public class Drink {

	private int waterCounter = 0;
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

		if (waterCounter == 0) {
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = context.getString(R.string.first_click);
		} else if (waterCounter == 1) {
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = String.format(context.getString(R.string.you_have_had),
					waterCounter);
		}else if(waterCounter == 2) {
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = context.getString(R.string.third_glass_of_water); 
		} else if (waterCounter == 3){
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = context.getString(R.string.fouth_glass_of_water);
		}else if(waterCounter == 7) {
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = context.getString(R.string.super_glass);
		}else if(waterCounter == 9) {
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = context.getString(R.string.over_ten_glasses_of_water);
		} else {
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = String.format(context.getString(R.string.you_have_had),
					waterCounter);
		}
		db.closeDatabase();
	}

	public String getToastText() {
		return toastText;

	}
}




