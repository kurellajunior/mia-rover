package privat.kurellajunior.rover;

import privat.kurellajunior.rover.error.UnknownDirectionException;

import java.util.Objects;

/**
 *
 */
public class Position {
  private final int x;
  private final int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int x() {
    return x;
  }

  public int y() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return x == position.x && y == position.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "[" + x + "," + y + ']';
  }

  public Position update(char heading) throws UnknownDirectionException {
    switch (heading) {
      case 'N':
        return new Position(x, y + 1);
      case 'E':
        return new Position(x + 1, y);
      case 'S':
        return new Position(x, y - 1);
      case 'W':
        return new Position(x - 1, y);
      default:
        throw new UnknownDirectionException(heading);
    }
  }
}
