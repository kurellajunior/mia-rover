package privat.kurellajunior.rover;

import org.junit.jupiter.api.Test;
import privat.kurellajunior.rover.error.MovementOffPlateauException;
import privat.kurellajunior.rover.error.RoverOffCliffException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 */
class RoverTest {

  @Test
  void createRoverOffCliff() {
    RoverOffCliffException exception = assertThrows(RoverOffCliffException.class, () -> new Rover("c1", new Position(4, 4), new Position(5, 2), 'N'));
  }

  @Test
  void addTasks() throws RoverOffCliffException {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    assertEquals(0, rover.taskList().size());
    rover.addTasks("MMM");
    assertEquals(3, rover.taskList().size());

  }

  @Test
  void runValidMove() throws RoverOffCliffException {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("MMM");
    assertEquals(3, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(0, rover.errors().size());
    assertEquals(new Position(2,5), rover.position());
    assertEquals('N', rover.heading());
  }

  @Test
  void runOffPlateau() throws RoverOffCliffException {
    final Rover rover = new Rover("t1", new Position(0, 0), new Position(0, 0), 'N');
    rover.addTasks("MLMLMLM");
    assertEquals(3, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(4, rover.errors().size());
    assertEquals(MovementOffPlateauException.class, rover.errors().get(0).getClass());
    assertEquals(new Position(0,0), rover.position());
    assertEquals('E', rover.heading());
  }


  @Test
  void runValidTurn() throws RoverOffCliffException {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("LLLLRRRRR");
    assertEquals(9, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(0, rover.errors().size());
    assertEquals(new Position(2,2), rover.position());
    assertEquals('E', rover.heading());
  }

  @Test
  void runOneBroken() throws RoverOffCliffException {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("XMM");
    assertEquals(2, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(1, rover.errors().size());
  }

  @Test
  void skipObstacles() throws RoverOffCliffException {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("MRM");
    final Rover obstacle = new Rover("o1", new Position(5, 5), new Position(3, 3), 'S');
    rover.setObstacles(Arrays.asList(obstacle));
    assertEquals(2, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(1, rover.errors().size());
    assertEquals(new Position(2,3), rover.position());
    assertEquals('E', rover.heading());
  }

  @Test
  void ignoreYourself() throws RoverOffCliffException {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("MLLM");
    rover.setObstacles(Arrays.asList(rover));
    assertEquals(4, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(0, rover.errors().size());
    assertEquals(new Position(2,2), rover.position());
    assertEquals('S', rover.heading());
  }

}