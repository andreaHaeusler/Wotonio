package com.rocketeercoders.wotonio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GraphSurface extends SurfaceView implements Runnable {

	private SurfaceHolder holder;
	private Thread thread = null;
	private boolean isRunning = false;
	private WeeklyGraphDataProvider weeklyGraphDataProvider;

	public GraphSurface(Context context,
			WeeklyGraphDataProvider weeklyGraphDataProvider) {
		super(context);
		holder = getHolder();
		this.weeklyGraphDataProvider = weeklyGraphDataProvider;
	}

	public void pause() {
		isRunning = false;
		while (true) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		thread = null;
	}

	public void resume() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!holder.getSurface().isValid())
				continue;

			Canvas canvas = holder.lockCanvas();

			int barWidth = canvas.getWidth() / 17;
			int graphHeight = canvas.getHeight() / 3;

			int fullBar = graphHeight - (4 * barWidth);
			int max = weeklyGraphDataProvider.getMax();

			int verticalBlock = fullBar / max;

			canvas.drawRGB(02, 02, 150);

			Paint ourBlue = new Paint();
			ourBlue.setColor(Color.BLUE);

			for (int day = 0; day < 7; day++) {
				int amount = weeklyGraphDataProvider.getValueDaysAgo(6 - day);

				canvas.drawRect((day + 1) * 2 * barWidth, (2 * barWidth)
						+ (fullBar - (amount * verticalBlock)),
						(((day + 1) * 2) + 1) * barWidth, (2 * barWidth)
								+ fullBar, ourBlue);
			}

			holder.unlockCanvasAndPost(canvas);
		}

	}

}
