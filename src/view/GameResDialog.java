package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.GuiController;

public class GameResDialog extends JDialog {
  public GameResDialog(Boolean isWin, GuiController guiController) {
    JLabel gameResult = new JLabel("");
    if (isWin) {
      gameResult.setText("Congratulations! You win.");
    } else {
      gameResult.setText("Chomp, chomp, chomp you have eaten by the monster.");
    }

    JButton okBtn = new JButton("Ok");
    JDialog thisDialog = this;
    okBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        thisDialog.setVisible(false);
        guiController.restartGame();
      }
    });
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    this.add(gameResult);
    this.add(okBtn);
    this.setSize(200, 80);
    this.setVisible(true);

  }
}
