package fr.ujm.tse.datastructures.proba.linearcouting.analyze;

import fr.ujm.tse.datastructures.proba.linearcouting.model.Estimators;

public class Analyzer {

	/**
	 * TODO :
	 * <ul>
	 * <li>Initialize an empty estimators table (intantiate {@link Estimators})
	 * using default constructor</li>
	 * <li>Read log file line per line</li>
	 * <ul>
	 * <li>For each line, parse the line and create a Packet instance</li>
	 * <li>Add the newly created Packet instance into the estimators table</li>
	 * </ul>
	 * <li>Use the linear counter 2D-array in order to estimate the three
	 * cardinalities asked by the three questions in the lab objectives. </ul>
	 */
	public void readLogFile() {

		/**
		 * TODO Your work of coding starts here, once you have grasped the
		 * different classes available to you.
		 */

	}

	/**
	 * This is the program entry point to use when running your probabilistic
	 * counters.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Analyzer analyzer = new Analyzer();
		analyzer.readLogFile();
	}

}
