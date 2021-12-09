import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import view.Util;

public class overlayTest {
  public overlayTest() {
    JFrame frame = new JFrame();
    JLabel label = new JLabel();

    File file = new File("res/images/N.png");
    try {
      BufferedImage image = ImageIO.read(file);
      BufferedImage combined = this.overlay(image, "res/images/diamond.png", 20);
      label.setIcon(new ImageIcon(combined));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }
    frame.add(label);
    frame.setSize(400, 400);
    frame.setVisible(true);
  }

  private BufferedImage overlay(BufferedImage starting, String fpath, int offset) throws IOException {
    BufferedImage overlay = ImageIO.read(new File(fpath));
    //resize the overlay image to specific size.
    overlay = Util.resizeImage(overlay, Util.OBJECTIMGSIZE, Util.OBJECTIMGSIZE);
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  public static void main(String[] args) {
    new overlayTest();
  }
}
