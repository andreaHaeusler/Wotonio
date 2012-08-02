package com.rocketeercoders.wotonio.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rocketeercoders.wotonio.VerticalAxisScaler;

public class VerticalAxisScalerTest {

	@Test
	public void testAxisHeightIfMaxValIs0() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(1, verticalAxisScaler.axisHeight(0));
	}

	@Test
	public void testAxisHeightIfMaxValIs1() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(2, verticalAxisScaler.axisHeight(1));
	}

	@Test
	public void testAxisHeightIfMaxValIs2() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(5, verticalAxisScaler.axisHeight(2));
	}

	@Test
	public void testAxisHeightIfMaxValIs3() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(5, verticalAxisScaler.axisHeight(3));
	}

	@Test
	public void testAxisHeightIfMaxValIs4() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(5, verticalAxisScaler.axisHeight(4));
	}

	@Test
	public void testAxisHeightIfMaxValIs5() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(10, verticalAxisScaler.axisHeight(5));
	}

	@Test
	public void testAxisHeightIfMaxValIs6() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(10, verticalAxisScaler.axisHeight(6));
	}

	@Test
	public void testAxisHeightIfMaxValIs7() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(10, verticalAxisScaler.axisHeight(7));
	}

	@Test
	public void testAxisHeightIfMaxValIs8() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(10, verticalAxisScaler.axisHeight(8));
	}

	@Test
	public void testAxisHeightIfMaxValIs9() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(10, verticalAxisScaler.axisHeight(9));
	}

	@Test
	public void testAxisHeightIfMaxValIs10() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(20, verticalAxisScaler.axisHeight(10));
	}
	
	@Test
	public void testAxisHeightIfMaxValIs11() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(20, verticalAxisScaler.axisHeight(11));
	}
	
	@Test
	public void testAxisHeightIfMaxValIs20() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(50, verticalAxisScaler.axisHeight(20));
	}
	
	@Test
	public void testAxisHeightIfMaxValIs21() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(50, verticalAxisScaler.axisHeight(21));
	}
	
	@Test
	public void testAxisHeightIfMaxValIs50() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(100, verticalAxisScaler.axisHeight(50));
	}
	
	@Test
	public void testAxisHeightIfMaxValIs51() {
		VerticalAxisScaler verticalAxisScaler = new VerticalAxisScaler();
		assertEquals(100, verticalAxisScaler.axisHeight(51));
	}
}
