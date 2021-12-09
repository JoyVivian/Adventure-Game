package controller;

import java.awt.*;
import java.awt.event.KeyEvent;
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
import model.GameModelImpl;
import view.GameResDialog;
import view.Util;
import view.View;
import view.ViewImpl;

public class GuiControllerImpl implements GuiController {
  private View view;
  private GameModel model;

  private int customRows = 5;
  private int customCols = 5;
  private int customInterconnectivity = 0;
  private boolean customIsWrap = false;
  private int customTreasurePer = 20;
  private int customOtyNum = 5;

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
    this.view.addKeyPressListener(this);
  }

  @Override
  public void handleCellClick(int x, int y) {
    Direction direction = this.calDir(x, y);
    List<Direction> dirs = this.model.getNextDirList();

    if (direction != null && dirs.contains(direction)) {
      this.changImg(false);
      this.model.move(direction);
      this.changImg(true);

      //Decide whether a player is win or be eaten.
      if (this.model.isEnd() && !this.model.isEaten(true)) {
        GameResDialog gameResDialog = new GameResDialog(true, this);
      } else if (this.model.isEaten(true)) {
        GameResDialog gameResDialog = new GameResDialog(false, this);
      }
    }
  }

  //https://www.youtube.com/watch?v=GAn5evoACsM
  @Override
  public void handleKeyPress(int keyCode) {
    Direction direction = null;
    List<Direction> dirs = this.model.getNextDirList();

    switch (keyCode) {
      case KeyEvent.VK_UP:
        direction = Direction.North;
        break;
      case KeyEvent.VK_DOWN:
        direction = Direction.South;
        break;
      case KeyEvent.VK_LEFT:
        direction = Direction.West;
        break;
      case KeyEvent.VK_RIGHT:
        direction = Direction.East;
        break;
      case KeyEvent.VK_P:
        this.showUpPick(this);
        break;
      case KeyEvent.VK_W:
        this.tryShoot(Direction.North);
        break;
      case KeyEvent.VK_S:
        this.tryShoot(Direction.South);
        break;
      case KeyEvent.VK_A:
        this.tryShoot(Direction.West);
        break;
      case KeyEvent.VK_D:
        this.tryShoot(Direction.East);
        break;
      case KeyEvent.VK_Q:
        this.view.disableShoot();
        break;
      default:
        direction = null;
    }

    if (direction != null && dirs.contains(direction)) {
      this.changImg(false);
      this.model.move(direction);
      this.changImg(true);

      //Decide whether a player is win or be eaten.
      if (this.model.isEnd() && !this.model.isEaten(true)) {
        GameResDialog gameResDialog = new GameResDialog(true, this);
      } else if (this.model.isEaten(true)) {
        GameResDialog gameResDialog = new GameResDialog(false, this);
      }
    }
  }

  private void tryShoot(Direction direction) {
    List<Direction> dirs = this.model.getNextDirList();
    int arrowNum = this.model.getPlayArrowNum();

    if (arrowNum == 0) {
      this.view.setRunoutArrowPrompt();
    }

    if (dirs.contains(direction) && arrowNum > 0) {
      this.view.enableShoot(direction);
    }
  }

  @Override
  public void handlePickUp(int pickDiaNum, int pickRubyNum, int pickSapphireNum, int pickArrowNum) {
    for (int i = pickDiaNum; i > 0; i--) {
      this.model.pickUpTreasure(Treasure.DIAMOND);
    }

    for (int i = pickRubyNum; i > 0; i--) {
      this.model.pickUpTreasure(Treasure.RUBIE);
    }

    for (int i = pickSapphireNum; i > 0; i--) {
      this.model.pickUpTreasure(Treasure.SAPPHIRE);
    }

    if (pickArrowNum > 0) {
      this.model.pickUpArrows();
    }

    //Change the pic of the current location.
    this.changImg(true);

    int diaNum = this.model.getPlayerTreasureNum(Treasure.DIAMOND);
    int rubyNum = this.model.getPlayerTreasureNum(Treasure.RUBIE);
    int sapphireNum = this.model.getPlayerTreasureNum(Treasure.SAPPHIRE);
    int arrowNum = this.model.getPlayArrowNum();

    this.view.updateMessageBoard(diaNum, rubyNum, sapphireNum, arrowNum);
  }

  @Override
  public void handleShoot(Direction direction, int distance) {
    //Show the shoot result in the message board.
    Boolean isHit = this.model.slayOty(direction, distance);
    this.view.showShootResult(isHit);

    //Update the smell in the current Location.
    this.changImg(true);

    //Change the number of arrows.
    this.model.decreaseArrows();

    //Update the messageboard after shoot.
    int diamondNum = this.model.getPlayerTreasureNum(Treasure.DIAMOND);
    int rubyNum = this.model.getPlayerTreasureNum(Treasure.RUBIE);
    int sapphireNum = this.model.getPlayerTreasureNum(Treasure.SAPPHIRE);
    int arrowNum = this.model.getPlayArrowNum();

    this.view.updateMessageBoard(diamondNum, rubyNum, sapphireNum, arrowNum);
  }

  private void showUpPick(GuiController guiController) {
    int diamondNum = this.model.getTreasureNum(Treasure.DIAMOND);
    int rubyNum = this.model.getTreasureNum(Treasure.RUBIE);
    int sapphireNum = this.model.getTreasureNum(Treasure.SAPPHIRE);

    int arrowNum = this.model.getArrowNum();

    this.view.showUpPick(guiController, diamondNum, rubyNum, sapphireNum, arrowNum);
  }

  private void changImg(Boolean hasPlayer) {
    String imgPath = String.format("res/images/%s.png", this.getImgPath());

    try {
      File file = new File(imgPath);
      BufferedImage image = ImageIO.read(file);
      image = Util.resizeImage(image, Util.IMGSIZE, Util.IMGSIZE);
      image = this.addObjects(image, hasPlayer);
      this.view.updateLocationImg(image);
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }
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
      image = Util.resizeImage(image, Util.IMGSIZE, Util.IMGSIZE);
      image = this.addObjects(image, true);
      View view = new ViewImpl(this, model, rows, cols, startRow, startCol, image);
      return view;
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }
  }

  /**
   * Used to get the image file name from a string.
   * Note that the directions come in the order NSEW.
   *
   * @return A string represents the file name of the image.
   */
  private String getImgPath() {
    String dirs = this.model.getNextDirs();
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

  private BufferedImage addObjects(BufferedImage image, boolean hasPlayer) throws RuntimeException {
    BufferedImage combinedImage = image;
    int offset = 35;
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
    if (treasureList.size() != 0) {
      for (Treasure treasure : treasureList) {
        String treasureImgPath = String.format("res/images/%s.png", treasure.toString().toLowerCase());
        try {
          offset += 10;
          combinedImage = this.overlay(combinedImage, treasureImgPath, offset);
        } catch (IOException e) {
          throw new RuntimeException("Image loads failed.");
        }
      }
    }

    //Add monster image to the Dungeon image if there exists Otyugh.
    List<Otyugh> otyughList = this.model.getOtyughs();
    int otyughNum = 0;

    for (Otyugh otyugh : otyughList) {
      if (otyugh.getHealth() == 0) {
        continue;
      } else {
        otyughNum++;
      }
    }

    for (int i = otyughNum; i > 0; i--) {
      String otyughImgPath = String.format("res/images/otyugh.png");
      try {
        offset += 10;
        combinedImage = this.overlay(combinedImage, otyughImgPath, offset);
      } catch (IOException e) {
        throw new RuntimeException("Image loads failed.");
      }
    }

    //Add a smell image to the dungeon image if it has smell.
    String dangerType = this.model.getDangerType();
    if (dangerType.equals("You smell something horrible here.\n") && hasPlayer) {
      String stenchLess = String.format("res/images/stench01.png");
      offset += 10;
      try {
        combinedImage = this.overlay(combinedImage, stenchLess, offset);
      } catch (IOException e) {
        throw new RuntimeException("Image loads failed.");
      }
    } else if (dangerType.equals("You smell something very horrible here.\n") && hasPlayer) {
      String stenchMore = String.format("res/images/stench02.png");
      offset += 10;
      try {
        combinedImage = this.overlay(combinedImage, stenchMore, offset);
      } catch (IOException e) {
        throw new RuntimeException("Image loads failed.");
      }
    }

    if (hasPlayer) {
      try {
        String playerImagePath = String.format("res/images/player.png");
        offset += 10;
        combinedImage = this.overlay(combinedImage, playerImagePath, offset);
      } catch (IOException e) {
        throw new RuntimeException("Image loads failed.");
      }
    }

    return combinedImage;
  }

  /**
   * Arithmetic method to convert the x, y coords from the view to
   * row and col coords of the Dungeon.
   *
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
    GameModel model = new GameModelImpl(customRows, customCols,
            customInterconnectivity, customIsWrap, customTreasurePer, customOtyNum, true);
    this.view.makeUnvisible();
    this.model = model;
    this.view = this.getInitialView(model);
    this.playGame(model);
  }

  @Override
  public void setCustomValues(int customRows, int customCols, int customInterconnectivity, boolean customIsWrap, int customTreasurePer, int customOtyNum) {
    this.customRows = customRows;
    this.customCols = customCols;
    this.customInterconnectivity = customInterconnectivity;
    this.customIsWrap = customIsWrap;
    this.customTreasurePer = customTreasurePer;
    this.customOtyNum = customOtyNum;
  }
}
