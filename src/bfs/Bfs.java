package bfs;

/**
 * This interface is used to find the
 * shortest path from the source node to the end node
 * using breath first searching method.
 */
public interface Bfs {
  /**
   * To determine whether a source node has a path to a specific node.
   *
   * @param v An integer that represents the specific node.
   * @return A boolean that represents whether
   */
  boolean hasPathTo(int v);

  /**
   * Return an Iterable that iterates the path from
   * the source node to the specific node.
   *
   * @param v An integer that represents the specific node.
   * @return An iterable that could iterate the path from
   *     the source node to the specific node.
   */
  Iterable<Integer> pathTo(int v);

  /**
   * Find the length of the path from the source node to
   * one specific node.
   *
   * @param v An integer that represents the specific node.
   * @return An integer that represents the length of
   *     the path from the source node to the specific node.
   */
  int pathLen(int v);
}
