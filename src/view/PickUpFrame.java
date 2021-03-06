package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import controller.GuiController;

class PickUpFrame extends JFrame {
  public PickUpFrame(GuiController guiController, int diamondNum,
                     int rubyNum, int sapphireNum, int arrowNum) {
    this.setLayout(new GridLayout(6, 3));

    JLabel name = new JLabel("Name");
    JLabel number = new JLabel("Number");
    JLabel whetherPick = new JLabel("Pick or not");

    this.add(name);
    this.add(number);
    this.add(whetherPick);

    JLabel diamond = new JLabel("Diamond");
    JLabel diamondNumber = new JLabel(String.format("%s", diamondNum));
    JCheckBox diaCheck = new JCheckBox();
    if (diamondNum > 0) {
      diaCheck.setSelected(true);
    } else {
      diaCheck.setEnabled(false);
    }

    this.add(diamond);
    this.add(diamondNumber);
    this.add(diaCheck);

    JLabel ruby = new JLabel("Ruby");
    JLabel rubyNumber = new JLabel(String.format("%s", rubyNum));
    JCheckBox rubyCheck = new JCheckBox();
    if (rubyNum > 0) {
      rubyCheck.setSelected(true);
    } else {
      rubyCheck.setEnabled(false);
    }

    this.add(ruby);
    this.add(rubyNumber);
    this.add(rubyCheck);

    JLabel sapphire = new JLabel("Sapphire");
    JLabel sapphireNumber = new JLabel(String.format("%s", sapphireNum));
    JCheckBox sapphireCheck = new JCheckBox();
    if (sapphireNum > 0) {
      sapphireCheck.setSelected(true);
    } else {
      sapphireCheck.setEnabled(false);
    }

    this.add(sapphire);
    this.add(sapphireNumber);
    this.add(sapphireCheck);

    JLabel arrow = new JLabel("Arrow");
    JLabel arrowNumber = new JLabel(String.format("%s", arrowNum));
    JCheckBox arrowCheck = new JCheckBox();
    if (arrowNum > 0) {
      arrowCheck.setSelected(true);
    } else {
      arrowCheck.setEnabled(false);
    }

    this.add(arrow);
    this.add(arrowNumber);
    this.add(arrowCheck);

    JButton okBtn = new JButton("Ok");
    JButton cancelBtn = new JButton("Cancel");

    JFrame pickFrame = this;

    okBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int pickDiaNum = diaCheck.isSelected() ? diamondNum : 0;
        int pickRubyNum = rubyCheck.isSelected() ? rubyNum : 0;
        int pickSapphireNum = sapphireCheck.isSelected() ? sapphireNum : 0;
        int pickArrowNum = arrowCheck.isSelected() ? arrowNum : 0;

        guiController.handlePickUp(pickDiaNum, pickRubyNum, pickSapphireNum, pickArrowNum);
        pickFrame.setVisible(false);
      }
    });

    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pickFrame.setVisible(false);
      }
    });



    this.add(okBtn);
    this.add(cancelBtn);

    this.setSize(300, 400);
    this.setVisible(false);
  }
}
