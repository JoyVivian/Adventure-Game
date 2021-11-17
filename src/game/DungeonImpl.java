package game;

import bfs.Bfs;
import bfs.BfsImpl;
import graph.Graph;
import graph.GraphImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import random.RandomFactory;
import random.RandomValue;
import unionfind.Uf;
import unionfind.UfImpl;


/**
 * This class implements the Dungeon interface.
 * The user inputs 5 arguments by command-line to construct
 * a wrapping or non-wrapping Dungeon. A wrapping Dungeon means
 * that a player can go from the border grid to the opposite border.
 * Also, a start cave and end cave will be chosen randomly. The path
 * between them should be at least 5.
 */
public class DungeonImpl implements Dungeon {
  private final int colNum;
  private final int interconnectivity;
  private final Graph graph;
  private final Uf uf;
  private int start;
  private int end;

  /**
   * The constructor of DungeonImpl get 5 arguments and
   * construct wrap and non-wrap Dungeon on a 2-D grid.
   *
   * @param rows         An integer that represents number of row.
   * @param cols         An integer that represents number of column.
   * @param connectivity An integer. The more the interconnectivity,
   *                     the more path from one node to the other.
   * @param isRandom     A boolean that represents that whether a real random value
   *                     should be generated.
   * @throws IllegalArgumentException If either of the rows, cols, connectivity, tPercentage
   *                                  is negative, it will be called.
   */
  public DungeonImpl(int rows, int cols, int connectivity,
                     boolean isWrapping, int treasurePer,
                     int otyNum, boolean isRandom) throws IllegalArgumentException {
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("The number of rows or cols should not be negative.");
    }

    if (connectivity < 0) {
      throw new IllegalArgumentException("Connectivity should not be negative.");
    }

    if (treasurePer < 0) {
      throw new IllegalArgumentException("Treasure percentage should not be negative.");
    }


    //According to the requirements, the start and end position should have a path greater than 5.
    //If the user entered rows and cols less than 5, it will be refactor to 5.
    int rowNum = rows >= 5 ? rows : 5;
    this.colNum = cols >= 5 ? cols : 5;
    this.interconnectivity = connectivity;
    boolean isWrap = isWrapping;
    int treasurePercentage = treasurePer;
    this.graph = new GraphImpl(rowNum, this.colNum);
    int vertexNum = rows * cols;
    this.uf = new UfImpl(vertexNum);

    if (!isWrap) {
      createNoWrapDun(vertexNum, isRandom);
    } else {
      createNoWrapDun(vertexNum, isRandom);
      linkWrapEdges(rowNum, colNum);
    }

    //Set isCave field after generating the graph.
    int caveNum = 0;
    for (int i = 1; i <= vertexNum; i++) {
      LocationImpl locationImpl = this.graph.getLocation(i);
      if (this.graph.getDegree(i) != 2) {
        locationImpl.setIsCave(true);
        caveNum++;
      } else {
        locationImpl.setIsCave(false);
      }
    }

    RandomFactory randomFactory = new RandomFactory();

    //Add treasures to caves.
    //Find the exact number of caves that should be assigned treasures.
    int numCaves = (int) Math.ceil(treasurePercentage / 100.0 * caveNum);
    int j = 1;
    for (int i = numCaves; i >= 1; i--) {
      Location locationImpl = this.graph.getLocation(j);
      //Find a cave.
      while (!locationImpl.getIsCave()) {
        j++;
        locationImpl = this.graph.getLocation(j);
      }

      //Assign a random treasure to the cave.
      RandomValue randomValueInsTreasure = randomFactory.createRandomInstance(isRandom, 0, 2);
      int randomIndex = randomValueInsTreasure.getRandomValue();

      Treasure treasure = Treasure.values()[randomIndex];
      locationImpl.assignTreasure(treasure);
      j++;
    }

    //Assign arrows.
    assignArrows(treasurePer, isRandom);

