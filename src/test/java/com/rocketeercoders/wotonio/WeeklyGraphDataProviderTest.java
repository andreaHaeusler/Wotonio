package com.rocketeercoders.wotonio.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.rocketeercoders.wotonio.DBInterface;
import com.rocketeercoders.wotonio.WeeklyGraphDataProvider;

public class WeeklyGraphDataProviderTest {

	@Test
	public void testGetMax() {
		DBInterface db = mock(DBInterface.class);
		when(db.getCountDrunkBetween(anyLong(), anyLong())).thenReturn(0)
				.thenReturn(5).thenReturn(0).thenReturn(9).thenReturn(0)
				.thenReturn(3).thenReturn(0);

		WeeklyGraphDataProvider provider = new WeeklyGraphDataProvider(db);
		int max = provider.getMax();
		assertEquals(9, max);
	}

	@Test
	public void testGetValueDaysAgo() {
		DBInterface db = mock(DBInterface.class);
		when(db.getCountDrunkBetween(anyLong(), anyLong())).thenReturn(1)
				.thenReturn(2).thenReturn(7).thenReturn(9).thenReturn(3)
				.thenReturn(3).thenReturn(4);

		WeeklyGraphDataProvider provider = new WeeklyGraphDataProvider(db);
		int value = provider.getValueDaysAgo(3);
		assertEquals(9, value);
	}
}
