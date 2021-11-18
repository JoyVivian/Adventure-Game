package game;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a implementation of Player interface
 * which can store the information about current location
 * and treasures that the player currently in and has collected.
 */
public class PlayerImpl implements Player {
  private Location locationImpl;
  private List<Treasure> treasures;
  private int arrowNum;


  /**
   * A constructor that is used to construct a Player instance.
   */
  public PlayerImpl() {
    this.locationImpl = null;
    this.treasures = new ArrayList<>();
    this.arrowNum = 3;
  }

  @Override
  public void move(Location locationImpl) throws IllegalArgumentException {
    if (locationImpl == null) {
      throw new IllegalArgumentException("Location could not be null");
    }
    this.locationImpl = locationImpl;
  }

  @Override
  public void pickUp(Treasure treasure) throws IllegalArgumentException {
    if (treasure == null) {
      throw new IllegalArgumentException("treasure should be null.");
    }
    this.treasures.add(treasure);
  }

  @Override
  public void pickUpAll(Location locationImpl) throws IllegalArgumentException {
    if (locationImpl == null) {
      throw new IllegalArgumentException("Location should not be null");
    }
    List<Treasure> treasures = locationImpl.getTreasureList();
    for (Treasure treasure : treasures) {
      this.pickUp(treasure);
    }
  }

  @Override
  public Location getLocation() {
    return this.locationImpl;
  }

  @Override
  public int getArrowNum() {
    return this.arrowNum;
  }

  @Override
  public void pickUpArrows(int pickNum) {
    this.arrowNum += pickNum;
  }

  @Override
  public void decreaseArrows() {
    this.arrowNum--;
  }

  //Consider to display this information in the driver class.
  @Override
  public String getCollected() {
    String collectedInfo = "";
    for (Treasure treasure : treasures) {
      collectedInfo += treasure.toString() + "\t";
    }
    return collectedInfo;
  }
}
