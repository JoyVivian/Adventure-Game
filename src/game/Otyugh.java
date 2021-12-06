package game;

import java.util.Objects;

/**
 * This class represents an Otyugh. An Otyugh has health of
 * 100 initially but can be shot by an arrow. Two shoots will
 * kill one Otyugh.
 */
public class Otyugh {
  private int health;

  /**
   * This is the constructor of Otyugh to initialize the
   * health of one Otyugh's health to 100.
   */
  public Otyugh() {
    this.health = 100;
  }

  /**
   * Get the health value of the Otyugh.
   *
   * @return An integer represents the health value of the Otyugh.
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * When hit by an arrow, an otyugh will lose 50 points of health.
   * This method is used to minus the health value of it.
   */
  public void loseHealth() {
    this.health -= 50;
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
