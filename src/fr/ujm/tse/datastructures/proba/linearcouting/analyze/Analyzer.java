package fr.ujm.tse.datastructures.proba.linearcouting.analyze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import fr.ujm.tse.datastructures.proba.linearcouting.model.Estimators;
import fr.ujm.tse.datastructures.proba.linearcouting.model.LinearCounter;
import fr.ujm.tse.datastructures.proba.linearcouting.model.Packet;

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
           * Initialize an empty estimators table (instantiate {@link Estimators})
           * using default constructor
           */
          Estimators estimators = new Estimators();

          String trafficFile = "/Users/cgravier/Documents/Enseignement/English/hpc/probabilstic-datastructures/traffic";
          /**
           * Read log file line per line
           */
          BufferedReader br;
          try {
               br = new BufferedReader(new FileReader(trafficFile));
               String line;
               while ((line = br.readLine()) != null) {
                    /**
                     * For each line, parse the line and create a Packet instance
                     */
                    Packet packet = extractPacketFromLine(line);

                    /**
                     * Add the newly created Packet instance into the estimators
                     * table
                     */
                    if (packet != null) {
                         estimators.reccordPacket(packet);
                    }
               }
               br.close();
          } catch (FileNotFoundException e) {
               System.out.println("Cannot find traffic file (expected location : "
                         + trafficFile + ")");
               e.printStackTrace();
          } catch (IOException e) {
               e.printStackTrace();
          }

          /**
           * Use the linear counter 2D-array in order to estimate the three
           * cardinalities asked by the three questions in the lab objectives.
           */
          LinearCounter linearCounter = estimators.getEstimators()[0][0];

          System.out
                    .println("The 25th april 2014 from 00:00:00 to 00:59:59, there have been "
                              + linearCounter.getCardinality()
                              + " unique source addresses that have hit the destination address 192.168.1.1");
     }

     private Packet extractPacketFromLine(String line) {
          String[] array = line.split(" - ");
          try {
               Packet packet = new Packet(array[0], array[1],
                         Long.parseLong(array[2]));
               return packet;
          } catch (Exception e) {
               System.out
                         .println("Cannot parse packet in line \""
                                   + line
                                   + "\" (didn't matched expected format \"sourceip - destip - timestmap - bytecontent\")");
          }
          return null;
     }

     public static void main(String[] args) {
          Analyzer analyzer = new Analyzer();
          analyzer.readLogFile();
     }

}