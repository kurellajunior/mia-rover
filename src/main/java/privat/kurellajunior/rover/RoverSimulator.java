package privat.kurellajunior.rover;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 */
public class RoverSimulator {
  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      System.err.println("You need to provide at least one simulation input file");
      System.exit(23);
    }
    final PrintWriter out = new PrintWriter(System.out);
    runSimulation(new Scanner(new File(args[0]), StandardCharsets.UTF_8), out);
    out.close();
  }

  protected static void runSimulation(Scanner scanner, PrintWriter out) {
    out.println("---- read data");
    final List<Rover> plateau = readSimulationPlan(scanner);
    out.println("---- begin simulation");
    for (Rover rover : plateau) {
      final int steps = rover.run();
      out.println("rover " + rover.id + " executed " + steps + " tasks: " + rover.position() + rover.heading());
    }
    out.println("---- end simulation");
  }

  protected static List<Rover> readSimulationPlan(Scanner scanner) {
    Position maxPos = new Position(scanner.nextInt(10), scanner.nextInt(10));
    final ArrayList<Rover> rovers = new ArrayList<>();
    while (scanner.hasNextLine()) {
      rovers.add(
          new Rover(
              UUID.randomUUID().toString(),
              maxPos,
              new Position(scanner.nextInt(10), scanner.nextInt(10)),
              scanner.nextLine().trim().charAt(0))
      );
      rovers.get(rovers.size() - 1).addTasks(scanner.nextLine());
    }
    return rovers;

  }
}

