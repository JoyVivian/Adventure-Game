package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameMenuBar extends JMenuBar {
  public GameMenuBar() {
    JMenu game = new JMenu("Game");
    JMenuItem exitGame = new JMenuItem("Exit game");
    JMenuItem restartGame = new JMenuItem("Restart game");
    game.add(exitGame);
    game.add(restartGame);

    JMenu custom = new JMenu("Custom");
    JMenuItem customSetting = new JMenuItem("Custom Setting");
    custom.add(customSetting);

    GameSettingFrame settingFrame = new GameSettingFrame();
    customSetting.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        settingFrame.setVisible(true);
      }
    });

    this.add(game);
    this.add(custom);
  }
}
