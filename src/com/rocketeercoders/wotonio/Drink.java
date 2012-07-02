package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Drink extends Activity implements OnClickListener{

	
	TextView tvWater;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink);
        
        tvWater = (TextView) findViewById(R.id.tvDrinks);
        tvWater.setText(R.string.pick_drink);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drink, menu);
        return true;
    }

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.bWater:
			int waterCounter = 0;
			DBClass dbW = new DBClass(null);
			dbW.openDatabse();
			waterCounter = dbW.getCount();
			
			
			if (waterCounter == 0) {
				waterCounter++;
				dbW.addAGlassOfWater(waterCounter);
				tvWater.setText(getString(R.string.first_click));
			} else if (waterCounter == 1) {
				tvWater.setText(getString(R.string.pick_drink));
				waterCounter++;
				dbW.addAGlassOfWater(waterCounter);
				tvWater.setText(String.format(getString(R.string.you_have_had), waterCounter));
			} else {
				tvWater.setText(getString(R.string.pick_drink));
				waterCounter++;
				dbW.addAGlassOfWater(waterCounter);
				tvWater.setText(String.format(getString(R.string.you_have_had), waterCounter));
			}
			dbW.closeDatabase();
			break;
		}
	}

    
}

