package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

public class GameBoardPanel extends JPanel {
  public GameBoardPanel(int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    DungeonPane dungeonPane = new DungeonPane(rows, cols, startRow, startCol, image);

    MessagePanel messagePanel = new MessagePanel();

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(dungeonPane);
    this.add(messagePanel);
  }
}
