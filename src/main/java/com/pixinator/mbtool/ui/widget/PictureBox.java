package com.pixinator.mbtool.ui.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.pixinator.mbtool.utils.Utils;
import com.pixinator.mbtool.ui.ImageLayout;

public class PictureBox extends JComponent {
	// ################################################################
	// # VARIABLES
	// ################################################################

	private BufferedImage backgroundImage;
	private ImageLayout imageLayout;

	// ################################################################
	// # CONSTRUCTORS
	// ################################################################

	public PictureBox() {
		super();

		super.setBackground(new Color(0, 0, 0, 0));

		this.setImageLayout(ImageLayout.None);
		this.setBackgroundImage(null);
	}

	// ################################################################
	// # METHODS
	// ################################################################

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(super.getBackground());
		g2.fillRect(0, 0, super.getWidth(), super.getHeight());

		if (this.backgroundImage != null) {
			switch (this.imageLayout) {
			case None:
				g2.drawImage(this.backgroundImage, 0, 0, this.backgroundImage.getWidth(),
						this.backgroundImage.getHeight(), null);
				break;
			case Tile:
				for (int x = 0; x < super.getWidth() / this.backgroundImage.getWidth(); x++) {
					for (int y = 0; y < super.getHeight() / this.backgroundImage.getHeight(); y++) {
						g2.drawImage(this.backgroundImage, x * this.backgroundImage.getWidth(),
								y * this.backgroundImage.getHeight(), this.backgroundImage.getWidth(),
								this.backgroundImage.getHeight(), null);
					}
				}
				break;
			case Center:
				g2.drawImage(this.backgroundImage, (super.getWidth() - this.backgroundImage.getWidth()) / 2,
						(super.getHeight() - this.backgroundImage.getHeight()) / 2, this.backgroundImage.getWidth(),
						this.backgroundImage.getHeight(), null);
				break;
			case Strech:
				g2.drawImage(this.backgroundImage, 0, 0, this.backgroundImage.getWidth(),
						this.backgroundImage.getHeight(), null);
				break;
			case Zoom:
				float zoomFactor = Math.max((float) this.backgroundImage.getWidth() / super.getWidth(),
						(float) this.backgroundImage.getHeight() / super.getHeight());
				g2.drawImage(this.backgroundImage,
						(int) Math.round((super.getWidth() - (this.backgroundImage.getWidth() / zoomFactor)) / 2F),
						(int) Math.round((super.getHeight() - (this.backgroundImage.getHeight() / zoomFactor)) / 2F),
						(int) Math.round(this.backgroundImage.getWidth() / zoomFactor),
						(int) Math.round(this.backgroundImage.getHeight() / zoomFactor), this);
				break;
			case ZoomFill:
				float zoomFullFactor = Math.min((float) this.backgroundImage.getWidth() / super.getWidth(),
						(float) this.backgroundImage.getHeight() / super.getHeight());
				g2.drawImage(this.backgroundImage,
						(int) Math.round((super.getWidth() - (this.backgroundImage.getWidth() / zoomFullFactor)) / 2F),
						(int) Math
								.round((super.getHeight() - (this.backgroundImage.getHeight() / zoomFullFactor)) / 2F),
						(int) Math.round(this.backgroundImage.getWidth() / zoomFullFactor),
						(int) Math.round(this.backgroundImage.getHeight() / zoomFullFactor), this);
				break;
			}
		}
	}

	// ################################################################
	// # GETTERS, SETTERS
	// ################################################################

	public BufferedImage getBackgroundImage() {
		return this.backgroundImage;
	}

	public void setBackgroundImage(BufferedImage backgroundImage) {
		if (backgroundImage != null) {
			this.backgroundImage = backgroundImage;
		} else {
			this.backgroundImage = Utils.iconNoImage;
		}

		this.repaint();
	}

	public ImageLayout getImageLayout() {
		return this.imageLayout;
	}

	public void setImageLayout(ImageLayout imageLayout) {
		this.imageLayout = imageLayout;

		this.repaint();
	}

}
