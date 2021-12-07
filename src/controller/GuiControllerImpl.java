package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import game.Direction;
import game.Otyugh;
import game.Treasure;
import model.GameModel;
import view.Util;
import view.View;
import view.ViewImpl;

public class GuiControllerImpl implements GuiController {
  private View view;
  private GameModel model;

  public GuiControllerImpl(GameModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.model = model;
    this.view = this.getInitialView(model);
  }


  @Override
  public void playGame(GameModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.view.setVisible();
    this.view.addClickListener(this);
  }

  @Override
  public void handleCellClick(int x, int y) {
    Direction direction = this.calDir(x, y);
    List<Direction> dirs = this.model.getNextDirList();

    if (direction != null && dirs.contains(direction)) {
      this.model.move(direction);
    }

  }

  @Override
  public void handleKeyPress() {

  }

  private View getInitialView(GameModel model) throws IllegalArgumentException, RuntimeException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    int rows = model.getRowNum();
    int cols = model.getColNum();
    int startRow = model.getStart().getRow();
    int startCol = model.getStart().getCol();

    String imgPath = String.format("res/images/%s.png", this.getImgPath());

    try {
      File file = new File(imgPath);
      BufferedImage image = ImageIO.read(file);
      image = this.addObjects(image);
      View view = new ViewImpl(model, rows, cols, startRow, startCol, image);
      return view;
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }
  }

  /**
   * Used to get the image file name from a string.
   *     Note that the directions come in the order NSEW.
   *
   * @return A string represents the file name of the image.
   */
  private String getImgPath() {
    String dirs = this.model.getNextDirs();
    System.out.println(String.format("dirs are: %s", dirs));
    dirs = dirs.replaceAll(", ", "");
    String orderedStr = "";

    Set strSet = new HashSet<Character>();
    for (int i = 0; i < dirs.length(); i++) {
      strSet.add(dirs.charAt(i));
    }

    //Organize the string in NSEW order.
    for (int i = 0; i < dirs.length(); i++) {
      if (strSet.contains('N')) {
        orderedStr += "N";
        strSet.remove('N');
        continue;
      } else if (strSet.contains('S')) {
        orderedStr += "S";
        strSet.remove('S');
        continue;
      } else if (strSet.contains('E')) {
        orderedStr += "E";
        strSet.remove('E');
        continue;
      } else {
        orderedStr += "W";
        strSet.remove('W');
        continue;
      }
    }

    return orderedStr;
  }

  private BufferedImage overlay(BufferedImage starting, String fpath, int offset) throws IOException {
    BufferedImage overlay = ImageIO.read(new File(fpath));
    //resize the overlay image to specific size.
    overlay = Util.resizeImage(overlay, Util.OBJECTIMGSIZE, Util.OBJECTIMGSIZE);
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  //TODO
  private BufferedImage addObjects(BufferedImage image, int curGridRow, int curGridCol) throws RuntimeException {
    BufferedImage combinedImage = image;
    int offset = 10;
    int arrowNum = this.model.getArrowNum();

    /**
     * Only add one image to the dungeon image because the number of each object
     * will show up in a frame when the player try to pick up something.
     */
    //Add arrow image to the dungeon image if there exists arrow.
    if (arrowNum > 0) {
      String arrowImgPath = "res/images/arrow-white.png";
      try {
        combinedImage = this.overlay(image, arrowImgPath, offset);
      } catch (IOException e) {
        throw new RuntimeException("Image loads failed.");
      }
    }

    //Add treasure image to the Dungeon image if there exists treasure.
    List<Treasure> treasureList = this.model.getTreasureList();
    if (treasureList.size() != 0 ) {
      for (Treasure treasure : treasureList) {
        String treasureImgPath = String.format("res/images/%s.png", treasure.toString().toLowerCase());
        try {
          offset += 10;
          combinedImage = this.overlay(combinedImage, treasureImgPath, offset);
        } catch (IOException e) {
          System.out.println(treasureImgPath);
          throw new RuntimeException("Image loads failed.");
        }
      }
    }

    //Add monster image to the Dungeon image if there exists Otyugh.
    List<Otyugh> otyughList = this.model.getOtyughs();
    if (otyughList.size() != 0) {
      for (Otyugh otyugh : otyughList) {
        String otyughImgPath = String.format("res/images/otyugh.png");
        try {
          offset += 10;
          combinedImage = this.overlay(combinedImage, otyughImgPath, offset);
        } catch (IOException e) {
          throw new RuntimeException("Image loads failed.");
        }
      }
    }

    //TODO: Add a player image to the dungeon image if the player currently in this dungeon.

    try {
      String playerImagePath = String.format("res/images/player.png");
      offset += 10;
      combinedImage = this.overlay(combinedImage, playerImagePath, offset);
    } catch (IOException e) {
      throw new RuntimeException("Image loada failed.");
    }

    //Add a smell image to the dungeon image if it has smell.
    String dangerType = this.model.getDangerType();
    if (dangerType.equals("Less smell")) {
      String stenchLess = String.format("res/images/stench01.png");
      offset += 10;
      try {
        combinedImage = this.overlay(combinedImage, stenchLess, offset);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (dangerType.equals("More smell")) {
      String stenchMore = String.format("res/images/stench02.png");
      offset += 10;
      try {
        combinedImage = this.overlay(combinedImage, stenchMore, offset);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return combinedImage;
  }

  /**
   * Arithmetic method to convert the x, y coords from the view to
   * row and col coords of the Dungeon.
   * @param x The x coord get from the view.
   * @param y The y coord get from the view.
   * @return rowCol[0] represents row, rowCol[1] represents col.
   */
  private int[] calRowCol(int x, int y) {
    int[] rowCol = new int[2];
    rowCol[0] = 0;
    rowCol[1] = 0;

    int imgPanelWidth = this.model.getColNum() * Util.IMGSIZE;
    int imgPanelHeight = this.model.getRowNum() * Util.IMGSIZE;

    if (x <= imgPanelWidth && y <= imgPanelHeight) {
      if (y % Util.IMGSIZE == 0) {
        rowCol[0] = y / Util.IMGSIZE;
      } else {
        rowCol[0] = y / Util.IMGSIZE + 1;
      }

      if (x % Util.IMGSIZE == 0) {
        rowCol[1] = x / Util.IMGSIZE;
      } else {
        rowCol[1] = x / Util.IMGSIZE + 1;
      }
    }

    return rowCol;
  }

  //find the direction of the player clicked.
  private Direction calDir(int x, int y) {
    Direction direction = null;
    int[] rowCol = this.calRowCol(x, y);

    int row = rowCol[0];
    int col = rowCol[1];

    int curRow = this.model.getCurRow();
    int curCol = this.model.getCurCol();

    if ((row == curRow - 1 && col == curCol) ||
            (row == this.model.getRowNum() && col == curCol && this.model.isWrap())) {
      direction = Direction.North;
    } else if ((row == curRow + 1 && col == curCol) ||
            (row == 1 && col == curCol && this.model.isWrap())) {
      direction = Direction.South;
    } else if ((row == curRow && col == curCol - 1) ||
            (row == curRow && col == this.model.getColNum() && this.model.isWrap())) {
      direction = Direction.West;
    } else if ((row == curRow && col == curCol + 1) ||
            (row == curRow && col == 1 && this.model.isWrap())) {
      direction = Direction.East;
    }
    return direction;
  }

  public void restartGame() {

  }
}
