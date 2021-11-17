import dfs.Dfs;
import dfs.DfsImpl;
import game.Direction;
import game.Dungeon;
import game.DungeonImpl;
import game.Location;
import game.LocationImpl;
import game.Player;
import game.PlayerImpl;
import graph.Graph;
import java.util.List;

/**
 * This is a Driver class that shows how the model works.
 */
public class Driver {
  /**
   * The main function that shows how player and dungeon work.
   *
   * @param args 5 command-line arguments. 0: The number of row.
   *             1: The number of column. 2: The connectivity of the
   *             dungeon. 3: whether the dungeon wrapped. 4: how many
   *             caves should be assigned treasures.
   */
  public static void main(String[] args) {
    int rowNum = 0;
    int colNum = 0;
    int interconnectivity = 0;
    boolean isWrap = true;
    int percentage = 0;
    int otyNum = 0;

    System.out.println("Please input the rowNum, colNum, " +
            "Interconnectivity, isWrap, percentage of treasure." +
            "Be sure to enter both rowNum and colNum greater than 5.");
    if (args.length > 0) {
      try {
        rowNum = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[0] + " must be an integer.");
        System.exit(1);
      }

      try {
        colNum = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[1] + " must be an integer.");
        System.exit(1);
      }

      try {
        interconnectivity = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[2] + " must be an integer.");
        System.exit(1);
      }

      try {
        isWrap = Boolean.parseBoolean(args[3]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[3] + " must be an integer.");
        System.exit(1);
      }

      try {
        percentage = Integer.parseInt(args[4]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[4] + " must be an integer.");
        System.exit(1);
      }

      try {
        otyNum = Integer.parseInt(args[5]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[5] + "must be an integer.");
        System.exit(1);
      }
    }

    Dungeon dungeon = new DungeonImpl(rowNum, colNum, interconnectivity, isWrap, percentage, otyNum, true);
    System.out.println(dungeon.printDungeon());

    Player player = new PlayerImpl();
    System.out.println("The player stand at the start and doesn't pick anything.");
    System.out.println(player.getCollected());

    System.out.println("The start node of the dungeon is: " + dungeon.getStart() + "\n");
    System.out.println("The end node of the dungeon is: " + dungeon.getEnd() + "\n");

    //Player enter the start location and show info about the path.
    System.out.println("Location info to the end: \n");
    player.move(dungeon.getStart());
    player.pickUpAll(dungeon.getStart());
    printCurInfo(dungeon, dungeon.getStart());
    Dfs dfs = new DfsImpl(dungeon.getGraph(), dungeon.getStart().getId(colNum));
    for (int w : dfs.pathTo(dungeon.getEnd().getId(colNum))) {
      LocationImpl locationImpl = dungeon.getGraph().getLocation(w);
      player.move(locationImpl);
      player.pickUpAll(locationImpl);
      printCurInfo(dungeon, locationImpl);
    }
    System.out.println("The player comes to the end " +
            "and picked everything on the road. There is what he picks: \n");
    System.out.println(player.getCollected());
  }

  private static void printCurInfo(Dungeon dungeon, Location locationImpl) {
    //current location info.
    Graph graph = dungeon.getGraph();
    String directionInfo = "The player can move to: ";
    List<Location> adjLocationImpls = graph.getAdjacentLocations(locationImpl);
    for (Location adjLocationImpl : adjLocationImpls) {
      //When meet this requirements, a player can move to North.
      if ((adjLocationImpl.getCol() == locationImpl.getCol()
              && adjLocationImpl.getRow() == locationImpl.getRow() - 1)
              || (locationImpl.getRow() == 1
              && adjLocationImpl.getRow() == graph.getRowNum())) {
        directionInfo += Direction.North.toString() + ", ";
      }

      //When meet this requirements, a player can move to East.
      if ((adjLocationImpl.getRow() == locationImpl.getRow()
              && adjLocationImpl.getCol() == locationImpl.getCol() + 1)
              || (locationImpl.getCol() == graph.getColNum()
              && adjLocationImpl.getCol() == 1)) {
        directionInfo += Direction.East.toString() + ", ";
      }

      //When meet this requiremtns, a player can move to South.
      if ((adjLocationImpl.getCol() == locationImpl.getCol()
              && adjLocationImpl.getRow() == locationImpl.getRow() + 1)
              || (locationImpl.getRow() == graph.getRowNum()
              && adjLocationImpl.getRow() == 1)) {
        directionInfo += Direction.South.toString() + ", ";
      }

      //When meet this requiremnets, a player can move to West.
      if ((adjLocationImpl.getRow() == locationImpl.getRow()
              && adjLocationImpl.getCol() == locationImpl.getCol() - 1)
              || (locationImpl.getCol() == 1
              && adjLocationImpl.getCol() == graph.getColNum())) {
        directionInfo += Direction.West.toString() + ", ";
      }
    }
    System.out.println(directionInfo + "\n" +
            "The current location info: " +
            locationImpl.toString());
  }
}
