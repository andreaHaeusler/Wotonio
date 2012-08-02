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
	private VerticalAxisScaler verticalAxisScaler;

	public GraphView(Context context) {
		super(context);
	}

	public GraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GraphView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void init(WeeklyGraphDataProvider weeklyGraphDataProvider,
			VerticalAxisScaler verticalAxisScaler) {
		this.weeklyGraphDataProvider = weeklyGraphDataProvider;
		this.verticalAxisScaler = verticalAxisScaler;
		ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int barWidth = canvas.getWidth() / 17;
		int graphHeight = canvas.getHeight() / 3;
		int fullBar = graphHeight - (4 * barWidth);
		int max = weeklyGraphDataProvider.getMax();
		max = verticalAxisScaler.axisHeight(max);
		Log.d(TAG, String.format(
				"BarWidth=%d, GraphHeight=%d, FullBar=%d, Max=%d", barWidth,
				graphHeight, fullBar, max));
		canvas.drawRGB(0xff, 0xff, 0x00);
		drawAxes(canvas, barWidth, fullBar, max);
		drawBars(canvas, barWidth, fullBar, max);
	}

	private void drawAxes(Canvas canvas, int barWidth, int fullBar, int max) {
		drawVerticalAxis(canvas, barWidth, fullBar, max);
		drawHorizontalAxis(canvas, barWidth, fullBar);
	}

	private void drawVerticalAxis(Canvas canvas, int barWidth, int fullBar,
			int max) {
		Paint black = new Paint();
		black.setColor(Color.BLACK);
		black.setTextSize(35);
		black.setTextAlign(Paint.Align.CENTER);

		Paint gray = new Paint();
		gray.setColor(Color.GRAY);

		canvas.drawLine(barWidth, 2 * barWidth, barWidth, 2 * barWidth
				+ fullBar, black);
		int ticksToDraw = getTicksToDraw(max);
		int step = max / ticksToDraw;
		Log.d(TAG, String.format("ticksToDraw=%d, step=%d", ticksToDraw, step));
		for (int tick = 0; tick < ticksToDraw; tick++) {
			int ypos = (2 * barWidth + fullBar)
					- ((fullBar / ticksToDraw) * (tick + 1));
			Log.d(TAG, String.format("tick=%d, ypos=%d", tick, ypos));
			canvas.drawText(Integer.toString((tick + 1) * step), barWidth / 2,
					ypos + 15, black);
			canvas.drawLine(barWidth, ypos, 16 * barWidth, ypos, gray);
		}
	}

	private int getTicksToDraw(int max) {
		switch (max) {
		case 0:
			return 1;
		case 1:
			return 1;
		case 2:
			return 2;
		default:
			return 5;
		}
	}

	private void drawHorizontalAxis(Canvas canvas, int barWidth, int fullBar) {
		Paint p = new Paint();
		p.setColor(Color.BLACK);
		canvas.drawLine(barWidth, 2 * barWidth + fullBar, 16 * barWidth, 2
				* barWidth + fullBar, p);
	}

	private void drawBars(Canvas canvas, int barWidth, int fullBar, int max) {

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
