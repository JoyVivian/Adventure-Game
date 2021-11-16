package random;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * This class is used to find whether the realRandom or falseRandom can work correctly.
 */
public class RandomValueTest {
  private RandomValue realRandomValue1;
  private RandomValue realRandomValue2;
  private RandomValue falseRandomValue1;
  private RandomValue falseRandomValue2;

  @Before
  public void setUp() throws Exception {
    realRandomValue1 = new RealRandom(1, 5);
    realRandomValue2 = new RealRandom(50, 1000);
    falseRandomValue1 = new FalseRandom(3, 4);
    falseRandomValue2 = new FalseRandom(444, 9999);
  }

  @Test
  public void getRandomValue() {
    assertTrue(realRandomValue1.getRandomValue() >= 1
            && realRandomValue1.getRandomValue() <= 5);

    assertTrue(realRandomValue2.getRandomValue() >= 50
            && realRandomValue2.getRandomValue() <= 1000);

    assertTrue(falseRandomValue1.getRandomValue() >= 3
            && falseRandomValue1.getRandomValue() <= 4);

    assertTrue(falseRandomValue2.getRandomValue() >= 444
            && falseRandomValue2.getRandomValue() <= 9999);
  }
}