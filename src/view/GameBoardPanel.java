package view;

import java.awt.*;

import javax.swing.*;

public class GameBoardPanel extends JPanel {
  public GameBoardPanel(int rows, int cols) {
    DungeonPane dungeonPane = new DungeonPane(rows, cols);

    MessagePanel messagePanel = new MessagePanel();

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(dungeonPane);
    this.add(messagePanel);
  }
}
