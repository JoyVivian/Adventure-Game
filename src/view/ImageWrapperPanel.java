package view;

import javax.swing.*;

/**
 * This class is used to wrap the ImagePanel to ensure that there
 * do not have gaps when the window resized.
 * Reference link: https://stackoverflow.com/questions/65112002/too-large-vertical-gaps-on-swing-gridlayout
 */
public class ImageWrapperPanel extends JPanel {
  public ImageWrapperPanel(ImagePanel imagePanel) {
    this.add(imagePanel);
  }
}
