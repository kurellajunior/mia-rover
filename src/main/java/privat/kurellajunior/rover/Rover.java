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
}
