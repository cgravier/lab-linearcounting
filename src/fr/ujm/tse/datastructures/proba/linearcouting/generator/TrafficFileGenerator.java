package fr.ujm.tse.datastructures.proba.linearcouting.generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import fr.ujm.tse.datastructures.proba.linearcouting.model.PacketSettings;

/**
 * 
 * @author Christophe Gravier, <christophe.gravier@univ-st-etienne.fr>
 */
public class TrafficFileGenerator {

	public static void main(String[] args) {
		TrafficFileGenerator trafficGen = new TrafficFileGenerator();
		try {
			trafficGen.generate();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate a traffic file randomly of {@link TrafficSettings#TRAFFIC_SIZE}
	 * lines.
	 * 
	 * @throws FileNotFoundException
	 */
	private void generate() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(TrafficSettings.DEST_TRAFFIC_FILE);
		for (int i = 0; i < TrafficSettings.TRAFFIC_SIZE; i++) {
			out.write(generateLine() + "\n");
		}
		out.close();
	}

	/**
	 * Generate a random line to be put in the traffic log file that we are
	 * trying to generate.
	 * 
	 * @return
	 */
	private String generateLine() {

		// pick a random IP for source IP
		String source = PacketSettings.getPossibleIPs().get(
				RandInt.randInt(0, 254));

		// pick a random IP for dest IP
		String dest = PacketSettings.getPossibleIPs().get(
				RandInt.randInt(0, 254));

		// pick a random timestamp between 25 th april 2014 midnight and 1st may
		// 2014 midnight -1 sec
		long timeStamp = RandInt.randInt(1398376800, 1398376800 + (3600 * 168));

		// generate a random 4 bytes content
		String randomContent = makeRandomContent();

		return source + TrafficSettings.TRAFFIC_SEPARATOR + dest
				+ TrafficSettings.TRAFFIC_SEPARATOR + timeStamp
				+ TrafficSettings.TRAFFIC_SEPARATOR + randomContent;
	}

	/**
	 * @return A 4 characters random content for the sake of the example. Taken
	 *         from
	 *         http://stackoverflow.com/questions/2863852/how-to-generate-a-
	 *         random-string-in-java
	 */
	private String makeRandomContent() {
		Random ran = new Random();
		int top = 3;
		char data = ' ';
		String dat = "";

		for (int i = 0; i <= top; i++) {
			data = (char) (ran.nextInt(25) + 97);
			dat = data + dat;
		}
		return dat;
	}
}
