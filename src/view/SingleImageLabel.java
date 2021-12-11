package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

class SingleImageLabel extends JLabel {
  public SingleImageLabel() throws RuntimeException {
    try {
      BufferedImage image = ImageIO.read(ClassLoader.getSystemResource("images/blank.png"));
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
