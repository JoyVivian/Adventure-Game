package game;

import graph.Graph;

/**
 * This is an interface of Dungeon.
 * This interface can construct a wrapping
 * or non wrapping Dungeon on a 2-D grid
 * A wrapping Dungeon is a Dungeon that can go from
 * the borders to the opposite borders while a non-wrapping
 * Dungeon cannot. Also, this interface provides functions
 * to output information of Dungeon.
 */
public interface Dungeon {
  /**
   * Print the structure of a Dungeon.
   *
   * @return A string contains the information of the dungeon.
   */
  String printDungeon();

  /**
   * Get the graph instance in the dungeon.
   *
   * @return A Graph instance in the Dungeon.
   */
  Graph getGraph();

  /**
   * Get the source node of the Dungeon. The Source
   * Node and the End Node are selected randomly. With
   * a path length at least of 5.
   *
   * @return A Location instance that represents the start Location.
   */
  Location getStart();

  /**
   * Get the end node of the Dungeon. The Source
   * Node and the End Node are selected randomly. With
   * a path length at least of 5.
   *
   * @return A location instance that represents the end location.
   */
  Location getEnd();

  /**
   * Find what type of smell that can be detect from the current location.
   *
   * @param curLoc The current location of the player.
   * @return A Danger Enum that represents the type of danger.
   */
  Danger findSmell(Location curLoc);

  /**
   * Find whether a Dungeon is wrapped or not.
   *
   * @return A boolean represents a dungeon is wrapped or not.
   */
  Boolean isWrapped();
}
