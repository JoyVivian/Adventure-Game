package view;

import javax.swing.*;

import model.GameModel;

public class ViewImpl extends JFrame implements View {
  private GameModel model;

  public ViewImpl(GameModel model, int rows, int cols, int startRow, int startCol, String imgPath) {
    this.model = model;
    GameBoardPanel gameBoardPanel = new GameBoardPanel(5, 5, startRow, startCol, imgPath);
    GameMenuBar gameMenuBar = new GameMenuBar();
    this.setSize(300, 300);
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
