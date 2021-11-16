package unionfind;

/**
 * This class is an interface that implements
 * the Uf Interface. This class is used to find
 * whether two sets are connected or not.
 */
public class UfImpl implements Uf {
  private int[] id;

  /**
   * A constructor that used to construct uf object.
   *
   * @param num The number of nodes in the union.
   */
  public UfImpl(int num) throws IllegalArgumentException {
    if (num <= 0) {
      throw new IllegalArgumentException("The number should not be less than 0.");
    }
    id = new int[num + 1];
    for (int i = 1; i <= num; i++) {
      id[i] = i;
    }
  }

  @Override
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  @Override
  public int find(int p) {
    return id[p];
  }

  @Override
  public void union(int p, int q) {
    int sourceId = find(p);
    int desId = find(q);

    if (sourceId == desId) {
      return;
    }

    for (int i = 0; i < id.length; i++) {
      if (id[i] == sourceId) {
        id[i] = desId;
      }
    }
  }
}
