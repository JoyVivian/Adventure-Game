package view;

import java.awt.*;

import javax.swing.*;

public class ViewImpl extends JFrame implements View {
  public ViewImpl() {
    GameBoardPanel gameBoardPanel = new GameBoardPanel(5, 5);
    GameMenuBar gameMenuBar = new GameMenuBar();
    JFrame frame = new JFrame();
    frame.setSize(300, 300);
    frame.getContentPane().add(gameBoardPanel);
    frame.setJMenuBar(gameMenuBar);
    frame.setVisible(true);
  }
}
