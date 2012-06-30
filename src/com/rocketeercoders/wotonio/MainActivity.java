package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button b, bResetDB;
	TextView tvInfo;
	int waterCounter = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvInfo = (TextView) findViewById(R.id.tvMessages);
		b = (Button) findViewById(R.id.bLetsGetDrinking);
		bResetDB = (Button) findViewById(R.id.bResetDB);
		b.setOnClickListener(this);
		bResetDB.setOnClickListener(this);
		loadFromDB();
		if (waterCounter > 0)
			updateMessage();
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
			waterCounter++;
			saveInDB();
			break;
		case R.id.bResetDB:
			waterCounter = 0;
			DBClass db = new DBClass(MainActivity.this);
			db.openDatabse();
			db.clearDBStructure();
			db.closeDatabase();
			break;
		}
		updateMessage();
	}

	private void updateMessage() {
		if (waterCounter == 0) {
			tvInfo.setText(getString(R.string.not_yet_clicked));
		} else {
			tvInfo.setText(String.format(getString(R.string.you_have_had),
					waterCounter));
		}
	}

	private void saveInDB() {
		DBClass db = new DBClass(MainActivity.this);
		db.openDatabse();
		db.addAGlassOfWater(waterCounter);
		db.closeDatabase();
	}

	private void loadFromDB() {
		DBClass db = new DBClass(MainActivity.this);
		db.openDatabse();
		waterCounter = db.getCount();
		db.closeDatabase();
	}
}
