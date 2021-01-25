package privat.kurellajunior.rover;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class Rover {
  final String id;
  final Position maxPos;
  private char heading;
  private Position position;
  private Queue<Character> tasks;
  private List<Throwable> errors;

  public Rover(String id, Position maxPos, Position position, char heading) {
    this.id = id;
    this.maxPos = maxPos;
    this.heading = heading;
    this.position = position;
    tasks = new LinkedList<>();
    errors = new ArrayList<>(1);
  }

  public Position position() {
    return position;
  }

  public char heading() {
    return heading;
  }

  public List<Character> taskList() {
    return new ArrayList<Character>(tasks);
  }

  public int addTasks(String taskString) {
    for (char task : taskString.toCharArray()) {
      tasks.add(task);
    }
    return tasks.size();
  }

  public int run() {
    int executed = 0;
    while (tasks.size() > 0) {

      try {
        final Character task = tasks.poll();
        switch (task) {
          case 'M':
            move();
            break;
          case 'L':
            turnLeft();
            break;
          case 'R':
            turnRight();
            break;
          default:
            throw new UnknowTaskException(task);
        }
        executed++;
      } catch (RoverError roverError) {
        errors.add(roverError);
      }
    }
    return executed;
  }

  private void turnRight() {
    switch (heading) {
      case 'N':
        heading = 'E';
        break;
      case 'E':
      heading = 'S';
        break;
      case 'S':
      heading = 'W';
        break;
      case 'W':
      heading = 'N';
        break;
    }
  }

  private void turnLeft() {
    switch (heading) {
      case 'N':
        heading = 'W';
        break;
      case 'W':
        heading = 'S';
        break;
      case 'S':
        heading = 'E';
        break;
      case 'E':
        heading = 'N';
        break;
    }
  }

  private void move() throws RoverError {
    // TODO check for valid move before assigning
    position = position.update(heading);
  }

  public List<Throwable> errors() {
    return errors;
  }
}
