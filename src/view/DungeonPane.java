package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JScrollPane;

class DungeonPane extends JScrollPane {
  private ImagePanel imgPanel;

  public DungeonPane(int rows, int cols, int startRow,
                     int startCol, BufferedImage image) throws IOException {
    this.setPreferredSize(new Dimension(500, 400));
    imgPanel = new ImagePanel(rows, cols, startRow, startCol, image);
    ImageWrapperPanel imageWrapperPanel = new ImageWrapperPanel(imgPanel);
    this.setViewportView(imageWrapperPanel);
  }

  ImagePanel getImgPanel() {
    return this.imgPanel;
  }
}
