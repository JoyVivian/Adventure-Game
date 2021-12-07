package view;

import java.awt.*;

import javax.swing.*;

class HelpFrame extends JFrame {
  public HelpFrame() {
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JPanel descriptionPanel = new JPanel();
    descriptionPanel.setLayout(new GridLayout(10, 2));

    JLabel shootKey = new JLabel("S");
    JLabel shootKeyInfo = new JLabel("shoot");

    JLabel wshootKey = new JLabel("W");
    JLabel wshootKeyInfo = new JLabel("shoot direction north.");

    JLabel ashootKey = new JLabel("A");
    JLabel ashootKeyInfo = new JLabel("Shoot direction east.");

    JLabel sshootKey = new JLabel("S");
    JLabel sshootKeyInfo = new JLabel("Shoot direction south");

    JLabel dshootKey = new JLabel("D");
    JLabel dshootKeyInfo = new JLabel("Shoot direction west");

    JLabel pickKey = new JLabel("P");
    JLabel pickKeyInfo = new JLabel("Pick up");

    JLabel northKey = new JLabel("up");
    JLabel northKeyInfo = new JLabel("Move direction north");

    JLabel southKey = new JLabel("down");
    JLabel southKeyInfo = new JLabel("Move direction south");

    JLabel eastKey = new JLabel("East");
    JLabel eastKeyInfo = new JLabel("Move direction east");

    JLabel westKey = new JLabel("West");
    JLabel westKeyInfo = new JLabel("Move direction west");

    descriptionPanel.add(shootKey);
    descriptionPanel.add(shootKeyInfo);

    descriptionPanel.add(wshootKey);
    descriptionPanel.add(wshootKeyInfo);

    descriptionPanel.add(ashootKey);
    descriptionPanel.add(ashootKeyInfo);

    descriptionPanel.add(sshootKey);
    descriptionPanel.add(sshootKeyInfo);

    descriptionPanel.add(dshootKey);
    descriptionPanel.add(dshootKeyInfo);

    descriptionPanel.add(pickKey);
    descriptionPanel.add(pickKeyInfo);

    descriptionPanel.add(northKey);
    descriptionPanel.add(northKeyInfo);

    descriptionPanel.add(southKey);
    descriptionPanel.add(southKeyInfo);

    descriptionPanel.add(eastKey);
    descriptionPanel.add(eastKeyInfo);

    descriptionPanel.add(westKey);
    descriptionPanel.add(westKeyInfo);

    JButton okBtn = new JButton("Ok");
    HelpFrame helpFrame = this;
    okBtn.addActionListener(e -> {
      helpFrame.makeUnvisible();
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.setSize(400, 30);
    buttonPanel.add(okBtn);

    this.add(descriptionPanel);
    this.add(buttonPanel);

    this.setSize(300, 250);
  }


  private void makeUnvisible() {
    this.setVisible(false);
  }
}
