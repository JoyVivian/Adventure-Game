package random;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test whether a randomFactory can generate
 * the right random instance.
 */
public class RandomFactoryTest {

  private RandomFactory randomFactory;

  @Before
  public void setUp() throws Exception {
    randomFactory = new RandomFactory();
  }

  @Test
  public void createRandomInstance() {
    assertEquals(new RealRandom(0, 6),
            randomFactory.createRandomInstance(true, 0, 6));
    assertEquals(new FalseRandom(1, 7),
            randomFactory.createRandomInstance(false, 1, 7));
  }
}