package view;

import javax.swing.*;

import model.GameModel;
import model.GameModelImpl;

public class GuiTest extends JFrame {
  public GuiTest() {
    GameModel model = new GameModelImpl(5, 5, 0, false, 20, 5, true);
    String imgPath = String.format("res/images/%s.png", this.getStartImgPath(model));
    GameBoardPanel gameBoardPanel = new GameBoardPanel(5, 5, model.getStart().getRow(), model.getStart().getCol(), imgPath);
    GameMenuBar gameMenuBar = new GameMenuBar();
    JFrame frame = new JFrame();
    frame.setSize(300, 300);
    frame.getContentPane().add(gameBoardPanel);
    frame.setJMenuBar(gameMenuBar);
    frame.setVisible(true);
  }

  private String getStartImgPath(GameModel model) {
    String dirs = model.getNextDirs();
    dirs = dirs.replaceAll(", $", "");
    return dirs;
  }

  public static void main(String[] args) {
    new GuiTest();
  }
}
