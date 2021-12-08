package view;

import java.awt.image.BufferedImage;

import controller.GuiController;

public interface View {
  public void setVisible();

  public void addClickListener(GuiController controller);

  public void updateLocationImg(BufferedImage image);

  public void addKeyPressListener(GuiController controller);

  public void showUpPick(GuiController controller, int diamondNum, int rubyNum, int sapphireNum, int arrowNum);

  public void updateMessageBoard(int diamondNum, int rubyNum, int sapphireNum, int arrowNum);
}

