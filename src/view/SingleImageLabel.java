package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

class SingleImageLabel extends JLabel {
  public SingleImageLabel() throws RuntimeException {
    try {
      File file = new File("res/images/blank.png");
      BufferedImage image = ImageIO.read(file);
      image = ViewUtil.resizeImage(image, ViewUtil.IMGSIZE, ViewUtil.IMGSIZE);
      this.setSize(ViewUtil.IMGSIZE, ViewUtil.IMGSIZE);
      this.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }
  }

  public SingleImageLabel(BufferedImage image) throws RuntimeException, IOException {
    image = ViewUtil.resizeImage(image, ViewUtil.IMGSIZE, ViewUtil.IMGSIZE);
    this.setSize(ViewUtil.IMGSIZE, ViewUtil.IMGSIZE);
    this.setIcon(new ImageIcon(image));
  }
}
