package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.os.Bundle;

public class Graph extends Activity {

	private GraphSurface surface;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surface = new GraphSurface(this, new WeeklyGraphDataProvider(
				new DBClass(this)));
		setContentView(surface);
	}

	@Override
	protected void onPause() {
		super.onPause();
		surface.pause();

	}

	@Override
	protected void onResume() {
		super.onResume();
		surface.resume();
	}

}
