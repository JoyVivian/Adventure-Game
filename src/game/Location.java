package game;

import java.util.List;

/**
 * This interface represents the node of a Dungeon
 * that can store the x, y coordinates and treasures.
 */
public interface Location {
  /**
   * Assign one treasure to this location.
   *
   * @param treasure A treasure instance
   *                 that should be assign to this location.
   */
  void assignTreasure(Treasure treasure);

  /**
   * Assign one Otyugh to the Dungeon.
   */
  void assignOtyugh();

  /**
   * Assign one arrow to the Dungeon.
   */
  void assignArrow();

  /**
   * Set if this location is a cave.
   *
   * @param isCave A boolean that represents whether this location is a cave.
   */
  void setIsCave(boolean isCave);

  /**
   * Get the information that whether this location is a cave.
   *
   * @return A boolean represents whether this location is a cave.
   */
  boolean getIsCave();

  /**
   * Use function to reverse a 2-D coordinates to 1-D integer.
   *
   * @param colNum The number of column in the Dungeon.
   * @return An integer that converted by a 2-D coordinates.
   */
  int getId(int colNum);

  /**
   * Get the x coordinate of the location.
   *
   * @return The x coordinate of the location.
   */
  int getCol();

  /**
   * Get the y coordinate of the location.
   *
   * @return The y coordinate of the location.
   */
  int getRow();

  /**
   * Get the list of treasures that store in the location.
   *
   * @return The list of treasures that store in the location.
   */
  List<Treasure> getTreasureList();

  /**
   * Get the list of Otyughs that store in the location.
   *
   * @return The list of treasures that store in the location.
   */
  List<Otyugh> getOtyughs();

  /**
   * Get the number of arrows in this location.
   *
   * @return An integer represents the number of the arrows in the location.
   */
  int getArrows();

  /**
   * When a player picks up a treasure. The treasure should be removed from the current location.
   * This method is used to remove the treasure from the current location.
   *
   * @param treasure The treasure that should be removed from the current location.
   */
  void removeTreasure(Treasure treasure);

  /**
   * When a player picks up arrows. The arrows should be removed from the current location.
   * This method is used to remove the arrows from the current location.
   */
  void removeArrows();
}
