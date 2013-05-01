package de.tum.in.dss.psquare;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestPercentile {

	private float[] initialObservations = { 3, 4, 5, 6, 2 };
	private float pvalue = 0.99f;
	private Percentile percentile;

	private float[] randomTestData(int factor, int values) {
		percentile = new Percentile(pvalue, initialObservations);
		float[] test = new float[values];
		Random rand = new Random();
		for (int i = 0; i < test.length; i++) {
			test[i] = Math.abs(rand.nextFloat() * factor);
		}
		return test;
	}

	@Test
	public void testAccept() {
		percentile = new Percentile(pvalue, initialObservations);
		float[] test = randomTestData(100, 10000);
		for (float value : test) {
			double p = percentile.accept(value);
			Assert.assertTrue(p > 0);
		}
	}

	private void runWithPercentile(float ptest, float[] test)
	{
		percentile = new Percentile(ptest, initialObservations);
		double psquared = 0; 
		for (float value : test) {
			psquared = percentile.accept(value);
		}
		
		// 
		org.apache.commons.math3.stat.descriptive.rank.Percentile p2 = new org.apache.commons.math3.stat.descriptive.rank.Percentile(
				ptest * 100);
		double[] dall = new double[initialObservations.length + test.length];
		for (int i = 0; i < initialObservations.length; i++)
			dall[i] = initialObservations[i];
		for (int i = 0; i < test.length; i++)
			dall[initialObservations.length + i] = test[i];
		double apache = p2.evaluate(dall);
		
		System.out.println(ptest + ": with " + test.length + " got: " + apache + " - " + psquared);
		double max = Math.max(apache, ptest); 
		double percentage = Math.abs(apache-psquared) / max; 
		Assert.assertTrue(percentage < 0.03); 
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
		float[] test = randomTestData(100, 10000);
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
		float[] test = randomTestData(100000, 10000);
		runWithPercentile(0.20f, test);
	}
	
	@Test
	public void test5PercentileHighValues() {
		float[] test = randomTestData(100000, 100000);
		runWithPercentile(0.05f, test);
	}
}
