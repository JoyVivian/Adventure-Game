package unionfind;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class is used to test whether a Union find
 * can determine the realtion of two nodes correctly.
 */
public class UfTest {
  private Uf uf;

  @Before
  public void setUp() throws Exception {
    uf = new UfImpl(25);
  }

  @Test
  public void connected() {
    assertTrue(!uf.connected(1, 2));
    uf.union(1, 2);
    assertTrue(uf.connected(1, 2));
  }

  @Test
  public void find() {
    assertEquals(1, uf.find(1));
    assertEquals(2, uf.find(2));
  }

  @Test
  public void union() {
    assertTrue(!uf.connected(1, 2));
    uf.union(1, 2);
    assertTrue(uf.connected(1, 2));
  }
}