package fr.ujm.tse.datastructures.proba.linearcouting.generator;

/**
 * Some configuration for the program.
 * 
 * @author Christophe Gravier, <christophe.gravier@univ-st-etienne.fr>
 * 
 */
public class TrafficSettings {

	/**
	 * Where to dump the traffic log file that we can generate using
	 * {@link TrafficFileGenerator}
	 */
	public static String DEST_TRAFFIC_FILE = "./traffic";

	/**
	 * How many lines the traffic log file should have
	 */
	public static long TRAFFIC_SIZE = 1000000;

	/**
	 * The string separator in the traffic log file.
	 */
	public static String TRAFFIC_SEPARATOR = " - ";

	/**
	 * The size in bits of each Linear Counter. There are 254 unique source
	 * addresses in the private network subnet, hence under a load factor of 10
	 * and a biais of the estimator of 0.1, this means 25.5 bits at minimum.
	 */
	public static int LINEAR_COUNTER_SIZE = 26;

}
