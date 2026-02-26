package com.pixinator.mbtool.ui.mainframe;

import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;

import com.pixinator.mbtool.Utility;
import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.ui.widget.Label;
import com.pixinator.mbtool.ui.widget.Panel;

public class HeaderPanel extends Panel {
	// ############################################################
	// # VARIABLES
	// ############################################################

	// private MainFrame frame;

	private Label lblHeader;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public HeaderPanel(MainFrame frame) {
		super();
		super.setBackground(Utility.COL_E_MAIN);
		super.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Utility.COL_E_MAIN.darker()));

		// this.frame = frame;

		this.initWidgets();
	}

	// ############################################################
	// # METHODS
	// ############################################################

	private void initWidgets() {
		this.lblHeader = new Label();
		this.lblHeader.setText("-");
		super.add(this.lblHeader);
	}

	public void componentResized(ComponentEvent e) {
		this.lblHeader.setLocation(12, 0);
		this.lblHeader.setSize(this.lblHeader.getPreferredSize().width, super.getHeight());
	}

	public void setHeader(String header) {
		this.lblHeader.setText(header);
	}
}
