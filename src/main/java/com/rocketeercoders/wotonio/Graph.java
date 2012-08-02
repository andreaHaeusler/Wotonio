package com.rocketeercoders.wotonio;

import android.app.Activity;
import android.os.Bundle;

public class Graph extends Activity {

	private GraphView view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new GraphView(this);
		view.init(new WeeklyGraphDataProvider(new DBClass(this)),
				new VerticalAxisScaler());
		setContentView(view);
	}
}
