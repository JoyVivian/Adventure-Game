package controller;

import javax.swing.*;

import game.Direction;
import model.GameModel;
import view.ShootFrame;

public interface GuiController {
  public void playGame(GameModel model);

  public void handleCellClick(int x, int y);

  public void handleKeyPress(int keyCode);

  public void handlePickUp(int pickDiaNum, int pickRubyNum, int pickSapphireNum, int pickArrowNum);

  public void handleShoot(Direction direction, int distance);
}
