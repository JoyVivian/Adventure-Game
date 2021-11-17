package game;

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
