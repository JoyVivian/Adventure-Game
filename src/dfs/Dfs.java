package dfs;

/**
 * This interface defines methods for deep first searching on a graph.
 * This interface has methods to determine where a source node can come
 * to a specific node on a graph and also save the path from the source
 * to the specific node.
 */
public interface Dfs {
  /**
   * To determine whether a source node has a path to a specific node.
   *
   * @param v An integer that represents the specific node.
   * @return A boolean that represents whether
   *     there is a path to this specific node.
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
