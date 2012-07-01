package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class Drink extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink);
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
			
			break;
		}
	}

    
}

