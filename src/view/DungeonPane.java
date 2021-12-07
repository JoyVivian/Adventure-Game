package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

class DungeonPane extends JScrollPane {
  private ImagePanel imgPanel;

  public DungeonPane(int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    this.setPreferredSize(new Dimension(500, 400));
    imgPanel = new ImagePanel(rows, cols, startRow, startCol, image);
    this.setViewportView(imgPanel);
  }

  ImagePanel getImgPanel() {
    return this.imgPanel;
  }
}
