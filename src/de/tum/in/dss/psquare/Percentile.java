package de.tum.in.dss.psquare;

public class Percentile {

	final int MARKERS = 5;
	double[] q = new double[MARKERS];
	int[] n = new int[MARKERS];
	double[] nd = new double[MARKERS];

	public Percentile(int p, double[] observations) {
		assert (observations.length == MARKERS);

		// Initialization
		for (int i = 0; i < MARKERS; i++) {
			q[i] = observations[i];
			n[i] = i;
		}

		// Desired marker positions
		nd[0] = 1;
		nd[1] = 1 + 2 * p;
		nd[2] = 1 + 4 * p;
		nd[3] = 3 + 2 * p;
		nd[4] = 5;
	}

	public void accept(double observation) {

	}

	public static void main(String arg[]) {
		double[] observations = { 3, 4, 5, 6, 2 };
		new Percentile(90, observations);
	}

}
