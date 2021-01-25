package privat.kurellajunior.rover;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 *
 */
class RoverSimulatorTest {

  @Test
  void runSimulation() {
    String testInput ="";
    StringWriter resultCollector = new StringWriter();
    RoverSimulator.runSimulation(new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8)), new PrintWriter(resultCollector));

  }
}