package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import controller.GuiController;

class GameBoardPanel extends JPanel {
  private DungeonPane dungeonPane;
  private MessagePanel messagePanel;

  public GameBoardPanel(GuiController guiController,
                        int rows, int cols, int startRow,
                        int startCol, BufferedImage image) throws IOException {
    dungeonPane = new DungeonPane(rows, cols, startRow, startCol, image);

    messagePanel = new MessagePanel(guiController);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(dungeonPane);
    this.add(messagePanel);
  }

  DungeonPane getDungeonPane() {
    return this.dungeonPane;
  }

  MessagePanel getMessagePanel() {
    return this.messagePanel;
  }
}
