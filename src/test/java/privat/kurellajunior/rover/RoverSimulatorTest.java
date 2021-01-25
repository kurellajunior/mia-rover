package privat.kurellajunior.rover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class RoverSimulatorTest {

  @Test
  void runSimulation() {
    String testInput ="";
    StringWriter resultCollector = new StringWriter();
    RoverSimulator.runSimulation(new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8)), new PrintWriter(resultCollector));
    final String result = resultCollector.toString();

    assertTrue(result.startsWith("---- begin simulation\n"));
    assertTrue(result.endsWith("---- end simulation\n"));
  }
}