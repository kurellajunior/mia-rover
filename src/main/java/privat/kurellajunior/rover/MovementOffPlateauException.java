package privat.kurellajunior.rover;

/**
 *
 */
public class MovementOffPlateauException extends RoverError{

  public MovementOffPlateauException(Position position, char heading) {
    super("Tried to move from "+ position + heading);
  }
}
