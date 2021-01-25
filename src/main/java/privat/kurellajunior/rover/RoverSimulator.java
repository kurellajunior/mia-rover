package privat.kurellajunior.rover;

import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 */
public class RoverSimulator {
  public static void main(String[] args) {
    if (args.length <1) {
      System.err.println("You need to provide at least one simulation input file");
      System.exit(23);
    }
    runSimulation(RoverSimulator.class.getResourceAsStream(args[0]), new PrintWriter(System.out));
  }

  protected static void runSimulation(InputStream simulationDescription, PrintWriter out) {
  }

}

