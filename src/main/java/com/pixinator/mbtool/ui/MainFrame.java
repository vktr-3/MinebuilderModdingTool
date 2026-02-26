package com.pixinator.mbtool.ui;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.pixinator.mbtool.Utility;
import com.pixinator.mbtool.ui.mainframe.FooterPanel;
import com.pixinator.mbtool.ui.mainframe.HeaderPanel;
import com.pixinator.mbtool.ui.mainframe.Mode;
import com.pixinator.mbtool.ui.mainframe.ModesPanel;
import com.pixinator.mbtool.ui.mainframe.editmode.BlockPanel;
import com.pixinator.mbtool.ui.mainframe.editmode.ItemPanel;
import com.pixinator.mbtool.ui.mainframe.selection.ItemsPanel;
import com.pixinator.mbtool.ui.widget.Frame;
import com.pixinator.mbtool.ui.widget.PictureBox;

public class MainFrame extends Frame implements ComponentListener, WindowListener {
	// ############################################################
	// # VARIABLES
	// ############################################################

	private HeaderPanel pnlHeader;

	private PictureBox imgBack;
	private ModesPanel pnlModes;

	private FooterPanel pnlFooter;

	private ItemsPanel pnlItems;

	private BlockPanel pnlBlocks;
	private ItemPanel pnlItem;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public MainFrame() {
		super("Minebuilder Modding Tool", new Dimension(800, 600));
		super.addWindowListener(this);
		super.addComponentListener(this);

		this.initWidgets();
	}

	// ############################################################
	// # METHODS
	// ############################################################

	private void initWidgets() {
		this.pnlHeader = new HeaderPanel(this);
		super.add(this.pnlHeader);

		this.imgBack = new PictureBox();
		this.imgBack.setImageLayout(ImageLayout.ZoomFill);
		this.imgBack.setBackgroundImage(Utility.getBack());
		super.add(this.imgBack);

		this.pnlModes = new ModesPanel(this);
		this.imgBack.add(this.pnlModes);

		this.pnlItem = new ItemPanel(this);
		this.pnlItem.setVisible(false);
		this.imgBack.add(this.pnlItem);

		this.pnlItems = new ItemsPanel(this, this.pnlItem);
		this.pnlItems.setVisible(false);
		this.imgBack.add(this.pnlItems);

		this.pnlBlocks = new BlockPanel(this);
		this.pnlBlocks.setVisible(false);
		this.imgBack.add(this.pnlBlocks);

		this.pnlFooter = new FooterPanel(this);
		super.add(this.pnlFooter);
	}

	public void switchMode(Mode mode) {
		this.pnlBlocks.setVisible(false);
		this.pnlItems.setVisible(false);
		this.pnlItem.setVisible(false);

		switch (mode) {
		case Blocks:
			this.pnlHeader.setHeader("Blocks");
			this.pnlBlocks.setVisible(true);
			break;
		case Items:
			this.pnlHeader.setHeader("Items");
			this.pnlItems.setVisible(true);
			this.pnlItem.setVisible(true);
			break;
		}

		this.componentResized(null);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		return;
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		return;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.pnlHeader.setLocation(0, 0);
		this.pnlHeader.setSize(super.getContentWidth(), 42);
		this.pnlHeader.componentResized(e);

		this.pnlFooter.setLocation(0, super.getContentHeight() - 42);
		this.pnlFooter.setSize(super.getContentWidth(), 42);
		this.pnlFooter.componentResized(e);

		this.imgBack.setLocation(0, this.pnlHeader.getY() + this.pnlHeader.getHeight());
		this.imgBack.setSize(super.getContentWidth(),
				super.getContentHeight() - this.pnlHeader.getHeight() - this.pnlFooter.getHeight());

		this.pnlModes.setLocation(12, 12);
		this.pnlModes.setSize(256, 88);
		this.pnlModes.componentResized(e);

		this.pnlItems.setLocation(12, this.pnlModes.getY() + this.pnlModes.getHeight() + 12);
		this.pnlItems.setSize(256, this.imgBack.getHeight() - this.pnlItems.getY() - 12);
		this.pnlItems.componentResized();

		this.pnlBlocks.setLocation(this.pnlModes.getX() + this.pnlModes.getWidth() + 12, 12);
		this.pnlBlocks.setSize(this.imgBack.getWidth() - this.pnlBlocks.getX() - 12, this.imgBack.getHeight() - 24);
		this.pnlBlocks.componentResized();

		this.pnlItem.setLocation(this.pnlModes.getX() + this.pnlModes.getWidth() + 12, 12);
		this.pnlItem.setSize(this.imgBack.getWidth() - this.pnlItem.getX() - 12, this.imgBack.getHeight() - 24);
		this.pnlItem.componentResized();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		return;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		this.pnlItems.windowActivated();
		this.pnlItem.windowActivated();
		this.pnlBlocks.windowActivated();
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("SAVE CHANGES BEFORE RIP?");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

}
