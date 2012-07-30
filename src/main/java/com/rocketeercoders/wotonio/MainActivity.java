package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button b, bResetDB, bConsumed;
	private int toastTextDuration = Toast.LENGTH_SHORT;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this will get rid of the inevitable banding effect (stripes in background)...
		getWindow().setFormat(PixelFormat.RGBA_8888);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
		setContentView(R.layout.activity_main);

		// this buttons should take user to a new page where they can select the
		// drink that they are going to drink
		b = (Button) findViewById(R.id.bLetsGetDrinking);
		
		
		// the reset button is only for our use, not for the user
		bResetDB = (Button) findViewById(R.id.bResetDB);
		// this button should take user to graph indicating consumtion
		bConsumed = (Button) findViewById(R.id.bConsumed);

		b.setOnClickListener(this);
		bConsumed.setOnClickListener(this);
		bResetDB.setOnClickListener(this);
		// if user wants to check what they have consumed before selecting next
		// drink...
		try {
			loadFromDB();
		} catch (SQLiteException e) {
			e.printStackTrace();
			resetDB();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bLetsGetDrinking:
			Drink water = new Drink(new DBClass(this), this);
			water.updateDatabase();
			Toast toast = Toast.makeText(getApplicationContext(),
					water.getToastText(), toastTextDuration);
			toast.setGravity(Gravity.CENTER_HORIZONTAL
					| Gravity.CENTER_VERTICAL, 0, 0);
			toast.show();
			break;
		case R.id.bConsumed:
			Intent i2 = new Intent(MainActivity.this, Graph.class);
			startActivity(i2);
			break;
		case R.id.bResetDB:
			resetDB();
			break;
		}
	}

	private void resetDB() {
		DBInterface db = new DBClass(MainActivity.this);
		db.openDatabase();
		db.clearDBStructure();
		db.closeDatabase();
	}

	private void loadFromDB() {
		DBInterface db = new DBClass(MainActivity.this);
		db.openDatabase();
		db.closeDatabase();
	}

}
