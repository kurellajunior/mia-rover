package privat.kurellajunior.rover;

/**
 *
 */
public class UnknowTaskException extends RoverError {
  public UnknowTaskException(char task) {
    super("Unknown task detected »"+task+"«");
  }
}
