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
}
