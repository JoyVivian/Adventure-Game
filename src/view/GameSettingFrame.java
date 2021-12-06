package view;

import java.awt.*;

import javax.swing.*;

public class GameSettingFrame extends JFrame {
  public GameSettingFrame() {
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
