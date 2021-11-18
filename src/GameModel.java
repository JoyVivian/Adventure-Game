import java.util.ArrayList;
import java.util.List;

import game.Direction;
import game.Dungeon;
import game.DungeonImpl;
import game.Location;
import game.Otyugh;
import game.Player;
import game.PlayerImpl;
import graph.Graph;
import random.RandomFactory;
import random.RandomValue;

public class GameModel implements IGameModel {
  private final Dungeon dungeon;
  private final Player player;

  GameModel(int rows, int cols, int connectivity,
            boolean isWrap, int treasurePer, int otyNum, boolean isRandom) {
    dungeon = new DungeonImpl(rows, cols, connectivity, isWrap, treasurePer, otyNum, isRandom);
    player = new PlayerImpl();

    //The player initially at the start cave of the Dungeon.
    player.move(dungeon.getStart());
  }

  @Override
  public List<Direction> getNextDir(Location curLoc) {
    Graph graph = this.dungeon.getGraph();
    List<Direction> directions = new ArrayList<>();

    //When meet this requirements, a player can move to North.
    List<Location> adjLocations = graph.getAdjacentLocations(curLoc);
    for (Location adjLoc : adjLocations) {
      //When meet this requirements, a player can move to North.
      if ((adjLoc.getCol() == curLoc.getCol()
              && adjLoc.getRow() == curLoc.getRow() - 1)
              || (curLoc.getRow() == 1
              && adjLoc.getRow() == graph.getRowNum())) {
        directions.add(Direction.North);
      }

      //When meet this requirements, a player can move to East.
      if ((adjLoc.getRow() == curLoc.getRow()
              && adjLoc.getCol() == curLoc.getCol() + 1)
              || (curLoc.getCol() == graph.getColNum()
              && adjLoc.getCol() == 1)) {
        directions.add(Direction.East);
      }

      //When meet this requiremtns, a player can move to South.
      if ((adjLoc.getCol() == curLoc.getCol()
              && adjLoc.getRow() == curLoc.getRow() + 1)
              || (curLoc.getRow() == graph.getRowNum()
              && adjLoc.getRow() == 1)) {
        directions.add(Direction.South);
      }

      //When meet this requiremnets, a player can move to West.
      if ((adjLoc.getRow() == curLoc.getRow()
              && adjLoc.getCol() == curLoc.getCol() - 1)
              || (curLoc.getCol() == 1
              && adjLoc.getCol() == graph.getColNum())) {
        directions.add(Direction.West);
      }
    }

    return directions;
  }

  private Location getNextLoc(Direction direction, Location curLoc) {
    //validate if the player can move in this direction.
    List<Direction> nextDir = this.getNextDir(curLoc);
    if (!nextDir.contains(direction)) {
      //TODO: Output the prompt that the player cannot move in
      // this direction. And provide another attempt for the user
      // to reenter the direction.
      //Is this the responsibility of controller or model?
      // Preferred controller.
    }

    int curCol = curLoc.getCol();
    int curRow = curLoc.getRow();

    int totalCol = dungeon.getGraph().getRowNum();
    int totalRow = dungeon.getGraph().getColNum();

    switch (direction) {
      case North:
        curRow = curRow - 1 == 0 ? totalRow : --curRow;
        break;
      case East:
        curCol = curCol + 1 > totalCol ? 1 : ++curCol;
        break;
      case South:
        curRow = curRow + 1 > totalRow ? 1 : ++curRow;
        break;
      case West:
        curCol = curCol - 1 == 0 ? totalCol : --curCol;
        break;
    }

    int id = getId(curCol, curRow, totalCol);
    Location des = dungeon.getGraph().getLocation(id);
    return des;
  }

  @Override
  public void move(Direction direction) {
    Location desLoc = this.getNextLoc(direction, player.getLocation());
    player.move(desLoc);
  }

  @Override
  public Location getCurLocation() {
    return player.getLocation();
  }


  @Override
  public boolean slayOty(Direction direction, int distance) {
    return this.slayOtyRecur(direction, distance, this.player.getLocation());
  }

  private boolean slayOtyRecur(Direction direction, int distance, Location curLoc) {
    if (distance == 0) {
      List<Otyugh> otyughs = curLoc.getOtyughs();
      if (otyughs.size() == 0) {
        return false;
      } else {
        boolean isAlive = false;
        //TODO: Kill the Otyugh have half blood firstly.
        for (Otyugh otyugh : otyughs) {
          if (otyugh.getHealth() > 0) {
            otyugh.loseHealth();
            isAlive = true;
          }
        }
        return isAlive;
      }
    } else {
      if (!curLoc.getIsCave()) {
        List<Direction> dirs = this.getNextDir(curLoc);
        Location nextLoc;
        Direction nextDir;
        if (dirs.contains(direction)) {
          nextLoc = this.getNextLoc(direction, curLoc);
        } else {
          Direction oppositeDir = Direction.South;
          switch (direction) {
            case North:
              oppositeDir = Direction.South;
              break;
            case East:
              oppositeDir = Direction.West;
              break;
            case South:
              oppositeDir = Direction.North;
              break;
            case West:
              oppositeDir = Direction.East;
              break;
          }
          nextDir = oppositeDir == dirs.get(0) ? dirs.get(1) : dirs.get(0);
          direction = nextDir;
          nextLoc = this.getNextLoc(nextDir, curLoc);
        }
        if (nextLoc.getIsCave()) {
          return slayOtyRecur(direction, distance - 1, nextLoc);
        } else {
          return slayOtyRecur(direction, distance, nextLoc);
        }
      } else {
        List<Direction> dirs = this.getNextDir(curLoc);
        if (dirs.contains(direction)) {
          Location nextLoc = this.getNextLoc(direction, curLoc);
          //If next place is a cave the distance should minus one.
          //Otherwise, the distance should not change.
          if (nextLoc.getIsCave()) {
            return this.slayOtyRecur(direction, distance - 1, nextLoc);
          } else {
            return this.slayOtyRecur(direction, distance, nextLoc);
          }
        } else {
          return false;
        }
      }
    }
  }

  private int getId(int curCol, int curRow, int totalCol) {
    return (curRow - 1) * totalCol + curCol;
  }

  @Override
  public Dungeon getDungeon() {
    return dungeon;
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public String getDangerType() {
    //TODO: Add more specific description.
    return this.dungeon.findSmell(this.player.getLocation()).toString();
  }

  @Override
  public boolean isEaten(boolean isRandom) {
    List<Otyugh> otyughs = this.player.getLocation().getOtyughs();
    boolean isEat = false;
    int halfNum = 0;

    for (Otyugh otyugh : otyughs) {
      if (otyugh.getHealth() == 0) {
        continue;
      } else if (otyugh.getHealth() == 50) {
        if (halfNum > 0) {
          return false;
        } else {
          RandomFactory randomFactory = new RandomFactory();
          RandomValue randomValueIns = randomFactory.createRandomInstance(isRandom, 0, 1);
          int result = randomValueIns.getRandomValue();

          if (result == 0) {
            return false;
          } else {
            halfNum++;
            return true;
          }
        }
      } else {
        isEat = true;
      }
    }

    return isEat;
  }

  @Override
  public boolean isWin() {
    Location endLoc = this.dungeon.getEnd();
    return (this.player.getLocation() == endLoc && isEaten(true));
  }
}
