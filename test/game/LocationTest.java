package game;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class is used to test the Location class.
 * Whether it can store the x, y coordinates and treasures
 * properly.
 */
public class LocationTest {
  private Location location;

  @Before
  public void setUp() throws Exception {
    location = new LocationImpl(1, 1);
  }

  @Test
  public void assignTreasure() {
    Treasure treasure1 = Treasure.DIAMOND;
    Treasure treasure2 = Treasure.RUBIE;
    Treasure treasure3 = Treasure.SAPPHIRE;

    List<Treasure> treasureList = new ArrayList<>();
    treasureList.add(treasure1);
    treasureList.add(treasure2);
    treasureList.add(treasure3);

    Location location = new LocationImpl(1, 1);
    location.assignTreasure(treasure1);
    location.assignTreasure(treasure2);
    location.assignTreasure(treasure3);

    assertEquals(treasureList, location.getTreasureList());
  }

  @Test
  public void setIsCave() {
    location.setIsCave(true);
    assertTrue(location.getIsCave());
  }

  @Test
  public void getIsCave() {
    location.setIsCave(true);
    assertTrue(location.getIsCave());
    location.setIsCave(false);
    assertTrue(!location.getIsCave());
  }

  @Test
  public void getId() {
    assertEquals(1, location.getId(3));
    location = new LocationImpl(3, 3);
    assertEquals(13, location.getId(5));
  }

  @Test
  public void getCol() {
    assertEquals(1, location.getCol());
  }

  @Test
  public void getRow() {
    assertEquals(1, location.getRow());
  }

  @Test
  public void getTreasureList() {
    Treasure treasure1 = Treasure.DIAMOND;
    Treasure treasure2 = Treasure.RUBIE;
    Treasure treasure3 = Treasure.SAPPHIRE;

    List<Treasure> treasureList = new ArrayList<>();
    treasureList.add(treasure1);
    treasureList.add(treasure2);
    treasureList.add(treasure3);

    Location location = new LocationImpl(1, 1);
    location.assignTreasure(treasure1);
    location.assignTreasure(treasure2);
    location.assignTreasure(treasure3);

    assertEquals(treasureList, location.getTreasureList());
  }

  @Test
  public void assignOtyugh() {
    location.assignOtyugh();
    List<Otyugh> expected = new ArrayList<>();

    Otyugh otyugh1 = new Otyugh();
    expected.add(otyugh1);

    assertEquals(expected, location.getOtyughs());

    location.assignOtyugh();

    Otyugh otyugh2 = new Otyugh();
    expected.add(otyugh2);
    assertEquals(expected, location.getOtyughs());
  }

  @Test
  public void assignArrow() {
    assertEquals(0, location.getArrows());

    location.assignArrow();
    location.assignArrow();

    assertEquals(2, location.getArrows());
  }
}