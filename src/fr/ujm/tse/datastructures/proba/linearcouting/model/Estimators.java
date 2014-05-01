package fr.ujm.tse.datastructures.proba.linearcouting.model;

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

	private static final int nbAddress = 254;
	private static final int nbTimeSLot = 168;

	LinearCounter[][] estimators = new LinearCounter[nbAddress][nbTimeSLot];

	/**
	 * Reccord a new packet to the ad hoc estiamtor, depending on the
	 * destination address and the timestamp of the packet.
	 * 
	 * @param packet
	 */
	public void reccordPacket(Packet packet) {
		estimators[packet.getIpRange()][packet.getTimestampIndex()].add(packet);
	}

}