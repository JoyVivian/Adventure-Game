package game;

import java.util.Objects;

public class Otyugh {
  private int health;

  public Otyugh() {
    this.health = 100;
  }

  public void hit() {
    if (this.health != 0) {
      this.health -= 50;
    }
  }

  public int getHealth() {
    return this.health;
  }


  @Override
  public String toString() {
    return "Otyugh";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Otyugh otyugh = (Otyugh) o;
    return health == otyugh.health;
  }

  @Override
  public int hashCode() {
    return Objects.hash(health);
  }
}
