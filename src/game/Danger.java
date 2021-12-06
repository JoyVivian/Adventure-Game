package game;

/**
 * This Enum represents different type of danger.
 * The LESSSMELL represents there is some danger.
 * The MORESMELL represents there is very dangerous.
 * The NODANGER represents there has no danger.
 */
public enum Danger {
  LESSSMELL("Less smell"), MORESMELL("more smell"), NODANGER("no danger");

  private Danger(String name) {
    this.name = name;
  }

  private String name;

  @Override
  public String toString() {
    return "" + name;
  }
}
