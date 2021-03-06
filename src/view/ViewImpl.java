package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.GuiController;
import game.Direction;
import model.GameModel;

/**
 * This class is an implementation of the View interface that extends
 * from a JFrame. This class is used to construct the main frame of the
 * whole game and provide methods to update these components included by
 * this view.
 */
public class ViewImpl extends JFrame implements View {
  private GameModel model;
  private GameBoardPanel gameBoardPanel;

  /**
   * The constructor of the View class that takes in few parameters to construct
   * the main frame.
   *
   * @param guiController The controller of the game.
   * @param model The model of the game.
   * @param rows The number of rows of the dungeon.
   * @param cols The number of the columns of the dungeon.
   * @param startRow The row of the start location.
   * @param startCol The column of the start location.
   * @param image The image of the start location.
   * @throws IOException When the image loads failed, it will be called.
   */
  public ViewImpl(GuiController guiController, GameModel model,
                  int rows, int cols, int startRow,
                  int startCol, BufferedImage image) throws IOException {
    this.model = model;
    this.gameBoardPanel = new GameBoardPanel(guiController, rows, cols, startRow, startCol, image);

    GameMenuBar gameMenuBar = new GameMenuBar(guiController);
    this.setSize(500, 700);
    this.getContentPane().add(gameBoardPanel);
    this.setJMenuBar(gameMenuBar);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  @Override
  public void addClickListener(GuiController controller) throws IllegalArgumentException {
    if (controller == null) {
      throw new IllegalArgumentException("Controller cann not be null.");
    }

    //Create MouseAdapter.
    MouseApt clickAdapter = new MouseApt() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        //Get the x, y coordinates and pass them to the controller.
        int x = e.getX();
        int y = e.getY();
        controller.handleCellClick(x, y);
      }
    };

    //Listener should be add to imagePanel.
    this.gameBoardPanel.getDungeonPane().getImgPanel().addMouseListener(clickAdapter);
  }

  @Override
  public void addKeyPressListener(GuiController controller) throws IllegalArgumentException {
    if (controller == null) {
      throw new IllegalArgumentException("Controller can not be null.");
    }

    KeyApt keyAdapter = new KeyApt() {
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int keyCode = e.getKeyCode();
        controller.handleKeyPress(keyCode);
      }
    };

    this.addKeyListener(keyAdapter);
  }


  @Override
  public void showUpPick(GuiController guiController,
                         int diamondNum, int rubyNum, int sapphireNum, int arrowNum) {
    PickUpFrame pickUpFrame = new PickUpFrame(guiController, diamondNum,
            rubyNum, sapphireNum, arrowNum);
    pickUpFrame.setVisible(true);
  }


  @Override
  public void updateMessageBoard(int diamondNum,
                                 int rubyNum, int sapphireNum, int arrowNum) {
    this.gameBoardPanel.getMessagePanel().updateNums(diamondNum, rubyNum,
            sapphireNum, arrowNum);
  }

  @Override
  public void enableShoot(Direction direction) {
    this.gameBoardPanel.getMessagePanel().enableShoot(direction);
  }

  @Override
  public void disableShoot() {
    this.gameBoardPanel.getMessagePanel().disableShoot();
  }

  @Override
  public void showShootResult(Boolean isHit) {
    this.gameBoardPanel.getMessagePanel().setShootResult(isHit);
  }

  @Override
  public void setRunoutArrowPrompt() {
    this.gameBoardPanel.getMessagePanel().setRunoutArrowPrompt();
  }

  @Override
  public void makeUnvisible() {
    this.setVisible(false);
  }

  @Override
  public void showGameResult(Boolean isWin, GuiController guiController) {
    GameResDialog gameResDialog = new GameResDialog(isWin, guiController);
  }

  @Override
  public void updateLocationImg(BufferedImage image) {
    this.gameBoardPanel.getDungeonPane().getImgPanel().setImg(image,
            this.model.getCurRow(), this.model.getCurCol());
  }


}
