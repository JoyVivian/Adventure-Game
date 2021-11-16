package graph;

import game.Location;
import game.LocationImpl;
import java.util.List;



/**
 * A graph instance that represents a graph using adjacent list.
 * Also, stores the location information.
 */
public interface Graph {
  /**
   * Get the number of vertexes.
   *
   * @return The number of the vertexes.
   */
  int getVnum();

  /**
   * Get the number of rows.
   *
   * @return The number of rows.
   */
  int getRowNum();

  /**
   * Get the number of columns.
   *
   * @return The number of columns.
   */
  int getColNum();

  /**
   * Add an edge to the graph.
   *
   * @param source The source of the edge.
   * @param des    The destination of the edge.
   */
  void addEdge(int source, int des);

  /**
   * Get the adjacent nodes of one node.
   *
   * @param v The node that should get the adjacent nodes.
   * @return A list contains the adjacent nodes of the specific node.
   */
  List<Integer> getAdjacentNodes(int v);

  /**
   * Get the adjacent locations of one node.
   *
   * @param v The node that should get the adjacent nodes.
   * @return A list contains the adjacent locations of the specific node.
   */
  List<LocationImpl> getAdjacentLocations(Location v);

  /**
   * Get how many other nodes are connected to this node.
   *
   * @param v The node that wants to know how many nodes are connected to it.
   * @return An integer that represents how many nodes connect to the specific node.
   */
  int getDegree(int v);

  @Override
  String toString();

  /**
   * Get the Location of one specific node.
   *
   * @param id An integer that represents the specific node.
   * @return A Location instance that represents
   *     the location of this specific node.
   */
  LocationImpl getLocation(int id);
}
