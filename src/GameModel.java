import java.util.List;

import game.Direction;
import game.Dungeon;
import game.Location;
import game.Player;
import game.Treasure;

public interface GameModel {
  /**
   * Get a list of directions that are adjacent to the current location.
   *
   * @return A list of Direction Enum.
   */

  String getNextDirs();

  /**
   * The user provide a direction and the player should move
   * to that location.
   *
   * @param direction The loaction specified by the user.
   */
  void move(Direction direction);

  Location getCurLocation();

  boolean slayOty(Direction direction, int distance);

  Dungeon getDungeon();

  Player getPlayer();

  String getDangerType();

  String getTreasure();

  int getArrowNum();

  int getPlayArrowNum();

  void pickUpTreasure(Treasure treasure);

  void pickUpArrows();

  void decreaseArrows();

  boolean isEaten(boolean isRandom);

  /**
   * To decide whether a player has come to the end location.
   *
   * @return A boolean represents whether a player is in the end location.
   */
  boolean isEnd();

  boolean isCave();
}
