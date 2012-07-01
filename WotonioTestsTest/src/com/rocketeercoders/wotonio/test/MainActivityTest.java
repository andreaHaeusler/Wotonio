package com.rocketeercoders.wotonio.test;

import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.rocketeercoders.wotonio.MainActivity;

public class MainActivityTest extends
		android.test.ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity activity;
	private TextView tv;
	private Button b, resetB;
	private String initialText, textFormat, onFirstClickTextFormat;

	public MainActivityTest() {
		super("com.rocketeercoders.wotonio.MainActivity", MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		tv = (TextView) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.tvMessages);
		b = (Button) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.bLetsGetDrinking);
		resetB = (Button) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.bResetDB);
		initialText = activity
				.getString(com.rocketeercoders.wotonio.R.string.not_yet_clicked);
		textFormat = activity
				.getString(com.rocketeercoders.wotonio.R.string.you_have_had);
		onFirstClickTextFormat = activity
				.getString(com.rocketeercoders.wotonio.R.string.first_click);

	}

	public void testPreconditions() {
		assertNotNull(tv);
		assertNotNull(b);

	}

	@UiThreadTest
	public void testResetButton() {
		resetB.performClick();
		assertEquals(initialText, (String) tv.getText());
	}

	@UiThreadTest
	public void testButtonPush() {
		resetB.performClick();
		b.performClick();
		assertEquals(onFirstClickTextFormat, (String) tv.getText());
	}
	
	@UiThreadTest
	public void testButtonPush1(){
		resetB.performClick();
		b.performClick();
		b.performClick();
		assertEquals(String.format(textFormat, 2), (String) tv.getText());
	}

}
