package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

public class DungeonPane extends JScrollPane {
  public DungeonPane(int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    this.setPreferredSize(new Dimension(500, 400));
    ImagePanel imgPanel = new ImagePanel(rows, cols, startRow, startCol, image);
    this.setViewportView(imgPanel);
  }
}
