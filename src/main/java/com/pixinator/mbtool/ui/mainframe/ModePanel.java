package com.pixinator.mbtool.ui.mainframe;

import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.ui.widget.Panel;

public abstract class ModePanel extends Panel {
	// ############################################################
	// # VARIABLES
	// ############################################################

	protected MainFrame frame;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public ModePanel(MainFrame frame) {
		super();

		this.frame = frame;

		this.initWidgets();
	}

	// ############################################################
	// # METHODS
	// ############################################################

	protected abstract void initWidgets();

	public abstract void componentResized();

	public abstract void windowActivated();
}
