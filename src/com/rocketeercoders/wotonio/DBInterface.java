package com.rocketeercoders.wotonio;

public interface DBInterface {

	public static final int DATABASE_VERSION = 1;

	public abstract DBInterface openDatabase();

	public abstract void closeDatabase();

	public abstract int getCount();

	public abstract void addAGlassOfWater(int waterCounter);

	public abstract void clearDBStructure();

	public abstract int getCountDrunkBetween(long aDayAgo, long now);

}