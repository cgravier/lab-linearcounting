package fr.ujm.tse.datastructures.proba.linearcouting.model;

import java.util.Arrays;

/**
 * A class hosting the estimators in a two dimensional array whose :
 * <ul>
 * <li>lines are the IP address from 192.168.1.1 to 192.168.1.255 (254 lines)</li>
 * <li>columns are time slot of one hour, starting from the first hour of April
 * 25th 2014, and ending with the last hour of May 1st 2014 (168 columns: 24
 * hours * 7 days)</li>
 * </ul>
 * 
 * @author Christophe Gravier, <christophe.gravier@univ-st-etienne.fr>
 */
public class Estimators {

	private static final int nbAddress = 255;
	private static final int nbTimeSLot = 169;

	private LinearCounter[][] estimators = new LinearCounter[nbAddress][nbTimeSLot];

	public Estimators() {
		for (int i = 0; i < nbAddress; i++) {
			for (int j = 0; j < nbTimeSLot; j++) {
				estimators[i][j] = new LinearCounter();
			}
		}
	}

	/**
	 * Reccord a new packet to the ad hoc estiamtor, depending on the
	 * destination address and the timestamp of the packet.
	 * 
	 * @param packet
	 */
	public void reccordPacket(Packet packet) {
		int ipRangeIdx = packet.getIpRange();
		int timestampTangeIdx = packet.getTimestampIndex();
		try {
			estimators[ipRangeIdx][timestampTangeIdx].add(packet);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public LinearCounter[][] getEstimators() {
		return estimators;
	}

	public void setEstimators(LinearCounter[][] estimators) {
		this.estimators = estimators;
	}

	public static int getNbaddress() {
		return nbAddress;
	}

	public static int getNbtimeslot() {
		return nbTimeSLot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(estimators);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estimators other = (Estimators) obj;
		if (!Arrays.deepEquals(estimators, other.estimators))
			return false;
		return true;
	}

}