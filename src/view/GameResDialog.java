package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import controller.GuiController;

/**
 * This dialog is consist of two parts. One is a
 * JLabel that shows the result of the game, the other
 * is ok button.
 */
public class GameResDialog extends JDialog {
  /**
   * The constructor of the dialog. When the user is win
   * a true will be passed and the text of the JLabel will
   * be set to "Congratulations!". Otherwise the JLabel will
   * be set to "Chomp, chomp, chomp you have eaten by the monster."
   *
   * @param isWin Whether a player has won the game.
   * @param guiController The controller of the game.
   */
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
