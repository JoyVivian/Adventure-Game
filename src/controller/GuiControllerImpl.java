package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import game.Treasure;
import model.GameModel;
import view.ImagePanel;
import view.View;
import view.ViewImpl;

public class GuiControllerImpl implements GuiController {
  private View view;
  private GameModel model;

  public GuiControllerImpl(GameModel model) {
    this.model = model;
    this.view = this.getInitialView(model);
  }


  @Override
  public void playGame(GameModel model) {
    this.view.setVisible();
  }

  @Override
  public void handleCellClick(int x, int y) {

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
      this.addObjects(image);
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    View view = new ViewImpl(model, rows, cols, startRow, startCol, imgPath);
    return view;
  }

  /**
   * Used to get the image file name from a string.
   *     Note that the directions come in the order NSEW.
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
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  private BufferedImage addObjects(BufferedImage image) throws RuntimeException {
    BufferedImage combinedImage = image;
    int arrowNum = this.model.getArrowNum();

    //Add arrow image to the dungeon image if there exists arrow.
    if (arrowNum > 0) {
      String arrowImgPath = "res/images/arrow-white.png";
      try {
        combinedImage = this.overlay(image, arrowImgPath, 5);
      } catch (IOException e) {
        throw new RuntimeException("Image loads failed.");
      }
    }

    //Add treasures' image to the Dungeon image if there exists treasure.
    List<Treasure> treasureList = this.model.getTreasureList();
    if (treasureList.size() != 0 ) {
      for (Treasure treasure : treasureList) {
        String treasureImgPath = String.format("res/images/%s.png", treasure.toString().toLowerCase());
        try {
          combinedImage = this.overlay(image, treasureImgPath, 5);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    //TODO: Add monster image to the Dungeon image if there exists Otyugh.
    return combinedImage;
  }

  public void restartGame() {

  }
}
