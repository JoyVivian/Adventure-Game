package unionfind;

/**
 * This interface is used to find whether two
 * sets are united ot not. If not, union them.
 */
public interface Uf {
  /**
   * Find whether two sets are connected or not.
   *
   * @param p The first node.
   * @param q The second node.
   * @return A boolean that represents whether the nodes connected or not.
   */
  boolean connected(int p, int q);

  /*
   * Find the root of one set.
   *
   * @param p The node that should find the root.
   * @return An integer that represents the root of the node.
   */
  int find(int p);

  /**
   * Union two nodes.
   *
   * @param p The first node.
   * @param q The second node.
   */
  void union(int p, int q);
}
