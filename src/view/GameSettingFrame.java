package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.GuiController;

class GameSettingFrame extends JFrame {
  public GameSettingFrame(GuiController guiController) {
    this.setLayout(new GridLayout(7, 2));
    JLabel rowNum = new JLabel("number of rows");
    SpinnerModel rowSpinnerModel = new SpinnerNumberModel(5, 5, 999, 1);
    JSpinner rowSpinner = new JSpinner(rowSpinnerModel);


    JLabel colNum = new JLabel("number of columns");
    SpinnerModel colSpinnerModel = new SpinnerNumberModel(5, 5, 999, 1);
    JSpinner colSpinner = new JSpinner(colSpinnerModel);


    JLabel connectivity = new JLabel("number of interconnectivity");
    SpinnerModel connectivitySpinnerModel = new SpinnerNumberModel(0, 0, 999, 1);
    JSpinner connectivitySpinner = new JSpinner(connectivitySpinnerModel);


    JLabel isWrap = new JLabel("wrapping or not");
    JCheckBox wrapChkBox = new JCheckBox();

    JLabel percentage = new JLabel("percentage of treasure & arrow");
    SpinnerModel perSpinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
    JSpinner perSpinner = new JSpinner(perSpinnerModel);

    JLabel monsterNum = new JLabel("number of monsters");
    SpinnerModel monsterNumSpinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
    JSpinner monsterNumSpinner = new JSpinner(monsterNumSpinnerModel);

    JButton applyBtn = new JButton("apply");
    JButton cancelBtn = new JButton("cancel");

    JFrame thisFrame = this;
    applyBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //TODO: TO apply custom setting.
        int rows = (int) rowSpinner.getValue();
        int cols = (int) colSpinner.getValue();
        int interconnectivity = (int) connectivitySpinner.getValue();
        boolean isWrap = wrapChkBox.isSelected();
        int per = (int) perSpinner.getValue();
        int monsterNum = (int) monsterNumSpinner.getValue();

        guiController.setCustomValues(rows, cols, interconnectivity, isWrap, per, monsterNum);
        guiController.restartGame();

        thisFrame.setVisible(false);
      }
    });


    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        thisFrame.setVisible(false);
      }
    });

    this.add(rowNum);
    this.add(rowSpinner);

    this.add(colNum);
    this.add(colSpinner);

    this.add(connectivity);
    this.add(connectivitySpinner);

    this.add(percentage);
    this.add(perSpinner);

    this.add(monsterNum);
    this.add(monsterNumSpinner);

    this.add(isWrap);
    this.add(wrapChkBox);

    this.add(applyBtn);
    this.add(cancelBtn);

    this.setSize(200, 300);
  }
}
