package com.rocketeercoders.wotonio.test;

import android.os.Handler;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.rocketeercoders.wotonio.MainActivity;

public class MainActivityTest extends
		android.test.ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity activity;
	private TextView tv;
	private Button b;
	private String initialText, textFormat;
	private Handler handler;

	public MainActivityTest() {
		super("com.rocketeercoders.wotonio.MainActivity", MainActivity.class);
		handler = new Handler();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		tv = (TextView) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.tvMessages);
		b = (Button) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.bLetsGetDrinking);
		initialText = activity
				.getString(com.rocketeercoders.wotonio.R.string.not_yet_clicked);
		textFormat = activity
				.getString(com.rocketeercoders.wotonio.R.string.you_have_had);

	}

	public void testPreconditions() {
		assertNotNull(tv);
		assertNotNull(b);

	}

	public void testText() {
		assertEquals(initialText, (String) tv.getText());
	}

	@UiThreadTest
	public void testButtonPush() {
		b.performClick();
		assertEquals(String.format(textFormat, 1), (String) tv.getText());
	}

}
