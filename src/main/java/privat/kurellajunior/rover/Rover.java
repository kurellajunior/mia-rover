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
        switch (task){
          case 'M': move();
            break;
          case 'L': turnLeft();
            break;
          case 'R': turnRight();
            break;
          default:
            throw new UnknowTaskException(task);
        }
        executed++;
      } catch (RoverError roverError) {
        errors.add(roverError);
      }
    }
    return 0;
  }

  private void turnRight() {
    switch (heading) {
      case 'N': heading='E';
      case 'E': heading='S';
      case 'S': heading='W';
      case 'W': heading='N';
    }
  }

  private void turnLeft() {
    switch (heading) {
      case 'N': heading='W';
      case 'W': heading='S';
      case 'S': heading='E';
      case 'E': heading='N';
    }
  }

  private void move() throws RoverError {
    // TODO check for valid move before assigning
    position = position.update(heading);
  }
}
