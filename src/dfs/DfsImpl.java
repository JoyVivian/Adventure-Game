package dfs;

import graph.Graph;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class is an implementation of Dfs interface.
 * This class get a graph and source node from input
 * and has the functions of determining whether there exists
 * a path to a specific node and ouput the path length
 * and path.
 */
public class DfsImpl implements Dfs {
  private boolean[] marked;
  private int[] edgeTo;
  private final int source;
  private final Graph graph;

  /**
   * The constructor of Dfs and is used to construct Dfs instance.
   * This method takes in a Graph instance and an integer and invoke
   * dfs method to search the graph recursively.
   *
   * @param graph An Graph instance that represent a graph.
   * @param s     An integer that represents the source node.
   */
  public DfsImpl(Graph graph, int s) throws IllegalArgumentException {
    if (graph == null) {
      throw new IllegalArgumentException("Graph should not be null");
    }

    if (s <= 0) {
      throw new IllegalArgumentException("The id of vertex should not be less than 0");
    }

    this.marked = new boolean[graph.getVnum() + 1];
    this.edgeTo = new int[graph.getVnum() + 1];
    this.source = s;
    this.graph = graph;
    dfs(this.source);
  }

  private void dfs(int v) {
    marked[v] = true;

    for (int w : this.graph.getAdjacentNodes(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(w);
      }
    }
  }

  @Override
  public boolean hasPathTo(int v) throws IllegalArgumentException {
    if (v <= 0) {
      throw new IllegalArgumentException("The id of vertex should not be less than 0");
    }
    return marked[v];
  }

  @Override
  public Iterable<Integer> pathTo(int v) throws IllegalArgumentException {
    if (v <= 0) {
      throw new IllegalArgumentException("The id of vertex should not be less than 0");
    }

    if (!hasPathTo(v)) {
      return null;
    }

    Deque<Integer> path = new ArrayDeque<>();
    for (int x = v; x != this.source; x = edgeTo[x]) {
      path.push(x);
    }
    return path;
  }

  @Override
  public int pathLen(int v) throws IllegalArgumentException {
    if (v <= 0) {
      throw new IllegalArgumentException("The id of vertex should not be less than 0");
    }

    int count = 0;
    for (int w : this.pathTo(v)) {
      count++;
    }
    return count;
  }
}
