package com.rocketeercoders.wotonio.test;

import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.rocketeercoders.wotonio.MainActivity;

public class MainActivityTest extends
		android.test.ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity activity;
	private TextView tv;
	private Button bHaveADrink, bWhatHaveIDrunk, bResetDB;
	private String initialText;

	public MainActivityTest() {
		super("com.rocketeercoders.wotonio.MainActivity", MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		tv = (TextView) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.tvMessages);
		bHaveADrink = (Button) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.bLetsGetDrinking);
		bWhatHaveIDrunk = (Button) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.bConsumed);
		bResetDB = (Button) activity
				.findViewById(com.rocketeercoders.wotonio.R.id.bResetDB);
		initialText = activity
				.getString(com.rocketeercoders.wotonio.R.string.not_yet_clicked);
	}

	@UiThreadTest
	public void testPreconditions() {
		assertNotNull(tv);
		assertNotNull(bHaveADrink);
		assertNotNull(bWhatHaveIDrunk);
		assertNotNull(bResetDB);
		assertEquals(initialText, (String) tv.getText());
	}
	/*
	 * Test obsolete
	 * 
	 * @UiThreadTest public void testHaveADrinkButtonPush() {
	 * bHaveADrink.performClick();
	 * 
	 * ActivityManager am = (ActivityManager) activity
	 * .getSystemService(Context.ACTIVITY_SERVICE);
	 * List<ActivityManager.RunningTaskInfo> processes = am.getRunningTasks(1);
	 * 
	 * assertEquals(Drink.class.getName(),
	 * processes.get(0).topActivity.getClassName());
	 * 
	 * activity.finish(); }
	 */
	/*
	 * 
	 * @UiThreadTest public void testResetButton() { bResetDB.performClick();
	 * assertEquals(initialText, (String) tv.getText()); }
	 * 
	 * 
	 * @UiThreadTest public void testButtonPush1() { bResetDB.performClick();
	 * bHaveADrink.performClick(); bHaveADrink.performClick();
	 * assertEquals(String.format(textFormat, 2), (String) tv.getText()); }
	 */
}
