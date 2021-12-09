package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.GuiController;

class GameMenuBar extends JMenuBar {
  public GameMenuBar(GuiController guiController) {
    JMenu game = new JMenu("Game");
    JMenuItem exitGame = new JMenuItem("Exit game");
    JMenuItem restartGame = new JMenuItem("Restart game");
    game.add(exitGame);
    game.add(restartGame);

    exitGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    restartGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        guiController.restartGame();
      }
    });

    JMenu custom = new JMenu("Custom");
    JMenuItem customSetting = new JMenuItem("Custom Setting");
    custom.add(customSetting);

    GameSettingFrame settingFrame = new GameSettingFrame(guiController);

    customSetting.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        settingFrame.setVisible(true);
      }
    });

    JMenu help = new JMenu("Help");
    JMenuItem helpInfo = new JMenuItem("help information");
    help.add(helpInfo);
    HelpFrame helpFrame = new HelpFrame();
    helpInfo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        helpFrame.setVisible(true);
      }
    });

    this.add(game);
    this.add(custom);
    this.add(help);
  }
}
