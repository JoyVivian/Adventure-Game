package view;

import java.awt.*;

import javax.swing.*;

public class GameBoardPanel extends JPanel {
  public GameBoardPanel(int rows, int cols, int startRow, int startCol, String imgPath) {
    DungeonPane dungeonPane = new DungeonPane(rows, cols, startRow, startCol, imgPath);

    MessagePanel messagePanel = new MessagePanel();

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(dungeonPane);
    this.add(messagePanel);
  }
}
