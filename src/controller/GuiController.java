package controller;

import game.Direction;
import model.GameModel;


public interface GuiController {
  public void playGame(GameModel model);

  public void handleCellClick(int x, int y);

  public void handleKeyPress(int keyCode);

  public void handlePickUp(int pickDiaNum, int pickRubyNum, int pickSapphireNum, int pickArrowNum);

  public void handleShoot(Direction direction, int distance);

  public void restartGame();

  public void setCustomValues(int customRows, int customCols,
                              int customInterconnectivity, boolean customIsWrap, int customTreasurePer, int customOtyNum);

  void disableMove();

  void enableMove();
}
