import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.Danger;
import game.Direction;
import game.Dungeon;
import game.DungeonImpl;
import game.Location;
import game.Otyugh;
import game.Player;
import game.PlayerImpl;
import game.Treasure;
import graph.Graph;
import random.RandomFactory;
import random.RandomValue;

public class GameModelImpl implements GameModel {
  private final Dungeon dungeon;
  private final Player player;

  GameModelImpl(int rows, int cols, int connectivity,
                boolean isWrap, int treasurePer, int otyNum, boolean isRandom) {
    dungeon = new DungeonImpl(rows, cols, connectivity, isWrap, treasurePer, otyNum, isRandom);
    player = new PlayerImpl();

    //The player initially at the start cave of the Dungeon.
    player.move(dungeon.getStart());
  }

  private List<Direction> getNextDir(Location curLoc) {
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

  @Override
  public String getNextDirs() {
    String result = "";
    List<Direction> dirs = this.getNextDir(this.player.getLocation());
    for (Direction dir : dirs) {
      switch (dir) {
        case North:
          result += "N, ";
          break;
        case South:
          result += "S, ";
          break;
        case East:
          result += "E, ";
          break;
        case West:
          result += "W, ";
          break;
      }
    }
    result = result.substring(0, result.length() - 2);
    return result;
  }


  private Location getNextLoc(Direction direction, Location curLoc) {
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
        for (Otyugh otyugh : otyughs) {
          if (otyugh.getHealth() > 0) {
            otyugh.loseHealth();
            isAlive = true;
            break;
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
    Danger danger = this.dungeon.findSmell(this.player.getLocation());
    String result = "";
    switch (danger) {
      case NODANGER:
        result += "You are safe right now.\n";
        break;
      case LESSSMELL:
        result += "You smell something horrible here.\n";
        break;
      case MORESMELL:
        result += "You smell something very horrible here.\n";
    }
    return result;
  }

  @Override
  public String getTreasure() {
    String result = "";
    Location curLoc = this.player.getLocation();
    List<Treasure> treasureList = curLoc.getTreasureList();
    Map<String, Integer> treasures = new HashMap<>();
    for (Treasure treasure : treasureList) {
      if (!treasures.containsKey(treasure)) {
        treasures.put(treasure.toString(), 1);
      } else {
        treasures.put(treasure.toString(), treasures.get(treasure) + 1);
      }
    }
    for (Map.Entry<String, Integer> entry : treasures.entrySet()) {
      result += entry.getValue() + " " + entry.getKey() + ", ";
    }

    if (result != "") {
      result = result.substring(0, result.length() - 2);
    }
    return result;
  }

  @Override
  public int getArrowNum() {
    Location curLoc = this.player.getLocation();
    return curLoc.getArrows();
  }

  @Override
  public int getPlayArrowNum() {
    return this.player.getArrowNum();
  }

  @Override
  public void pickUpTreasure(Treasure treasure) {
    Location curLoc = this.player.getLocation();
    this.player.pickUp(treasure);
    curLoc.removeTreasure(treasure);
  }

  @Override
  public void pickUpArrows() {
    Location curLocation = this.player.getLocation();
    this.player.pickUpArrows(curLocation.getArrows());
    curLocation.removeArrows();
  }

  @Override
  public void decreaseArrows() {
    this.player.decreaseArrows();
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
  public boolean isEnd() {
    return this.player.getLocation() == this.dungeon.getEnd();
  }

  @Override
  public boolean isCave() {
    return this.player.getLocation().getIsCave();
  }

}
