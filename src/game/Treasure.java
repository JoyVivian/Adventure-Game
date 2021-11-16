package game;

/**
 * An Enum that encodes all treasure type.
 */
public enum Treasure {
  DIAMOND("Diamond"), RUBIE("Rubie"), SAPPHIRE("Sapphire");

  private Treasure(String name) {
    this.name = name;
  }

  private String name;

  @Override
  public String toString() {
    return "" + name;
  }
}
