package privat.kurellajunior.rover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
class RoverSimulatorTest {

  @Test
  void runSimulation() {
    String testInput = "";
    StringWriter resultCollector = new StringWriter();
    RoverSimulator.runSimulation(new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8)), new PrintWriter(resultCollector));
    final String result = resultCollector.toString();

    assertTrue(result.startsWith("---- begin simulation\n"));
    assertTrue(result.endsWith("---- end simulation\n"));
  }

  @Test
  void readSimulationPlan() {
    String testInput =
        "5 6\n" +
        "1 2 N\n" +
        "LMLMLMLMM\n" +
        "3 3 E\n" +
        "MMRMMRMRRM";
    final List<Rover> result = RoverSimulator.readSimulationPlan(new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8)));
    assertEquals(2, result.size(), "wrong line count");
    assertEquals(result.get(0).maxPos, new Position(5,6));
    assertEquals(result.get(0).position(), new Position(1,2));
    assertEquals(result.get(0).heading(), 'N');
  }
}