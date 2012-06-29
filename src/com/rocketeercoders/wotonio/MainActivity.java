package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button b;
	TextView tvInfo;
	int waterCounter = 0;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvInfo = (TextView) findViewById(R.id.tvMessages);
		b = (Button) findViewById(R.id.bLetsGetDrinking);
		b.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) 
	{
		switch (arg0.getId())
		{
			case R.id.bLetsGetDrinking:
				loadFromDB();
				saveInDB();
				tvInfo.setText("Another glass of water!? WooHoo!! That makes "
						+ waterCounter + " glasses so far.");
				
			break;
		}
		
	}
	
	private void loadFromDB()
	{
		DBClass counter = new DBClass(MainActivity.this);
		counter.openDatabse();
		waterCounter = counter.getCount();
		
		if(waterCounter == 0)
		{
			tvInfo.setText("Well Done! Your first glass of water. The first of many I'm sure");
		}
		
		counter.closeDatabase();
	}

	private void saveInDB()
	{
		DBClass counter = new DBClass(MainActivity.this);
		counter.openDatabse();
		counter.addAClassOfWater(waterCounter);
		counter.closeDatabase();
	}
	
	
	
	

}
