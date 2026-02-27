package com.pixinator.mbtool.ui.mainframe;

import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.ui.widget.Button;
import com.pixinator.mbtool.ui.widget.Panel;

import java.awt.event.ComponentEvent;

public class ModesPanel extends Panel {
  // ############################################################
  // # VARIABLES
  // ############################################################

  private MainFrame frame;

  private Button btnBlocks;
  private Button btnItems;

  // ############################################################
  // # CONSTRUCTORS
  // ############################################################

  public ModesPanel(MainFrame frame) {
    super();

    this.frame = frame;

    this.initWidgets();
  }

  // ############################################################
  // # METHODS
  // ############################################################

  private void initWidgets() {
    this.btnBlocks = new Button();
    this.btnBlocks.setText("Blocks");
    this.btnBlocks.setClick("btnBlocks_Click", this);
    super.add(this.btnBlocks);

    this.btnItems = new Button();
    this.btnItems.setText("Items");
    this.btnItems.setClick("btnItems_Click", this);
    super.add(this.btnItems);
  }

  public void componentResized(ComponentEvent e) {
    this.btnBlocks.setLocation(12, 12);
    this.btnBlocks.setSize(super.getWidth() - 24, 26);

    this.btnItems.setLocation(12, 50);
    this.btnItems.setSize(super.getWidth() - 24, 26);
  }

  public void btnBlocks_Click(Object sender) {
    this.frame.switchMode(Mode.Blocks);
  }

  public void btnItems_Click(Object sender) {
    this.frame.switchMode(Mode.Items);
  }

}
