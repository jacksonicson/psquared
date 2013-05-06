package de.tum.in.dss.psquare;

import junit.framework.Assert;

import org.junit.Test;

public class TestCornerCases {

	@Test
	public void testOne() {
		PSquared psquared = new PSquared(0.99f);
		float[] test = { 100 };
		double p = 0;
		for (float value : test) {
			p = psquared.accept(value);
			Assert.assertTrue(p >= 0);
		}

		Assert.assertTrue(p == 100);
	}

	@Test
	public void testTwo() {
		PSquared psquared = new PSquared(0.99f);
		float[] test = { 100, 200 };
		double p = 0;
		for (float value : test) {
			p = psquared.accept(value);
			Assert.assertTrue(p >= 0);
		}

		Assert.assertTrue(p == 200);
	}
	
	@Test
	public void testThree() {
		PSquared psquared = new PSquared(0.50f);
		float[] test = { 100, 200, 300 };
		double p = 0;
		for (float value : test) {
			p = psquared.accept(value);
			Assert.assertTrue(p >= 0);
		}

		Assert.assertTrue(p == 200);
	}
	
	@Test
	public void testFour() {
		PSquared psquared = new PSquared(0.50f);
		float[] test = { 100, 200, 300, 400 };
		double p = 0;
		for (float value : test) {
			p = psquared.accept(value);
			Assert.assertTrue(p >= 0);
		}

		Assert.assertTrue(p == 300);
	}

}
