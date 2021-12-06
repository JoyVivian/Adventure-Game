package view;

import javax.swing.*;

public class GuiTest extends JFrame {
  public GuiTest() {
    //DungeonPane dungeonPane = new DungeonPane(5, 5);
    GameBoardPanel gameBoardPanel = new GameBoardPanel(5, 5);
    GameMenuBar gameMenuBar = new GameMenuBar();
    JFrame frame = new JFrame();
    frame.setSize(300, 300);
    frame.getContentPane().add(gameBoardPanel);
    frame.setJMenuBar(gameMenuBar);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    new GuiTest();
  }
}
