package view;

import java.awt.*;

import javax.swing.*;

public class DungeonPane extends JScrollPane {
  public DungeonPane(int rows, int cols) {
    int width = cols * 64;
    int height = rows * 64;

    ImagePanel imgPanel = new ImagePanel(rows, cols);
    this.setSize(width, height);
    this.setViewportView(imgPanel);
  }
}
