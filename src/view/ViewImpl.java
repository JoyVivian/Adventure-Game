package view;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import controller.GuiController;
import model.GameModel;

public class ViewImpl extends JFrame implements View {
  private GameModel model;
  private GameBoardPanel gameBoardPanel;

  public ViewImpl(GameModel model, int rows, int cols, int startRow, int startCol, BufferedImage image) throws IOException {
    this.model = model;
    this.gameBoardPanel = new GameBoardPanel(5, 5, startRow, startCol, image);
    GameMenuBar gameMenuBar = new GameMenuBar();
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

  private String getStartImgPath() {
    String dirs = this.model.getNextDirs();
    System.out.println(dirs);
    dirs = dirs.replaceAll(", ", "");
    return dirs;
  }
}
