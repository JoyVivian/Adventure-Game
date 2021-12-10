package controller;

import game.Direction;
import model.GameModel;

/**
 * This interface is the controller of Gui version of thi game.
 * This controller is used to process mouse click, key event, pick up,
 * shoot and game restart.
 */
public interface GuiController {
  /**
   * The function to start the game.
   *
   * @param model The model generated for this game.
   */
  void playGame(GameModel model);

  /**
   * This method gets the x, y coordinates from the user and
   * pass these parameters to model to process and get the result
   * from the model to start the game.
   *
   * @param x The x coordinate the user clicks.
   * @param y The y coordinate the user clicks.
   */
  void handleCellClick(int x, int y);

  /**
   * This method gets the key code that the user press and pass
   * this code to model and update the view.
   *
   * @param keyCode the code of the key the user pressed.
   */
  void handleKeyPress(int keyCode);

  /**
   * Get the picked objects and their number and update the MessageBoard.
   * If the user did not pick up a item their number will be 0.
   *
   * @param pickDiaNum The number of Diamond that a user picked.
   * @param pickRubyNum The number of Ruby that a user picked.
   * @param pickSapphireNum The number of Sapphire that a user picked.
   * @param pickArrowNum The number of Arrow that a user picked.
   */
  void handlePickUp(int pickDiaNum, int pickRubyNum, int pickSapphireNum, int pickArrowNum);

  /**
   * Get the shoot direction and the shoot distance from the view enter by the user
   * and shoot and get the result in the model.
   *
   * @param direction Shoot direction.
   * @param distance Shoot distance.
   */
  void handleShoot(Direction direction, int distance);

  /**
   * This method re-generate model and view and control the program
   * to restart the game.
   */
  void restartGame();

  /**
   * The custom setting is store in 6 fields in the controller.
   * When a user choose to restart the game. These stored parameters
   * will be used to generate the game.
   *
   * @param customRows The custom setting of rows of the dungeon.
   * @param customCols The custom setting of columns of the dungeon.
   * @param customInterconnectivity The custom setting of the interconnectivity of the dungeon.
   * @param customIsWrap The custom setting of wrapping.
   * @param customTreasurePer The custom setting of percentage of treasures and arrows.
   * @param customOtyNum The custom setting of number of monsters.
   */
  void setCustomValues(int customRows, int customCols,
                              int customInterconnectivity, boolean customIsWrap,
                       int customTreasurePer, int customOtyNum);

  /**
   * This method is used to disable move function when a user is shooting.
   */
  void disableMove();

  /**
   * This method is used to enable move function after a user has shoot.
   */
  void enableMove();
}
