package com.pixinator.mbtool.ui.mainframe.editmode;

import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.ui.mainframe.ModePanel;
import com.pixinator.mbtool.ui.widget.Label;

import java.awt.*;

public class BlockPanel extends ModePanel {
  // ############################################################
  // # VARIABLES
  // ############################################################

  private Label lblTest;

  // ############################################################
  // # CONSTRUCTORS
  // ############################################################

  public BlockPanel(MainFrame frame) {
    super(frame);
  }

  @Override
  protected void initWidgets() {
    this.lblTest = new Label();
    this.lblTest.setForeground(Color.BLACK);
    this.lblTest.setText("Blocks");
    super.add(this.lblTest);
  }

  @Override
  public void componentResized() {
    this.lblTest.setLocation(12, 12);
    this.lblTest.setSize(this.lblTest.getPreferredSize().width, this.lblTest.getPreferredSize().height);
  }

  @Override
  public void windowActivated() {
  }

}
