package privat.kurellajunior.rover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
class RoverSimulatorTest {

  @Test
  void runSimulation() {
    String testInput = "27 18";
    StringWriter resultCollector = new StringWriter();
    RoverSimulator.runSimulation(new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8)), new PrintWriter(resultCollector));
    final String result = resultCollector.toString();

    assertTrue(result.startsWith("---- read data\n"));
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
    assertEquals(new Position(5,6), result.get(0).maxPos);
    assertEquals(new Position(1,2), result.get(0).position());
    assertEquals('N', result.get(0).heading());
    assertEquals("LMLMLMLMM", result.get(0).taskList().stream().map(String::valueOf).collect(Collectors.joining()));
  }
}