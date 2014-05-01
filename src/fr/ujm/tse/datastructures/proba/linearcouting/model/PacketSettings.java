package fr.ujm.tse.datastructures.proba.linearcouting.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Christophe Gravier, <christophe.gravier@univ-st-etienne.fr>
 * 
 */
public class PacketSettings {

	/**
	 * The set of all possible IPs
	 */
	public static List<String> getPossibleIPs() {

		List<String> ips = new ArrayList<String>();
		for (int i = 1; i <= 255; i++) {
			ips.add("192.168.1." + i);
		}
		return ips;
	}
}
