package privat.kurellajunior.rover;


/**
 *
 */
public class Rover {
  final String id;
  final Position maxPos;
  private char heading;
  private Position position;

  public Rover(String id, Position maxPos, Position position, char heading) {
    this.id = id;
    this.maxPos = maxPos;
    this.heading = heading;
    this.position = position;
  }

}
