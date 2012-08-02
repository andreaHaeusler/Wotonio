package com.rocketeercoders.wotonio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GraphView extends View {

	private static final String TAG = "GraphView";

	private WeeklyGraphDataProvider weeklyGraphDataProvider;
	private Paint ourBlue;

	public GraphView(Context context) {
		super(context);
	}

	public GraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GraphView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void init(WeeklyGraphDataProvider weeklyGraphDataProvider) {
		this.weeklyGraphDataProvider = weeklyGraphDataProvider;
		ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int barWidth = canvas.getWidth() / 17;
		int graphHeight = canvas.getHeight() / 3;
		int fullBar = graphHeight - (4 * barWidth);
		Log.d(TAG, String.format("BarWidth=%d, GraphHeight=%d, FullBar=%d",
				barWidth, graphHeight, fullBar));
		canvas.drawRGB(0xff, 0xff, 0x00);
		drawAxes(canvas, barWidth, fullBar);
		drawBars(canvas, barWidth, fullBar);
	}

	private void drawAxes(Canvas canvas, int barWidth, int fullBar) {
		drawVerticalAxis(canvas, barWidth, fullBar);
		drawHorizontalAxis(canvas, barWidth, fullBar);
	}

	private void drawVerticalAxis(Canvas canvas, int barWidth, int fullBar) {
		Paint p = new Paint();
		p.setColor(Color.BLACK);
		canvas.drawLine(barWidth, 2 * barWidth + fullBar, 16 * barWidth, 2
				* barWidth + fullBar, p);
	}

	private void drawHorizontalAxis(Canvas canvas, int barWidth, int fullBar) {
		Paint p = new Paint();
		p.setColor(Color.BLACK);
		canvas.drawLine(barWidth, 2 * barWidth, barWidth, 2 * barWidth
				+ fullBar, p);
	}

	private void drawBars(Canvas canvas, int barWidth, int fullBar) {

		int max = weeklyGraphDataProvider.getMax();

		int verticalBlock = fullBar / max;

		for (int day = 0; day < 7; day++) {
			int amount = weeklyGraphDataProvider.getValueDaysAgo(6 - day);

			canvas.drawRect((day + 1) * 2 * barWidth, (2 * barWidth)
					+ (fullBar - (amount * verticalBlock)),
					(((day + 1) * 2) + 1) * barWidth, (2 * barWidth) + fullBar,
					ourBlue);
		}
	}
}
