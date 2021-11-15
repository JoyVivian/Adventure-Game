package game;

public class Otyugh {
  private int health;

  public Otyugh() {
    health = 100;
  }

  public void hit() {
    if (this.health != 0) {
      health -= 50;
    }
  }

  public int getHealth() {
    return this.health;
  }


  @Override
  public String toString() {
    return "Otyugh";
  }
}
