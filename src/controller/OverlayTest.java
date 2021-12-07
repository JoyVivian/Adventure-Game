package controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import view.Util;

public class OverlayTest extends JFrame {
  public OverlayTest() {
    JLabel imgLabel = new JLabel();
    File dundeonFile = new File("res/images/N.png");
    String arrowFile = "res/images/arrow-white.png";
    String otyughFile = "res/images/otyugh.png";
    try {
      BufferedImage dungeonImg = ImageIO.read(dundeonFile);
      dungeonImg = this.resizeImage(dungeonImg, 128, 128);
      BufferedImage combinedImage = this.overlay(dungeonImg, arrowFile, 30);
      combinedImage = this.overlay(combinedImage, otyughFile, 40);

      imgLabel.setIcon(new ImageIcon(combinedImage));
    } catch (IOException e) {
      e.printStackTrace();
    }

    this.add(imgLabel);
    this.setSize(400, 400);
    this.setVisible(true);
  }

  private BufferedImage overlay(BufferedImage starting, String fpath, int offset) throws IOException {
    BufferedImage overlay = ImageIO.read(new File(fpath));
    //resize the overlay image to specific size.
    overlay = this.resizeImage(overlay, Util.OBJECTIMGSIZE, Util.OBJECTIMGSIZE);
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }

  public static void main(String[] args) {
    new OverlayTest();
  }
}
