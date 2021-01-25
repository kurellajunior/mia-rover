package privat.kurellajunior.rover.error;

/**
 *
 */
public class UnknowTaskException extends RoverError {
  public UnknowTaskException(char task) {
    super("Unknown task detected »"+task+"«");
  }
}
