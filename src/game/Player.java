package game;

/**
 * This interface represents a player that could move in the
 * Dungeon or pickup treasures in the current location. Also,
 * showing the current location of the player and what treasures
 * have been collected.
 */
public interface Player {
  /**
   * A player move to one specific location.
   *
   * @param locationImpl A location instance that the player should
   *                     move to.
   */
  void move(Location locationImpl);

  /**
   * A player pickup one treasure.
   *
   * @param treasure A Treasure instance that a player should pickup.
   */
  void pickUp(Treasure treasure);

  /**
   * Get the information about what treasures have been collected.
   *
   * @return A string contains information about what treasures have
   * been collected.
   */
  String getCollected();

  /**
   * Pickup all treasures in a Location.
   *
   * @param locationImpl The location that all of its treasure should be picked up.
   */
  void pickUpAll(Location locationImpl);

  /**
   * Get the current location the player in.
   *
   * @return A location instance that the player currently in.
   */
  Location getLocation();

  /**
   * Get the current number of arrows of this player.ß
   *
   * @return An integer that represents the number of arrows.
   */
  int getArrowNum();

  /**
   * To add or minus arrows of this player.
   *
   * @param changeNum The number that should change the arrows.
   */
  void setArrowNum(int changeNum);
}
