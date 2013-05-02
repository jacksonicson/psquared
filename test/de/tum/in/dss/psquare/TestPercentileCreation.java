package de.tum.in.dss.psquare;

import org.junit.Test;

public class TestPercentileCreation {

	@Test
	public void testCreation() {
		PSquared p = new PSquared(0.99f);
		p.dump();
	}

}
