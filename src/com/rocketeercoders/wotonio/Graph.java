package com.rocketeercoders.wotonio;

import android.os.Bundle;
import android.app.Activity;

public class Graph extends Activity {

	GraphSurface surface;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surface = new GraphSurface(this);
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
