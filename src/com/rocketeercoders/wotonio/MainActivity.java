package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Button b, bResetDB, bConsumed;
	TextView tvInfo;
	int waterCounter = 0;
	int toastTextDuration = Toast.LENGTH_SHORT;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvInfo = (TextView) findViewById(R.id.tvMessages);
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

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bLetsGetDrinking:
			Drink water = new Drink(new DBClass(this), this);
			water.updateDatabase();
			Toast toast = Toast.makeText(getApplicationContext(), water.getToastText(),toastTextDuration);
			toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
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
		DBClass db = new DBClass(MainActivity.this);
		db.openDatabase();
		db.clearDBStructure();
		db.closeDatabase();
	}
	
	private void saveInDB(){
		DBClass db = new DBClass(MainActivity.this);
		db.openDatabase();
		db.closeDatabase();
	}
	
	private void loadFromDB(){
		DBClass db = new DBClass(MainActivity.this);
		db.openDatabase();
		db.closeDatabase();
	}

}
