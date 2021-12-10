package view;

import java.awt.image.BufferedImage;

import controller.GuiController;
import game.Direction;

/**
 * This interface defines many methods to update the view.
 */
public interface View {
  /**
   * To make the main frame of the game visible which will be
   * called in the controller to start the game.
   */
  void setVisible();

  /**
   * Add a click listener in the imagePanel instance and
   * get input from the view and pass them to controller.
   *
   * @param controller The controller used to handle the input
   *                       from the view.
   */
  void addClickListener(GuiController controller);

  /**
   * This method is used to update images on the current location.
   *
   * @param image The image that should be showed in the
   *                  current location.
   */
  void updateLocationImg(BufferedImage image);

  /**
   * Add a key listener in the main frame and get the key code from
   * the view and pass them to the controller to process.
   *
   * @param controller The controller used to handle the input
   *                       from the view.
   */
  void addKeyPressListener(GuiController controller);

  /**
   * Show up the pick up frame to let the user to choose
   * which treasures to pick.
   *
   * @param controller The controller use to handle the input from the view.
   * @param diamondNum The number of diamond in the current location.
   * @param rubyNum The number of ruby in the current location.
   * @param sapphireNum The number of sapphire in the current location.
   * @param arrowNum The number of arrow in the current location.
   */
  void showUpPick(GuiController controller, int diamondNum,
                  int rubyNum, int sapphireNum, int arrowNum);

  /**
   * This method is used to update the message board after pick up treasures.
   *
   * @param diamondNum The number of diamond picked up.
   * @param rubyNum The number of ruby picked up.
   * @param sapphireNum The number of sapphire picked up.
   * @param arrowNum The number of arrow picked up.
   */
  void updateMessageBoard(int diamondNum, int rubyNum, int sapphireNum, int arrowNum);

  /**
   * This method is used to enable shoot function after the validation of
   * shooting direction.
   *
   * @param direction The chose direction by the player that should be showed
   *                      up in the message board.
   */
  void enableShoot(Direction direction);

  /**
   * Disable the function of shooting when the direction user choose can not
   * reach.
   */
  void disableShoot();

  /**
   * Show the result of shooting in the message board.
   *
   * @param isHit A boolean represents whether a monster is hit.
   */
  void showShootResult(Boolean isHit);

  /**
   * Show a message to prompt that the player has run out of arrows.
   */
  void setRunoutArrowPrompt();

  /**
   * Make the whole frame unvisible after creating another frame.
   */
  void makeUnvisible();

  /**
   * Show the GameResDialog to prompt the result of the game.
   *
   * @param isWin A boolean represents whether a player is win or not.
   * @param guiController The controller of the game.
   */
  void showGameResult(Boolean isWin, GuiController guiController);
}

