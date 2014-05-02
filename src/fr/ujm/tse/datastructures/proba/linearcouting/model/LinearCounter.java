package fr.ujm.tse.datastructures.proba.linearcouting.model;

import java.util.BitSet;

import fr.ujm.tse.datastructures.proba.linearcouting.generator.TrafficSettings;

/**
 * A Linear Counter implementation.
 * 
 * @author Christophe Gravier, <christophe.gravier@univ-st-etienne.fr>
 * 
 */
public class LinearCounter {

	/**
	 * The Linear Counter itself.
	 */
	BitSet mask = new BitSet(TrafficSettings.LINEAR_COUNTER_SIZE);

	/**
	 * The mapping function that will set the i-th bit in the Linear Counter to
	 * 1, where i is the output of the hash function applied to the
	 * <code>packet</code> given as parameter.
	 * 
	 * @param packet
	 *            the packet to record to the linear counter.
	 */
	public void add(Packet packet) {
		int position = packet.hashPacket();
		mask.set(position);
	}

	/**
	 * @return the weight of the linear counter, i.e. the number of bit set to
	 *         one in the associated BitSet.
	 */
	public int getWeight() {
		return mask.cardinality();
	}

	/**
	 * The actual Cardinality estimator of the linear counter.
	 * 
	 * @return
	 */
	public double getCardinality() {
		// here I decompose the computation of the estimator for your
		// convenience.
		int m = TrafficSettings.LINEAR_COUNTER_SIZE;
		int w = this.getWeight();
		double zeroRatio = (m - w) / (double)m;
		return -m * Math.log(zeroRatio);
	}
}
