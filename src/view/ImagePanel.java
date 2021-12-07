package view;

import java.awt.*;

import javax.swing.*;

public class ImagePanel extends JPanel {
  private final int rows;
  private final int cols;
  private SingleImageLabel[][] imageLabels;

  public ImagePanel(int rows, int cols, int startRow, int startCol, String imgPath) {
    this.rows = rows;
    this.cols = cols;


    this.setSize(this.calPanelWid(), this.calPanelHei());
    this.setLayout(new GridLayout(rows, cols));

    imageLabels = new SingleImageLabel[rows+1][cols+1];

    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        if (i == startRow && j == startCol) {
          this.imageLabels[i][j] = new SingleImageLabel(imgPath);
          this.add(imageLabels[i][j]);
        } else {
          this.imageLabels[i][j] = new SingleImageLabel();
          this.add(imageLabels[i][j]);
        }
      }
    }
  }

  private int calPanelWid() {
    return this.cols * Reference.IMGSIZE;
  }

  private int calPanelHei() {
    return this.rows * Reference.IMGSIZE;
  }
}
