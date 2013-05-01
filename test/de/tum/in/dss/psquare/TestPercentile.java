package de.tum.in.dss.psquare;

import java.util.Random;

import org.junit.Test;

public class TestPercentile {

	@Test
	public void testCreation() {
		float[] observations = { 3, 4, 5, 6, 2 };
		Percentile p = new Percentile(0.99f, observations);
		p.dump();
	}

	@Test
	public void testAccept() {
		float[] observations = { 3, 4, 5, 6, 2 };
		float percentile= 0.75f; 
		Percentile p = new Percentile(percentile, observations);

		// Accept all test values
		float[] test = new float[10000];
		Random rand = new Random();
		for (int i = 0; i < test.length; i++) {
			test[i] = Math.abs(rand.nextFloat() * 1000);
		}
		double r = 0;
		for (float t : test) {
			r = p.accept(t);
		}

		System.out.println("r: " + r);

		org.apache.commons.math3.stat.descriptive.rank.Percentile p2 = new org.apache.commons.math3.stat.descriptive.rank.Percentile(
				percentile * 100);
		double[] dall = new double[observations.length + test.length];
		for (int i = 0; i < observations.length; i++)
			dall[i] = observations[i];
		for (int i = 0; i < test.length; i++)
			dall[observations.length + i] = test[i];

		System.out.println("Compare: " + p2.evaluate(dall) + " vs " + r);
	}
}
