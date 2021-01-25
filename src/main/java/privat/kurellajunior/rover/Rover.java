package privat.kurellajunior.rover;

import privat.kurellajunior.rover.error.CollisionException;
import privat.kurellajunior.rover.error.MovementOffPlateauException;
import privat.kurellajunior.rover.error.RoverError;
import privat.kurellajunior.rover.error.RoverOffCliffException;
import privat.kurellajunior.rover.error.UnknowTaskException;
import privat.kurellajunior.rover.error.UnknownDirectionException;

import java.util.ArrayList;
import java.util.Collections;
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
  private final Queue<Character> tasks;
  private final List<Throwable> errors;
  private List<Rover> obstacles;

  public Rover(String id, Position maxPos, Position position, char heading) throws RoverOffCliffException {
    this.id = id;
    this.maxPos = maxPos;
    if (isOffPlateau(position)) {
      throw new RoverOffCliffException(position);
    }
    this.heading = heading;
    this.position = position;
    tasks = new LinkedList<>();
    errors = new ArrayList<>(1);
    obstacles = Collections.emptyList();
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

  public void setObstacles(List<Rover> obstacles) {
    this.obstacles = obstacles;
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

  private void turnRight() throws UnknownDirectionException {
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
      default:
        throw new UnknownDirectionException(heading);
    }
  }

  private void turnLeft() throws UnknownDirectionException {
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
      default:
        throw new UnknownDirectionException(heading);
    }
  }

  private void move() throws RoverError {
    final Position nextPos = position.update(heading);
    // test for end of plateau
    if (isOffPlateau(nextPos)) {
      throw new MovementOffPlateauException(position, heading);
    }
    // collision test
    if (obstacles.stream().anyMatch(rover -> rover.position.equals(nextPos))) {
      throw new CollisionException(position, heading);
    }
    position = nextPos;
  }

  private boolean isOffPlateau(Position nextPos) {
    return nextPos.x() < 0 || nextPos.x() > maxPos.x()
           || nextPos.y() < 0 || nextPos.y() > maxPos.y();
  }

  public List<Throwable> errors() {
    return errors;
  }
}
