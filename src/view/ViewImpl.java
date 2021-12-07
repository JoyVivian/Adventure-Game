package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import model.GameModel;

public class ViewImpl extends JFrame implements View {
  private GameModel model;

  public ViewImpl(GameModel model, int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    this.model = model;
    GameBoardPanel gameBoardPanel = new GameBoardPanel(5, 5, startRow, startCol, image);
    GameMenuBar gameMenuBar = new GameMenuBar();
    this.setSize(500, 700);
    this.getContentPane().add(gameBoardPanel);
    this.setJMenuBar(gameMenuBar);
  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  private String getStartImgPath() {
    String dirs = this.model.getNextDirs();
    System.out.println(dirs);
    dirs = dirs.replaceAll(", ", "");
    return dirs;
  }
}
