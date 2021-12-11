package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

import controller.GuiController;
import game.Direction;

class MessagePanel extends JPanel {
  private JLabel diaLabel;
  private JLabel rubyLabel;
  private JLabel sapphireLabel;
  private JLabel arrowLabel;

  private JLabel shootDir;
  private JComboBox shootDis;
  private JButton shootBtn;

  private JLabel shootResult;

  public MessagePanel(GuiController guiController) {
    JPanel objectPanel = new JPanel();
    objectPanel.setLayout(new GridLayout(2, 4));
    JLabel ruby = new JLabel();
    try {
      BufferedImage image = ImageIO.read(ClassLoader.getSystemResource("images/rubie.png"));
      ruby.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    JLabel diamond = new JLabel();
    try {
      BufferedImage image = ImageIO.read(ClassLoader.getSystemResource("images/diamond.png"));
      diamond.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    JLabel emerald = new JLabel();
    try {
      BufferedImage image = ImageIO.read(ClassLoader.getSystemResource("images/sapphire.png"));
      emerald.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    JLabel arrowImg = new JLabel();
    try {
      BufferedImage image = ImageIO.read(ClassLoader.getSystemResource("images/arrow-white.png"));
      arrowImg.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    rubyLabel = new JLabel("  0");
    diaLabel = new JLabel("  0");
    sapphireLabel = new JLabel("  0");
    arrowLabel = new JLabel("  3");

    objectPanel.add(ruby);
    objectPanel.add(diamond);
    objectPanel.add(emerald);
    objectPanel.add(arrowImg);
    objectPanel.add(rubyLabel);
    objectPanel.add(diaLabel);
    objectPanel.add(sapphireLabel);
    objectPanel.add(arrowLabel);

    JPanel promptPanel = new JPanel();
    promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));

    JPanel resultPanel = new JPanel();
    resultPanel.setLayout(new GridLayout(2, 1));
    JLabel shoot = new JLabel("Shoot result: ");
    this.shootResult = new JLabel("");
    resultPanel.add(shoot);
    resultPanel.add(shootResult);

    JPanel processPanel = new JPanel();
    processPanel.setLayout(new BoxLayout(processPanel, BoxLayout.X_AXIS));

    shootDir = new JLabel("Shoot dir: ");
    shootDis = new JComboBox<Integer>();
    shootDis.addItem(1);
    shootDis.addItem(2);
    shootDis.addItem(3);
    shootDis.addItem(4);
    shootDis.addItem(5);

    shootDis.setFocusable(false);

    shootDis.setEnabled(false);
    shootBtn = new JButton("Shoot");
    shootBtn.setEnabled(false);

    //https://stackoverflow.com/questions/8074316/keylistener-not-working-after-clicking-button
    //Must set the JButton not focusable, Or the key listener of the frame will not work.
    shootBtn.setFocusable(false);

    MessagePanel thisPanel = this;
    shootBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String dirStr = shootDir.getText();
        String dir = dirStr.substring(11, 12);

        Direction direction = null;

        switch (dir) {
          case "N":
            direction = Direction.North;
            break;
          case "S":
            direction = Direction.South;
            break;
          case "W":
            direction = Direction.West;
            break;
          case "E":
            direction = Direction.East;
            break;
          default:
            direction = null;
        }

        int shootDistance = shootDis.getSelectedIndex() + 1;

        if (direction != null) {
          guiController.handleShoot(direction, shootDistance);
          guiController.enableMove();
        }

        thisPanel.disableShoot();
      }
    });

    processPanel.add(shootDir);
    processPanel.add(shootDis);
    processPanel.add(shootBtn);

    promptPanel.add(resultPanel);
    promptPanel.add(processPanel);

    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(objectPanel);
    this.add(promptPanel);
    this.setSize(400, 150);
  }

  void updateNums(int diamondNum, int rubyNum, int sapphireNum, int arrowNum) {
    diaLabel.setText(String.format("%s", diamondNum));
    rubyLabel.setText(String.format("%s", rubyNum));
    sapphireLabel.setText(String.format("%s", sapphireNum));
    arrowLabel.setText(String.format("%s", arrowNum));
  }

  void enableShoot(Direction direction) {
    this.shootDir.setText(String.format("Shoot dir: %s", direction.toString()));
    this.shootDis.setEnabled(true);
    this.shootBtn.setEnabled(true);
  }

  void disableShoot() {
    this.shootDir.setText("");
    this.shootDis.setEnabled(false);
    this.shootBtn.setEnabled(false);
  }

  void setShootResult(Boolean isHit) {
    if (isHit) {
      shootResult.setText("You heard a great howl in the distance.");
    } else {
      shootResult.setText("You shoot the arrow into the darkness.");
    }
  }

  void setRunoutArrowPrompt() {
    shootResult.setText("You have run out of arrow, Please explore to find more.");
  }
}
