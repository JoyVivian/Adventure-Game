package graph;

import game.Location;
import game.LocationImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of Graph interface that
 * using an adjacent list to store nodes.
 */
public class GraphImpl implements Graph {
  private final int vertexNum;
  private int edgeNum;
  private Map<Integer, List<Integer>> adjacentList;
  private Map<Integer, LocationImpl> locations;
  private int rowNum;
  private int colNum;

  /**
   * A constructor of GraphImpl that is used to
   * construct graph instance.
   *
   * @param rowNum The number of rows.
   * @param colNum The number of columns.
   */
  public GraphImpl(int rowNum, int colNum) throws IllegalArgumentException {
    if (rowNum <= 0 || colNum <= 0) {
      throw new IllegalArgumentException("colNum or rowNum should not be less than 0");
    }

    this.rowNum = rowNum;
    this.colNum = colNum;
    this.vertexNum = this.rowNum * this.colNum;
    this.edgeNum = 0;

    adjacentList = new HashMap<>();
    locations = new HashMap<>();

    for (int i = 1; i <= vertexNum; i++) {
      adjacentList.put(i, new LinkedList<>());
    }
  }

  @Override
  public int getVnum() {
    return vertexNum;
  }

  @Override
  public int getRowNum() {
    return this.rowNum;
  }

  @Override
  public int getColNum() {
    return this.colNum;
  }

  @Override
  public void addEdge(int source, int des) throws IllegalArgumentException {
    if (source <= 0 || des <= 0) {
      throw new IllegalArgumentException("source and des should not be less than 0.");
    }
    if (source > adjacentList.size()
            || des > adjacentList.size()) {
      throw new IllegalArgumentException("The vertex entered in is not present.");
    }

    List<Integer> sourceList = adjacentList.get(source);
    sourceList.add(des);

    List<Integer> desList = adjacentList.get(des);
    desList.add(source);

    if (!this.locations.containsKey(source)) {
      int sourceX = this.getRow(source);
      int sourceY = this.getCol(source);
      LocationImpl sourceLoc = new LocationImpl(sourceX, sourceY);
      this.locations.put(source, sourceLoc);
    }

    if (!this.locations.containsKey(des)) {
      int desX = this.getRow(des);
      int desY = this.getCol(des);
      LocationImpl desLoc = new LocationImpl(desX, desY);
      this.locations.put(des, desLoc);
    }

    edgeNum++;
  }

  @Override
  public List<LocationImpl> getAdjacentLocations(Location v) throws IllegalArgumentException {
    if (v == null) {
      throw new IllegalArgumentException("Location should not be null");
    }
    List<LocationImpl> adjacentLocationImpls = new ArrayList<>();
    List<Integer> adjacentNodes = this.getAdjacentNodes(v.getId(colNum));
    for (int w : adjacentNodes) {
      adjacentLocationImpls.add(this.getLocation(w));
    }
    return adjacentLocationImpls;
  }

  @Override
  public List<Integer> getAdjacentNodes(int v) throws IllegalArgumentException {
    if (v <= 0) {
      throw new IllegalArgumentException("The id of vertex should not be less than 0.");
    }
    if (v > adjacentList.size()) {
      throw new IllegalArgumentException("The vertex entered is in not present.");
    }
    return adjacentList.get(v);
  }

  @Override
  public int getDegree(int v) throws IllegalArgumentException {
    if (v <= 0) {
      throw new IllegalArgumentException("The id of vertex should not be less than 0.");
    }
    if (v > adjacentList.size()) {
      throw new IllegalArgumentException("The vertex entered is in not present.");
    }

    int degree = 0;
    for (int ignored : adjacentList.get(v)) {
      degree++;
    }
    return degree;
  }

  @Override
  public LocationImpl getLocation(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("The id of vertex should not be less than 0.");
    }
    return this.locations.get(id);
  }


  @Override
  public String toString() {
    return "GraphImpl{" +
            "vertexNum=" + this.vertexNum +
            ", edgeNum=" + this.edgeNum +
            "}\n" +
            "adjacentList=" +
            adjacentList +
            '}';
  }

  private int getCol(int id) {
    return id % colNum == 0 ? colNum : id % colNum;
  }

  private int getRow(int id) {
    return (id - this.getCol(id)) / colNum + 1;
  }
}
