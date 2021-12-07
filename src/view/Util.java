package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Util {
  public static final int IMGSIZE = 128;
  public static final int OBJECTIMGSIZE = 20;

  //How to resize a BufferImage instance.
  //Reference Link: https://www.baeldung.com/java-resize-image
  public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }
}
