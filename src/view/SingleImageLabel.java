package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SingleImageLabel extends JLabel {
  public SingleImageLabel() {
   try {
     File file = new File("res/images/blank.png");
     BufferedImage image = ImageIO.read(file);
     this.setSize(64, 64);
     this.setIcon(new ImageIcon(image));
   } catch (IOException e) {
     throw new RuntimeException("Image loads failed.");
   }
  }
}
