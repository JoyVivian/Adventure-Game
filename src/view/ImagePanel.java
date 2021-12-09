package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

class ImagePanel extends JPanel {
  private final int rows;
  private final int cols;
  private SingleImageLabel[][] imageLabels;

  public ImagePanel(int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    this.rows = rows;
    this.cols = cols;


    this.setSize(this.calPanelWid(), this.calPanelHei());
    GridLayout grid = new GridLayout(rows, cols);

    grid.setHgap(0);
    grid.setVgap(0);

    this.setLayout(grid);

    imageLabels = new SingleImageLabel[rows + 1][cols + 1];

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

  void setImg(BufferedImage image, int row, int col) {
    imageLabels[row][col].setIcon(new ImageIcon(image));
  }
}
