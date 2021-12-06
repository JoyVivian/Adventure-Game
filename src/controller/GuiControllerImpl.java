package controller;

import model.GameModel;
import view.View;

public class GuiControllerImpl implements GuiController {
  private View view;
  private GameModel model;

  public GuiControllerImpl() {
    this.view = view;
    this.model = model;
  }


  @Override
  public void playGame(GameModel model) {

  }

  @Override
  public void handleCellClick(int x, int y) {

  }

  @Override
  public void handleKeyPress() {

  }
}
