package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class MessagePanel extends JPanel {
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

    JLabel rubyNum = new JLabel("  0");
    JLabel diamondNum = new JLabel("  0");
    JLabel emeraldNum = new JLabel("  0");
    JLabel arrowNum = new JLabel("  3");

    objectPanel.add(ruby);
    objectPanel.add(diamond);
    objectPanel.add(emerald);
    objectPanel.add(arrowImg);
    objectPanel.add(rubyNum);
    objectPanel.add(diamondNum);
    objectPanel.add(emeraldNum);
    objectPanel.add(arrowNum);

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
}
