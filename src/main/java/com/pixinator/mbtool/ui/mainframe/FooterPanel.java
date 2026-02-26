package com.pixinator.mbtool.ui.mainframe;

import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;

import com.pixinator.mbtool.Utility;
import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.ui.widget.Label;
import com.pixinator.mbtool.ui.widget.Panel;

public class FooterPanel extends Panel {
	// ############################################################
	// # VARIABLES
	// ############################################################

	// private MainFrame frame;

	private Label lblCopyright;
	private Label lblVersion;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public FooterPanel(MainFrame frame) {
		super();
		super.setBackground(Utility.COL_E_MAIN);
		super.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Utility.COL_E_MAIN.darker()));

		// this.frame = frame;

		this.initWidgets();
	}

	// ############################################################
	// # METHODS
	// ############################################################

	private void initWidgets() {
		this.lblCopyright = new Label();
		this.lblCopyright.setText("(C) 2018 - Viktor Drei");
		super.add(this.lblCopyright);

		this.lblVersion = new Label();
		this.lblVersion.setText("Ver.: 1.0");
		super.add(this.lblVersion);
	}

	public void componentResized(ComponentEvent e) {
		this.lblCopyright.setLocation(12, 0);
		this.lblCopyright.setSize(this.lblCopyright.getPreferredSize().width, super.getHeight());

		this.lblVersion.setLocation(super.getWidth() - this.lblVersion.getPreferredSize().width - 12, 0);
		this.lblVersion.setSize(this.lblVersion.getPreferredSize().width, super.getHeight());
	}

}
