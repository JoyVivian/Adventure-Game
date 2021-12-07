package view;

import controller.GuiController;

public interface View {
  public void setVisible();

  public void addClickListener(GuiController controller);
}

