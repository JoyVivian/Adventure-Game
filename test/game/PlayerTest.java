package game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test whether a Player can move
 * the right location and pick up treasures.
 */
public class PlayerTest {
  private Player player;

  @Before
  public void setUp() throws Exception {
    player = new PlayerImpl();
  }

  @Test
  public void move() {
    Location expectedLocationImpl = new LocationImpl(2, 2);
    player.move(new LocationImpl(2, 2));
    assertEquals(expectedLocationImpl, player.getLocation());

    //Test North.
    Location expectedLocationImplN = new LocationImpl(1, 2);
    player.move(new LocationImpl(1, 2));
    assertEquals(expectedLocationImplN, player.getLocation());

    //Test East
    Location expectedLocationImplE = new LocationImpl(2, 3);
    player.move(new LocationImpl(2, 3));
    assertEquals(expectedLocationImplE, player.getLocation());

    //Test South
    Location expectedLocationImplS = new LocationImpl(3, 2);
    player.move(new LocationImpl(3, 2));
    assertEquals(expectedLocationImplS, player.getLocation());

    //Test West
    Location expectedLocationImplW = new LocationImpl(2, 1);
    player.move(new LocationImpl(2, 1));
    assertEquals(expectedLocationImplW, player.getLocation());
  }

  @Test
  public void pickUp() {
    Treasure treasureDia = Treasure.DIAMOND;
    Treasure treasureR = Treasure.RUBIE;
    Treasure treasureS = Treasure.SAPPHIRE;

    player.pickUp(treasureDia);
    player.pickUp(treasureR);
    player.pickUp(treasureS);

    assertEquals("DIAMOND\tRUBIE\tSAPPHIRE\t", player.getCollected());
  }

  @Test
  public void getCollected() {
    Treasure treasureDia = Treasure.DIAMOND;
    Treasure treasureR = Treasure.RUBIE;
    Treasure treasureS = Treasure.SAPPHIRE;

    player.pickUp(treasureDia);
    player.pickUp(treasureR);
    player.pickUp(treasureS);

    assertEquals("DIAMOND\tRUBIE\tSAPPHIRE\t", player.getCollected());
  }

  @Test
  public void pickUpAll() {
    Treasure treasureDia = Treasure.DIAMOND;
    Treasure treasureR = Treasure.RUBIE;
    Treasure treasureS = Treasure.SAPPHIRE;

    LocationImpl locationImplWithTreasure = new LocationImpl(1, 2);
    locationImplWithTreasure.assignTreasure(treasureDia);
    locationImplWithTreasure.assignTreasure(treasureR);
    locationImplWithTreasure.assignTreasure(treasureS);

    player.pickUpAll(locationImplWithTreasure);

    assertEquals("DIAMOND\tRUBIE\tSAPPHIRE\t", player.getCollected());
  }

  @Test
  public void getLocation() {
    LocationImpl expectedLocationImpl = new LocationImpl(2, 2);
    player.move(new LocationImpl(2, 2));
    assertEquals(expectedLocationImpl, player.getLocation());
  }
}