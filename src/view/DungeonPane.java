package view;

import java.awt.*;

import javax.swing.*;

public class DungeonPane extends JScrollPane {
  public DungeonPane(int rows, int cols, int startRow, int startCol, String imgPath) {
    this.setPreferredSize(new Dimension(500, 400));
    ImagePanel imgPanel = new ImagePanel(rows, cols, startRow, startCol, imgPath);
    this.setViewportView(imgPanel);
  }
}
