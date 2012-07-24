package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Drink  {

	int waterCounter = 0;
	String toastText = "";
	private DBClass db;
	private Context context;

	public Drink(DBClass db, Context context){
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
		} else {
			waterCounter = waterCounter + 1;
			db.addAGlassOfWater(waterCounter);
			toastText = String.format(context.getString(R.string.you_have_had),
					waterCounter);
		}
		db.closeDatabase();
	}
	
	public String getToastText(){
		return toastText;
		
	}
	

}
