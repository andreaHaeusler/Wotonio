package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.database.Cursor;
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
		loadFromDB();
		if(waterCounter > 0)
			updateMessage();
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
				waterCounter++;
				saveInDB();
			updateMessage();
				
			break;
		}
		
	}

	private void updateMessage()
	{
		tvInfo.setText("Another glass of water!? WooHoo!! That makes "
				+ waterCounter + " glasses so far.");
	}

	private void saveInDB()
	{
		DBClass db = new DBClass(MainActivity.this);
		db.openDatabse();
		db.addAGlassOfWater(waterCounter);
		db.closeDatabase();
	}

	private void loadFromDB()
	{
		DBClass db = new DBClass(MainActivity.this);
		db.openDatabse();
		waterCounter = db.getCount();
		db.closeDatabase();
	}


}
