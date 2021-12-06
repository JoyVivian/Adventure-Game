package view;

import java.awt.*;

import javax.swing.*;

public class ImagePanel extends JPanel {
  private final int imageSize = 64;
  private final int rows;
  private final int cols;
  private SingleImageLabel[][] imageLabels;

  public ImagePanel(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;


    this.setSize(this.calPanelWid(), this.calPanelHei());
    this.setLayout(new GridLayout(rows, cols));

    imageLabels = new SingleImageLabel[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        this.imageLabels[i][j] = new SingleImageLabel();
        this.add(imageLabels[i][j]);
      }
    }
  }

  private int calPanelWid() {
    return this.cols * imageSize;
  }

  private int calPanelHei() {
    return this.rows * imageSize;
  }
}
