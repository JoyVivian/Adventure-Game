import game.Dungeon;
import game.DungeonImpl;
import game.Player;
import game.PlayerImpl;

public class GameModel implements IGameModel {
  private final Dungeon dungeon;
  private final Player player;

  GameModel(int rows, int cols, int connectivity,
            boolean isWrap, int treasurePer, int otyNum, boolean isRandom) {
    dungeon = new DungeonImpl(rows, cols, connectivity, isWrap, treasurePer, otyNum, isRandom);
    player = new PlayerImpl();
  }

}
