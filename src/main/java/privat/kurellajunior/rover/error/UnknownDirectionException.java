package privat.kurellajunior.rover.error;

/**
 *
 */
public class UnknownDirectionException extends RoverError {
  private char heading;

  public UnknownDirectionException(char heading) {
    super("Unknown direction detected »"+heading+"«");
    this.heading = heading;
  }

  public char heading() {
    return heading;
  }
}
