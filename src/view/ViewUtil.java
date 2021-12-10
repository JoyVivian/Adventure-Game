package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The help class that defines the size of the image in
 * each cell of the dungeon.
 */
public class ViewUtil {
  public static final int IMGSIZE = 128;

  //How to resize a BufferImage instance.
  //Reference Link: https://www.baeldung.com/java-resize-image

  /**
   * This method is used to resize the image to specific size.
   *
   * @param originalImage The original image.
   * @param targetWidth The width that the image wants to resize to.
   * @param targetHeight The height that the image wants ro resize to.
   * @return A BufferImage represents the resized image.
   * @throws IOException When the image loading fails, it will be called.
   */
  public static BufferedImage resizeImage(BufferedImage originalImage,
                                          int targetWidth, int targetHeight) throws IOException {
    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight,
            Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight,
            BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }
}
