package com.rocketeercoders.wotonio;

public class WeeklyGraphDataProvider {

	private int[] cache;
	private DBInterface db;

	public WeeklyGraphDataProvider(DBInterface db) {
		this.db = db;
	}

	public int getMax() {
		cache = new int[7];
		db.openDatabase();
		int max = 0;
		for (int i = 0; i < 7; i++) {
			long now = (System.currentTimeMillis() / 1000L) - (i * 86400);
			long aDayAgo = (System.currentTimeMillis() / 1000L)
					- ((i + 1) * 86400);
			int drunkOnDay = db.getCountDrunkBetween(aDayAgo, now);
			cache[i] = drunkOnDay;
			if (drunkOnDay > max)
				max = drunkOnDay;
		}
		db.closeDatabase();
		return max;
	}

	public int getValueDaysAgo(int daysAgo) {
		if (cache == null)
			getMax();
		return cache[daysAgo];

	}
}
