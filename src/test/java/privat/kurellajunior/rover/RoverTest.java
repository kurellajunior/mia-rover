package privat.kurellajunior.rover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
class RoverTest {

  @Test
  void addTasks() {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    assertEquals(0, rover.taskList().size());
    rover.addTasks("MMM");
    assertEquals(3, rover.taskList().size());

  }

  @Test
  void runValidMove() {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("MMM");
    assertEquals(3, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(0, rover.errors().size());
    assertEquals(new Position(2,5), rover.position());
    assertEquals('N', rover.heading());
  }

  @Test
  void runValidTurn() {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("LLLLRRRRR");
    assertEquals(9, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(0, rover.errors().size());
    assertEquals(new Position(2,2), rover.position());
    assertEquals('E', rover.heading());
  }

  @Test
  void runOneBroken() {
    final Rover rover = new Rover("t1", new Position(5, 5), new Position(2, 2), 'N');
    rover.addTasks("XMM");
    assertEquals(2, rover.run());
    assertEquals(0, rover.taskList().size());
    assertEquals(1, rover.errors().size());
  }
}