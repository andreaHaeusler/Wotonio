package com.rocketeercoders.wotonio;

import android.app.Activity;
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
	public void onClick(View arg0) {

		switch (arg0.getId()) {

		case R.id.bLetsGetDrinking:
			waterCounter = waterCounter + 1;
			tvInfo.setText(String.format(getString(R.string.you_have_had),
					waterCounter));
			DBClass counter = new DBClass(MainActivity.this);
			counter.openDatabse();
			counter.continueCounting(waterCounter);
			counter.closeDatabase();

			break;
		}

		// if(waterCounter == 0){
		// tvInfo.setText("Well Done! Your first glass of water. The first of many I'm sure");
		// waterCounter = waterCounter + 1;
		// }
		// else{
		// waterCounter = waterCounter + 1;
		// tvInfo.setText("Another?! WooHoo!! That makes " + waterCounter
		// + " glasses of water so far!");
		// }

	}

}
