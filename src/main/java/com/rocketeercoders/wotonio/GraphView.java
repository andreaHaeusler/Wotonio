package com.rocketeercoders.wotonio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {

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
		int max = weeklyGraphDataProvider.getMax();

		int verticalBlock = fullBar / max;

		canvas.drawRGB(02, 02, 150);

		for (int day = 0; day < 7; day++) {
			int amount = weeklyGraphDataProvider.getValueDaysAgo(6 - day);

			canvas.drawRect((day + 1) * 2 * barWidth, (2 * barWidth)
					+ (fullBar - (amount * verticalBlock)),
					(((day + 1) * 2) + 1) * barWidth, (2 * barWidth) + fullBar,
					ourBlue);
		}
	}
}
