package privat.kurellajunior.rover.error;

import privat.kurellajunior.rover.Position;

/**
 *
 */
public class MovementOffPlateauException extends RoverError{

  public MovementOffPlateauException(Position position, char heading) {
    super("Tried to move from "+ position + heading);
  }
}
