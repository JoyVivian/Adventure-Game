package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

public class ImagePanel extends JPanel {
  private final int rows;
  private final int cols;
  private SingleImageLabel[][] imageLabels;

  public ImagePanel(int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    this.rows = rows;
    this.cols = cols;


    this.setSize(this.calPanelWid(), this.calPanelHei());
    this.setLayout(new GridLayout(rows, cols));

    imageLabels = new SingleImageLabel[rows+1][cols+1];

    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        if (i == startRow && j == startCol) {
          this.imageLabels[i][j] = new SingleImageLabel(image);
          this.add(imageLabels[i][j]);
        } else {
          this.imageLabels[i][j] = new SingleImageLabel();
          this.add(imageLabels[i][j]);
        }
      }
    }
  }

  private int calPanelWid() {
    return this.cols * Util.IMGSIZE;
  }

  private int calPanelHei() {
    return this.rows * Util.IMGSIZE;
  }
}
