package de.tum.in.dss.psquare;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

public class TestRandom {

	private float[] randomTestData(int factor, int values) {
		float[] test = new float[values];
		Random rand = new Random();
		for (int i = 0; i < test.length; i++) {
			test[i] = Math.abs(rand.nextFloat() * factor);
		}
		return test;
	}

	private float getApacheMathPercentile(float percentile, float[] test) {
		org.apache.commons.math3.stat.descriptive.rank.Percentile p2 = new org.apache.commons.math3.stat.descriptive.rank.Percentile(
				percentile * 100);

		double[] dall = new double[test.length];
		for (int i = 0; i < test.length; i++)
			dall[i] = test[i];

		return (float) p2.evaluate(dall);
	}

	@Test
	public void testAccept() {
		PSquared psquared = new PSquared(0.99f);
		float[] test = randomTestData(100, 10000);
		for (float value : test) {
			double p = psquared.accept(value);
			Assert.assertTrue(p >= 0);
		}
	}

	private void assertValues(float a, float b) {
		double max = Math.max(a, b);
		double percentage = Math.abs(a - b) / max;
		Assert.assertTrue(percentage < 0.01);
	}

	private void runWithPercentile(float percentile, float[] test) {
		PSquared psquared = new PSquared(percentile);
		float p2value = 0;
		for (float value : test)
			p2value = psquared.accept(value);

		float amValue = getApacheMathPercentile(percentile, test);
		assertValues(p2value, amValue);
	}

	@Test
	public void test99Percentile() {
		float[] test = randomTestData(100, 10000);
		runWithPercentile(0.99f, test);
	}

	@Test
	public void test90Percentile() {
		float[] test = randomTestData(100, 10000);
		runWithPercentile(0.90f, test);
	}

	@Test
	public void test20Percentile() {
		float[] test = randomTestData(100, 100000);
		runWithPercentile(0.20f, test);
	}

	@Test
	public void test5Percentile() {
		float[] test = randomTestData(50, 990000);
		runWithPercentile(0.50f, test);
	}

	@Test
	public void test99PercentileHighValues() {
		float[] test = randomTestData(100000, 10000);
		runWithPercentile(0.99f, test);
	}

	@Test
	public void test90PercentileHighValues() {
		float[] test = randomTestData(100000, 100000);
		runWithPercentile(0.90f, test);
	}

	@Test
	public void test20PercentileHighValues() {
		float[] test = randomTestData(100000, 100000);
		runWithPercentile(0.20f, test);
	}

	@Test
	public void test5PercentileHighValues() {
		float[] test = randomTestData(100000, 100000);
		runWithPercentile(0.05f, test);
	}
}
