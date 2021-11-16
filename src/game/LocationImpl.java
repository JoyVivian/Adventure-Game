package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represent a node on the Dungeon.
 * It has two fields represent the number of
 * columns and rows in the Dungeon. It also keeps
 * a list of treasures that stores in this location.
 */
public class LocationImpl implements Location {
  private int col;
  private int row;
  private boolean isCave;
  private List<Treasure> treasureList;
  private List<Otyugh> otyughList;
  private int arrowNum;

  /**
   * The constructor of the Location class that takes in two argument.
   *
   * @param x An integer that represents the x coordinate of this Dungeon.
   * @param y An integer that represents the y coordinates of this Dungeon.
   */
  public LocationImpl(int x, int y) throws IllegalArgumentException {
    if (x <= 0 || y <= 0) {
      throw new IllegalArgumentException("x, y coordinates should not be negative.");
    }

    this.row = x;
    this.col = y;
    this.treasureList = new ArrayList<>();
    this.otyughList = new ArrayList<>();
    this.arrowNum = 0;
  }

  @Override
  public void assignTreasure(Treasure treasure) throws IllegalArgumentException {
    if (treasure == null) {
      throw new IllegalArgumentException("Illegal arguments.");
    }

    treasureList.add(treasure);
  }

  @Override
  public void assignOtyugh() {
    Otyugh otyugh = new Otyugh();
    otyughList.add(otyugh);
  }

  @Override
  public void assignArrow() {
    this.arrowNum++;
  }

  @Override
  public void setIsCave(boolean isCave) {
    this.isCave = isCave;
  }

  @Override
  public boolean getIsCave() {
    return this.isCave;
  }

  @Override
  public int getId(int colNum) throws IllegalArgumentException {
    if (colNum <= 0) {
      throw new IllegalArgumentException("Illegal arguments.");
    }
    return (this.row - 1) * colNum + this.col;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public List<Treasure> getTreasureList() {
    return this.treasureList;
  }

  @Override
  public List<Otyugh> getOtyughs() {
    return this.otyughList;
  }

  @Override
  public int getArrows() {
    return this.arrowNum;
  }

  @Override
  public String toString() {
    return "Location{" +
            "col=" + col +
            ", row=" + row +
            ", isCave=" + isCave +
            ", treasureList=" + treasureList +
            ", otyughList=" + otyughList +
            ", arrowNum=" + arrowNum +
            '}' + "\n";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LocationImpl locationImpl = (LocationImpl) o;
    return col == locationImpl.col && row == locationImpl.row
            && isCave == locationImpl.isCave
            && treasureList.equals(locationImpl.treasureList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(col, row, isCave, treasureList);
  }
}
