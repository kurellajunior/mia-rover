package privat.kurellajunior.rover.error;

import privat.kurellajunior.rover.Position;

/**
 *
 */
public class RoverOffCliffException extends RoverError{
  public RoverOffCliffException(Position position) {
    super("Placed rover off cliff "+ position);
  }
}
