package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class MessagePanel extends JPanel {
  private JLabel diaLabel;
  private JLabel rubyLabel;
  private JLabel sapphireLabel;
  private JLabel arrowLabel;

  public MessagePanel() {
    JPanel objectPanel = new JPanel();
    objectPanel.setLayout(new GridLayout(2, 4));
    JLabel ruby = new JLabel();
    try {
      File file = new File("res/images/rubie.png");
      BufferedImage image = ImageIO.read(file);
      ruby.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    JLabel diamond = new JLabel();
    try {
      File file = new File("res/images/diamond.png");
      BufferedImage image = ImageIO.read(file);
      diamond.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    JLabel emerald = new JLabel();
    try {
      File file = new File("res/images/sapphire.png");
      BufferedImage image = ImageIO.read(file);
      emerald.setIcon(new ImageIcon(image));
    } catch (IOException e) {
      throw new RuntimeException("Image loads failed.");
    }

    JLabel arrowImg = new JLabel();
    try {
      File file = new File("res/images/arrow-white.png");
      BufferedImage image = ImageIO.read(file);
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
    promptPanel.setLayout(new GridLayout(4, 1));
    JLabel shoot = new JLabel("Shoot result: ");
    JLabel shootResult = new JLabel("");

    JLabel danger = new JLabel("Danger prompt: ");
    JLabel dangerResult = new JLabel("");

    promptPanel.add(shoot);
    promptPanel.add(shootResult);
    promptPanel.add(danger);
    promptPanel.add(dangerResult);

    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(objectPanel);
    this.add(promptPanel);
    this.setSize(400, 150);
  }

  public void updateNums(int diamondNum, int rubyNum, int sapphireNum, int arrowNum) {
    diaLabel.setText(String.format("%s", diamondNum));
    rubyLabel.setText(String.format("%s", rubyNum));
    sapphireLabel.setText(String.format("%s", sapphireNum));
    arrowLabel.setText(String.format("%s", arrowNum));
  }
}
