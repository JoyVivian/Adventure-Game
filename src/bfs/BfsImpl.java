package bfs;

import graph.Graph;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


/**
 * This class is an implementation of Bfs interface.
 * This class get a graph and source node from input
 * and has the functions of determining whether there exists
 * a path to a specific node and ouput the path length
 * and path.
 */
public class BfsImpl implements Bfs {
  private boolean[] marked;
  private int[] edgeTo;
  private int[] dist;
  private final int source;

  /**
   * The constructor of Bfs and is used to construct Bfs instance.
   * This method takes in a Graph instance and an integer and invoke
   * bfs method to search the graph recursively.
   *
   * @param graph An Graph instance that represent a graph.
   * @param s     An integer that represents the source node.
   */
  public BfsImpl(Graph graph, int s) {
    marked = new boolean[graph.getVnum() + 1];
    edgeTo = new int[graph.getVnum() + 1];
    dist = new int[graph.getVnum() + 1];
    this.source = s;

    for (int i = 1; i < graph.getVnum() + 1; i++) {
      marked[i] = false;
      dist[i] = -1;
      edgeTo[i] = 0;
    }
    bfs(graph, s);
  }

  private void bfs(Graph graph, int source) throws IllegalArgumentException {
    if (graph == null) {
      throw new IllegalArgumentException("Graph could not be null.");
    }

    if (source <= 0) {
      throw new IllegalArgumentException("The id of vertex should not less than 0");
    }

    Queue<Integer> queue = new LinkedList<>();

    marked[source] = true;

    dist[source] = 0;
    queue.add(source);

    while (!queue.isEmpty()) {
      int v = queue.poll();
      for (int w : graph.getAdjacentNodes(v)) {
        if (!marked[w]) {
          edgeTo[w] = v;
          dist[w] = dist[v] + 1;
          marked[w] = true;
          queue.add(w);
        }
      }
    }
  }

  @Override
  public boolean hasPathTo(int v) throws IllegalArgumentException {
    if (v <= 0) {
      throw new IllegalArgumentException("The id of vertexes should not less than 0");
    }
    return marked[v];
  }

  @Override
  public Iterable<Integer> pathTo(int v) throws IllegalArgumentException {
    if (v <= 0) {
      throw new IllegalArgumentException("The id of vertexes should not less than 0");
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
      throw new IllegalArgumentException("The id of vertexes should not less than 0");
    }

    return dist[v];
  }
}
