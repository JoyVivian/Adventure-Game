import java.util.List;

import game.Direction;
import game.Dungeon;
import game.Location;
import game.Player;

public interface IGameModel {
  /**
   * Get a list of directions that are adjacent to the current location.
   *
   * @return A list of Direction Enum.
   */
  List<Direction> getNextDir(Location curLoc);

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
}