    RandomValue randomValueIns = randomFactory.createRandomInstance(isRandom, 1, vertexNum);
    this.start = randomValueIns.getRandomValue();
    this.end = isRandom ? randomValueIns.getRandomValue() : vertexNum;

    Bfs bfs = new BfsImpl(this.graph, this.start);
    int shortestPathLen = bfs.pathLen(this.end);

    LocationImpl startLoc = this.graph.getLocation(this.start);
    LocationImpl endLoc = this.graph.getLocation(this.end);

    if (!isRandom) {
      while (!(startLoc.getIsCave())) {
        this.start++;
        startLoc = this.graph.getLocation(this.start);
      }
    }

    while (shortestPathLen < 5 || !(startLoc.getIsCave()) || !(endLoc.getIsCave())) {
      //If it is realRandom, just generate two other random values.
      //If it is falseRandom, look up start node from the left-upper corner and
      //the end node from the right-lower corner of the grid.
      if (isRandom) {
        this.start = randomValueIns.getRandomValue();
        this.end = randomValueIns.getRandomValue();
        // If the start equals the end, the BFS will calculate wrong Path length.
        while (this.start == this.end) {
          this.start = randomValueIns.getRandomValue();
          this.end = randomValueIns.getRandomValue();
        }
      } else {
        this.end--;
        if (this.end < 1) {
          throw new IllegalArgumentException("" +
                  "This graph cannot have a path that start and end have a length 5");
        }
      }

      startLoc = this.graph.getLocation(this.start);
      endLoc = this.graph.getLocation(this.end);
      bfs = new BfsImpl(this.graph, this.start);
      shortestPathLen = bfs.pathLen(this.end);
    }

