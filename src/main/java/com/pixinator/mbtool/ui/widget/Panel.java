package com.pixinator.mbtool.ui.widget;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.pixinator.mbtool.utils.Utils;

public class Panel extends JPanel implements Runnable {
	// ############################################################
	// # VARIABLES
	// ############################################################

	private Thread thread;
	private Border oldBorder;
	private int ms;
	private Color color;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public Panel() {
		super();

		super.setLayout(null);
		super.setOpaque(true);
		super.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Utils.COL_D_BORDER_M));

		this.thread = new Thread(this);
	}

	public void highlight(int ms, Color color) {
		this.ms = ms;
		this.color = color;

		if (!this.thread.isAlive()) {
			this.thread = new Thread(this);
			this.thread.start();
		}
	}

	@Override
	public void run() {
		while (this.ms > 0) {
			super.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, this.color));
			this.ms -= 50;

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.setBorder(this.oldBorder);
	}

	@Override
	public void setBorder(Border border) {
		super.setBorder(border);

		this.oldBorder = border;
	}

}
