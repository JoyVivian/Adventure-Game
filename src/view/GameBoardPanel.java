package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

class GameBoardPanel extends JPanel {
  private DungeonPane dungeonPane;

  public GameBoardPanel(int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    dungeonPane = new DungeonPane(rows, cols, startRow, startCol, image);

    MessagePanel messagePanel = new MessagePanel();

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(dungeonPane);
    this.add(messagePanel);
  }

  public DungeonPane getDungeonPane() {
    return this.dungeonPane;
  }
}
