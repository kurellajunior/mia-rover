package privat.kurellajunior.rover;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
    out.println("---- read data");
    final List<Rover> plateau = readSimulationPlan(simulationDescription);
    out.println("---- begin simulation");
    for (Rover rover : plateau) {
      final int steps = rover.run();
      out.println("rover " + rover.id + " executed " + steps +" tasks: "+rover.position());
    }
    out.println("---- end simulation");
  }

  protected static List<Rover> readSimulationPlan(InputStream simulationDescription) {
    final Scanner scanner = new Scanner(simulationDescription);
    Position maxPos =  new Position(scanner.nextInt(10),scanner.nextInt(10));
    final ArrayList<Rover> rovers = new ArrayList<>();
    while (scanner.hasNextLine()) {
      rovers.add(
          new Rover(
              UUID.randomUUID().toString(),
              maxPos,
              new Position(scanner.nextInt(10), scanner.nextInt(10)),
              scanner.nextLine().trim().charAt(0))
      );
      rovers.get(rovers.size()-1).addTasks(scanner.nextLine());
    }
    return rovers;



  }
}

