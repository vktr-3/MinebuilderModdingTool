package com.pixinator.mbtool.ui.mainframe.selection.itemspanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.pixinator.mbtool.mod.Mod;
import com.pixinator.mbtool.utils.Utils;
import com.pixinator.mbtool.mod.Item;
import com.pixinator.mbtool.ui.ImageLayout;
import com.pixinator.mbtool.ui.widget.Label;
import com.pixinator.mbtool.ui.widget.Panel;
import com.pixinator.mbtool.ui.widget.PictureBox;

public class ItemsCellRenderer implements ListCellRenderer<Item> {
	private Panel pnlItem;
	private PictureBox imgTexture;
	private Label lblID;
	private Label lblName;

	public ItemsCellRenderer() {
		super();

		this.initWidgets();
	}

	private void initWidgets() {
		this.pnlItem = new Panel();
		this.pnlItem.setBackground(Color.WHITE);
		this.pnlItem.setBorder(new EmptyBorder(0, 0, 0, 0));

		this.imgTexture = new PictureBox();
		this.imgTexture.setBackground(new Color(255, 255, 255, 31));
		this.imgTexture.setLocation(4, 0);
		this.imgTexture.setSize(32, 32);
		this.imgTexture.setImageLayout(ImageLayout.ZoomFill);
		this.pnlItem.add(this.imgTexture);

		this.lblID = new Label();
		this.lblID.setText("0000");
		this.lblID.setLocation(this.imgTexture.getX() + this.imgTexture.getWidth() + 4, 0);
		this.lblID.setSize(this.lblID.getPreferredSize().width, 32);
		this.lblID.setHorizontalTextPosition(SwingConstants.RIGHT);
		this.pnlItem.add(this.lblID);

		this.lblName = new Label();
		this.lblName.setLocation(this.lblID.getX() + this.lblID.getWidth() + 4, 0);
		this.lblName.setSize(0, 32);
		this.pnlItem.add(this.lblName);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected,
			boolean cellHasFocus) {

		this.imgTexture.setBackgroundImage(Mod.getItemTexture(item.getID()));
		this.lblID.setText("" + item.getID());
		this.lblName.setText("" + item.getName());
		this.lblName.setSize(list.getWidth() - this.lblName.getX() - 4, 32);

		if (isSelected) {
			this.pnlItem.setBackground(Utils.COL_E_MAIN);
			this.lblID.setForeground(Color.WHITE);
			this.lblName.setForeground(Color.WHITE);
		} else {
			this.pnlItem.setBackground(Color.WHITE);
			this.lblID.setForeground(Color.BLACK);
			this.lblName.setForeground(Color.BLACK);
		}

		return this.pnlItem;
	}

}
