package privat.kurellajunior.rover;

import privat.kurellajunior.rover.error.RoverOffCliffException;

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
    final List<Rover> plateau = runSimulation(new Scanner(new File(args[0]), StandardCharsets.UTF_8), out);
    // expected simplified output
    for (Rover rover : plateau) {
      out.println(rover.position().x() + " "+ rover.position().y()+" " + rover.heading());
    }
    out.close();
  }

  protected static List<Rover> runSimulation(Scanner scanner, PrintWriter out) {
    out.println("---- read data");
    final List<Rover> plateau = readSimulationPlan(scanner, out);
    out.println("---- begin simulation");
    for (Rover rover : plateau) {
      final int steps = rover.run();
      out.println("rover " + rover.id + " tasks executed: " + steps + ", skipped: "+rover.errors().size()+ " ⇒ " + rover.position() + rover.heading());
      for (Throwable error : rover.errors()) {
        out.println(" - " + error.getClass().getSimpleName() +": " + error.getMessage());
      }
    }
    out.println("---- end simulation");
    return plateau;
  }

  protected static List<Rover> readSimulationPlan(Scanner scanner, PrintWriter out) {
    Position maxPos = new Position(scanner.nextInt(10), scanner.nextInt(10));
    final ArrayList<Rover> rovers = new ArrayList<>();
    while (scanner.hasNextLine()) {
      try {
        final int x = scanner.nextInt(10);
        final int y = scanner.nextInt(10);
        final String heading = scanner.nextLine().trim();
        final String tasks = scanner.nextLine();
        final Rover rover = new Rover(UUID.randomUUID().toString(), maxPos, new Position(x, y), heading.charAt(0));
        rovers.add(rover);
        rover.addTasks(tasks);
        rover.setObstacles(rovers); // FIXME: this gives each rover access to all rovers. Be careful!
      } catch (RoverOffCliffException error) {
        out.println(error.getMessage());
      }
    }
    return rovers;

  }
}

