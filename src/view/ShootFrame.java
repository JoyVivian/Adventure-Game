package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import game.Direction;

public class ShootFrame extends JFrame {
  private JLabel shootDirInfo;

  public ShootFrame() {
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JPanel notiPanel = new JPanel();
    notiPanel.setLayout(new GridLayout(5, 2));
    JLabel descriptionKey = new JLabel("Press key below to shoot");
    JLabel empty = new JLabel("");
    JLabel wkey = new JLabel("W");
    JLabel wkeyInfo = new JLabel("North");
    JLabel skey = new JLabel("S");
    JLabel skeyInfo = new JLabel("South");
    JLabel akey = new JLabel("A");
    JLabel akeyInfo = new JLabel("West");
    JLabel dkey = new JLabel("D");
    JLabel dkeyInfo = new JLabel("East");

    notiPanel.add(descriptionKey);
    notiPanel.add(empty);
    notiPanel.add(wkey);
    notiPanel.add(wkeyInfo);
    notiPanel.add(skey);
    notiPanel.add(skeyInfo);
    notiPanel.add(akey);
    notiPanel.add(akeyInfo);
    notiPanel.add(dkey);
    notiPanel.add(dkeyInfo);

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new GridLayout(3, 2));

    JLabel shootDir = new JLabel("Shoot Direction");
    shootDirInfo = new JLabel("");

    JLabel shootDis = new JLabel("Shoot Distance");
    JComboBox shootDisContent = new JComboBox<Integer>();
    shootDisContent.addItem(1);
    shootDisContent.addItem(2);
    shootDisContent.addItem(3);
    shootDisContent.addItem(4);
    shootDisContent.addItem(5);

    JButton okBtn = new JButton("Ok");
    JButton cancelBtn = new JButton("Cancel");

    infoPanel.add(shootDir);
    infoPanel.add(shootDirInfo);

    infoPanel.add(shootDis);
    infoPanel.add(shootDisContent);

    infoPanel.add(okBtn);
    infoPanel.add(cancelBtn);

    this.add(notiPanel);
    this.add(infoPanel);
    this.setSize(300, 400);

    JFrame frame = this;

    okBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Process shoot");
      }
    });

    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
      }
    });
  }

  public void setDir(Direction direction) {
    shootDirInfo.setText(direction.toString());
  }
}
