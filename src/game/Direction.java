package game;

/**
 * An Enum to encode Direction information
 * 0 will be encoded to North; 1 Will be encoded to East;
 * 2 will be encoded to South; 3 will be encoded to West.
 */
public enum Direction {
  North("N"), East("E"), South("S"), West("W");

  private Direction(String name) {
    this.name = name;
  }

  private String name;

  @Override
  public String toString() {
    return "" + name;
  }
}
