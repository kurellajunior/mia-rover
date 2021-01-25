package privat.kurellajunior.rover.error;

import privat.kurellajunior.rover.Position;

/**
 *
 */
public class CollisionException extends RoverError {
  public CollisionException(Position position, char heading) {
    super("Collision detected while moving "+ position + heading);
  }
}