    //Add Otyughs to the Dungeon.
    addOtyughs(otyNum, isRandom);

  }

  //TODO: Add some code for test purpose.

  /**
   * To assign monsters randomly to the dungeon.
   *
   * @param otyNum   The number of Otyugh that should be assigned to the Dungeon.
   * @param isRandom To specify whether this code is for testing.
   */
  private void addOtyughs(int otyNum, boolean isRandom) {
    Location startLoc = this.graph.getLocation(this.start);
    Location endLoc = this.graph.getLocation(this.end);

    //Get all the caves of the Dungeon.
    List<Location> caveList = new ArrayList<>();
    for (int i = 1; i <= this.graph.getVnum(); i++) {
      Location location = this.graph.getLocation(i);
      if (!location.getIsCave()) {
        continue;
      } else {
        caveList.add(location);
      }
    }

    while (otyNum > 0) {
      //According to the requirement, the end position must have an Otyugh.
      endLoc.assignOtyugh();
      otyNum--;
      if (otyNum <= 0) {
        break;
      }

      //Assign the remain Otyughs randomly.
      RandomFactory randomFactory = new RandomFactory();
      RandomValue randomValueIns = randomFactory.createRandomInstance(isRandom, 0, caveList.size() - 1);
      int randomValue = randomValueIns.getRandomValue();

      Location randomCave = caveList.get(randomValue);

      if (isRandom) {
        //The start position can not hold Octyughs.
        while (randomCave.equals(startLoc)) {
          randomValue = randomValueIns.getRandomValue();

          randomCave = caveList.get(randomValue);
        }

        randomCave.assignOtyugh();
        otyNum--;
      } else {
        endLoc.assignOtyugh();
        otyNum--;
        if (otyNum <= 0) {
          break;
        }

        for (int i = randomValue; i < caveList.size(); i++) {
          randomCave = caveList.get(i);

          if (randomCave.equals(startLoc) || randomCave.equals(endLoc)) {
            continue;
          } else {
            randomCave.assignOtyugh();
            otyNum--;
            if (otyNum <= 0) {
              break;
            }
          }
        }
      }
    }
  }

  /**
   * Assign one or two arrows randomly to specific locations.
   *
   * @param arrowPer An integer represents have many percentage of locations
   *                 should be assigned arrows.
   */
  private void assignArrows(int arrowPer, boolean isRandom) {
    int numLocs = (int) Math.ceil(arrowPer / 100.0 * this.graph.getVnum());

    List<Location> visitedLocs = new ArrayList<>();
    int curId = 1;
    for (int i = numLocs; i > 0; i--) {
      RandomFactory randomFactory = new RandomFactory();
      RandomValue randomValueIns = randomFactory.createRandomInstance(isRandom, 1,
              this.graph.getVnum());
      int randomValue = randomValueIns.getRandomValue();

      Location randomLoc = this.graph.getLocation(randomValue);

      if (isRandom) {
        while (visitedLocs.contains(randomLoc)) {
          randomValue = randomValueIns.getRandomValue();
          randomLoc = this.graph.getLocation(randomValue);
        }

        visitedLocs.add(randomLoc);

        //Generate a random number one or two which represents the number of arrows
        //that should be assigned to this cave.
        randomValueIns = randomFactory.createRandomInstance(isRandom, 1, 2);
        randomValue = randomValueIns.getRandomValue();
        while (randomValue > 0) {
          randomLoc.assignArrow();
          randomValue--;
        }
      } else {
        if (curId > this.graph.getVnum()) {
          curId = 1;
        } else {
          Location location = this.graph.getLocation(curId);
          location.assignArrow();
        }
        curId++;
      }
    }
  }

  private void createEdges(int randomVertex, int anotherVertex,
                           Map<Integer, Integer> leftoverEdge)
          throws IllegalArgumentException {
    if (randomVertex <= 0 || anotherVertex <= 0 || leftoverEdge == null) {
      throw new IllegalArgumentException("Illegal Arguments");
    }

    if (!uf.connected(randomVertex, anotherVertex)) {
      uf.union(randomVertex, anotherVertex);
      graph.addEdge(randomVertex, anotherVertex);
    } else {
      //Find if there exists an edge between these two nodes.
      // If not, add them to leftoverEdge.
      List<Integer> edges = graph.getAdjacentNodes(randomVertex);

      // Find if the edge exists in the leftoverEge hashMap.
      //If not, add it.
      if (!edges.contains(anotherVertex)) {
        if (leftoverEdge.containsKey(anotherVertex)) {
          if ((int) leftoverEdge.get(anotherVertex) != randomVertex) {
            leftoverEdge.put(randomVertex, anotherVertex);
          }
        } else {
          leftoverEdge.put(randomVertex, anotherVertex);
        }
      }
    }
  }

  private void createNoWrapDun(int vertexNum, boolean isRandom)
          throws IllegalArgumentException {
    if (vertexNum <= 0) {
      throw new IllegalArgumentException("Illegal arguments.");
    }

    Map leftoverEdge = new HashMap<Integer, Integer>();
    List<Integer> visitedNodes = new ArrayList<>();


    int offset = 0;
    while (visitedNodes.size() < vertexNum) {
      RandomFactory randomFactory = new RandomFactory();
      RandomValue randomValueIns = randomFactory.createRandomInstance(isRandom, 1, vertexNum);
      int randomVertex = randomValueIns.getRandomValue();
      // If it is false random,
      // it should start from the first node to the end node by arabic order.
      if (!isRandom) {
        randomVertex += offset;
        offset++;
      }
      while (visitedNodes.contains(randomVertex)) {
        randomVertex = randomValueIns.getRandomValue();
      }
      visitedNodes.add(randomVertex);

      int x = this.getRow(randomVertex);

      if (randomVertex - colNum >= 1) {
        createEdges(randomVertex, randomVertex - colNum, (HashMap<Integer, Integer>) leftoverEdge);
      }

      if (randomVertex + 1 <= (x - 1) * colNum + colNum) {
        createEdges(randomVertex, randomVertex + 1, (HashMap<Integer, Integer>) leftoverEdge);
      }

      if (randomVertex - 1 >= (x - 1) * colNum + 1) {
        createEdges(randomVertex, randomVertex - 1, (HashMap<Integer, Integer>) leftoverEdge);
      }

      if (randomVertex + colNum <= vertexNum) {
        createEdges(randomVertex, randomVertex + colNum, (HashMap<Integer, Integer>) leftoverEdge);
      }
    }

    //Interconnectivity can not be greater than the number of leftoverEdges.
    int moreEdges = this.interconnectivity > leftoverEdge.size() ?
            leftoverEdge.size() : this.interconnectivity;
    //Add leftover edges to graph.
    Iterator it = leftoverEdge.entrySet().iterator();
    for (int i = moreEdges; i > 0; i--) {
      if (it.hasNext()) {
        Map.Entry pair = (Map.Entry) it.next();
        this.graph.addEdge((int) pair.getKey(), (int) pair.getValue());
      }
    }
  }

  private int getCol(int id) {
    return id % this.colNum == 0 ? this.colNum : id % colNum;
  }

  private int getRow(int id) {
    return (id - this.getCol(id)) / this.colNum + 1;
  }

  @Override
  public Location getStart() {
    return this.graph.getLocation(this.start);
  }

  @Override
  public Location getEnd() {
    return this.graph.getLocation(this.end);
  }

  private List<Integer> findAdjacentSpeDis(int distance, int node) {
    List<Integer> result = new ArrayList<>();
    dfs(distance, node, -1, result);
    return result;
  }

  private void dfs(int distance, int node, int parent, List<Integer> findNodes) {
    if (distance == 0) {
      findNodes.add(node);
      return;
    }

    List<Integer> tmp = this.graph.getAdjacentNodes(node);

    for (int i : tmp) {
      if (i != parent) {
        dfs(distance - 1, i, node, findNodes);
      }
    }
  }

  @Override
  public Danger findSmell(Location curLoc) {
    Danger danger = Danger.LESSSMELL;
    int curLocId = curLoc.getId(this.colNum);
    List<Integer> adjLocs = this.findAdjacentSpeDis(1, curLocId);
    for (int adjLoc : adjLocs) {
      Location location = this.graph.getLocation(adjLoc);
      List<Otyugh> otyughs = location.getOtyughs();
      int otyughNum = 0;
      for (Otyugh otyugh : otyughs) {
        if (otyugh.getHealth() == 0) {
          continue;
        } else {
          otyughNum++;
        }
      }

      if (otyughNum > 0) {
        return Danger.MORESMELL;
      }
    }

    adjLocs = this.findAdjacentSpeDis(2, curLocId);
    for (int adjLoc : adjLocs) {
      Location location = this.graph.getLocation(adjLoc);
      List<Otyugh> otyughs = location.getOtyughs();
      int otyughNum = 0;
      for (Otyugh otyugh : otyughs) {
        if (otyugh.getHealth() == 0) {
          continue;
        } else {
          otyughNum++;
        }
      }
      if (otyughNum > 1) {
        return Danger.MORESMELL;
      } else if (otyughNum == 1) {
        return Danger.LESSSMELL;
      } else {
        return Danger.NODANGER;
      }
    }

    return Danger.NODANGER;
  }

  @Override
  public Graph getGraph() {
    return this.graph;
  }

  private void linkWrapEdges(int rowNum, int colNum) {
    //Link all rows' border vertexes.
    int rowLeft = 1;
    int rowRight = colNum;

    for (int i = rowNum; i >= 1; i--) {
      this.graph.addEdge(rowLeft, rowRight);
      rowLeft += colNum;
      rowRight += colNum;
    }
    //Link all columns' border vertexes.
    int colUp = 1;
    int colDown = (rowNum - 1) * colNum + 1;

    for (int i = colNum; i >= 1; i--) {
      this.graph.addEdge(colUp, colDown);
      colUp += 1;
      colDown += 1;
    }
  }

  @Override
  public String printDungeon() {
    String locationInfo = "";
    for (int i = 1; i <= this.graph.getVnum(); i++) {
      locationInfo += this.graph.getLocation(i).toString() + "\n";
    }

    String dungeonInfo = "The graph is: " + this.graph.toString() +
            "\n" +
            "The information on this location is: \n" + locationInfo;
    return dungeonInfo;
  }
}
