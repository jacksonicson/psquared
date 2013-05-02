package de.tum.in.dss.psquare;

import org.junit.Test;

public class TestPercentileCreation {

	private float[] initialObservations = { 3, 4, 5, 6, 2 };

	@Test
	public void testCreation() {
		PSquared p = new PSquared(0.99f, initialObservations);
		p.dump();
	}

}