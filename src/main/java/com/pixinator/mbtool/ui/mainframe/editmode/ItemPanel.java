package com.pixinator.mbtool.ui.mainframe.editmode;

import java.awt.Color;

import com.pixinator.mbtool.mod.Mod;
import com.pixinator.mbtool.mod.Item;
import com.pixinator.mbtool.mod.item.Equip;
import com.pixinator.mbtool.mod.item.Food;
import com.pixinator.mbtool.mod.item.Sword;
import com.pixinator.mbtool.ui.ImageLayout;
import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.ui.mainframe.ModePanel;
import com.pixinator.mbtool.ui.widget.Button;
import com.pixinator.mbtool.ui.widget.IntTextBox;
import com.pixinator.mbtool.ui.widget.Label;
import com.pixinator.mbtool.ui.widget.Panel;
import com.pixinator.mbtool.ui.widget.PictureBox;
import com.pixinator.mbtool.ui.widget.TextBox;

public class ItemPanel extends ModePanel {
	// ############################################################
	// # VARIABLES
	// ############################################################

	private Button btnSubmit;
	private Button btnReset;

	private Panel pnlBasic;
	private PictureBox imgItem;
	private Label lblID;
	private IntTextBox txtID;
	private Button btnGenID;
	private Label lblName;
	private TextBox txtName;

	private Panel pnlEquip;
//	private ComboBox cmbType;
	private Label lblAttack;
	private IntTextBox txtAttack;
	private Label lblDefense;
	private IntTextBox txtDefense;
	private Label lblEnergy;
	private IntTextBox txtEnergy;
	private Label lblFlyingSpeed;
	private IntTextBox txtFlyingSpeed;
	private Label lblHealth;
	private IntTextBox txtHealth;
	private Label lblJumpHeight;
	private IntTextBox txtJumpHeight;
	private Label lblMiningSpeed;
	private IntTextBox txtMiningSpeed;
	private Label lblSpeed;
	private IntTextBox txtSpeed;

	private Panel pnlFood;
	private Label lblFood;
	private IntTextBox txtFood;

	private Panel pnlSword;
	private Label lblSword;
	private IntTextBox txtSword;

	private Item item;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public ItemPanel(MainFrame frame) {
		super(frame);
	}

	// ############################################################
	// # METHODS
	// ############################################################

	public void txtID_TextChanged(Object sender) {
		if (this.txtID.isValid()) {
			int id = Integer.parseInt(this.txtID.getText());
			this.imgItem.setBackgroundImage(Mod.getItemTexture(id));
		}
	}

	public void btnGenID(Object sender) {

	}

	public void btnSubmit_Click(Object sender) {
		if (this.isItemEdited()) {
			Item oldItem = this.item;

			int id = Integer.parseInt(this.txtID.getText());
			String name = this.txtName.getText();
			Equip equip = null;
			Food food = null;
			Sword sword = null;

			Item newItem = new Item(id, name, equip, food, sword);

			this.setItem(newItem);
			Mod.replaceItem(oldItem, newItem);
		}
	}

	public void btnReset_Click(Object sender) {
		this.setItem(this.item);
	}

	@Override
	protected void initWidgets() {
		this.btnSubmit = new Button();
		this.btnSubmit.setClick("btnSubmit_Click", this);
		super.add(this.btnSubmit);

		this.btnReset = new Button();
		this.btnReset.setClick("btnReset_Click", this);
		super.add(this.btnReset);

		this.imgItem = new PictureBox();
		this.imgItem.setImageLayout(ImageLayout.ZoomFill);
		super.add(this.imgItem);

		this.lblID = new Label();
		this.lblID.setForeground(Color.BLACK);
		this.lblID.setText("ID: ");
		super.add(this.lblID);

		this.txtID = new IntTextBox();
		this.txtID.setNullable(false);
		this.txtID.setMinValue(Mod.ITEM_ID_MIN);
		this.txtID.setMaxValue(Mod.ITEM_ID_MAX);
		this.txtID.setTextChanged("txtID_TextChanged", this);
		super.add(this.txtID);

		this.lblName = new Label();
		this.lblName.setForeground(Color.BLACK);
		this.lblName.setText("Name: ");
		super.add(this.lblName);

		this.txtName = new TextBox();
		super.add(this.txtName);
	}

	@Override
	public void componentResized() {
		this.btnSubmit.setLocation(12, 12);
		this.btnSubmit.setSize(26, 26);

		this.btnReset.setLocation(this.btnSubmit.getX() + this.btnSubmit.getWidth() + 12, 12);
		this.btnReset.setSize(26, 26);

		this.imgItem.setLocation(12, this.btnSubmit.getY() + this.btnSubmit.getHeight() + 12);
		this.imgItem.setSize(64, 64);

		this.lblID.setLocation(this.imgItem.getX() + this.imgItem.getWidth() + 12, this.imgItem.getY());
		this.lblID.setSize(this.lblID.getPreferredSize().width, 26);

		this.txtID.setLocation(this.lblID.getX() + this.lblID.getWidth() + 12, this.lblID.getY());
		this.txtID.setSize(64, 26);

		this.lblName.setLocation(this.imgItem.getX() + this.imgItem.getWidth() + 12,
				this.lblID.getY() + this.lblID.getHeight() + 12);
		this.lblName.setSize(this.lblName.getPreferredSize().width, 26);

		this.txtName.setLocation(this.lblName.getX() + this.lblName.getWidth() + 12, this.lblName.getY());
		this.txtName.setSize(128, 26);
	}

	@Override
	public void windowActivated() {
		if (this.item != null) {
			if (this.txtID.isValid()) {
				int id = Integer.parseInt(this.txtID.getText());
				this.imgItem.setBackgroundImage(Mod.getItemTexture(id));
			} else {
				this.imgItem.setBackgroundImage(Mod.getItemTexture(this.item.getID()));
			}
		}
	}

	// ############################################################
	// # GETTERS, SETTERS
	// ############################################################

	public boolean isItemEdited() {
		if (this.item == null) {
			return false;
		}

		boolean edited = false;

		edited |= !Integer.toString(this.item.getID()).equals(this.txtID.getText());
		edited |= !this.item.getName().equals(this.txtName.getText());

		return edited;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		if (item == null) {
			return;
		}

		this.item = item;

		this.imgItem.setBackgroundImage(Mod.getItemTexture(this.item.getID()));
		this.txtID.setText("" + this.item.getID());
		this.txtName.setText(this.item.getName());

		// SET ID BLACKLIST
		this.txtID.clearBlackList();
		for (int i = 0; i < Mod.itemsCount(); i++) {
			Item _item = Mod.getItem(i);

			if (_item == this.item) {
				continue;
			} else {
				this.txtID.addBlackListItem(_item.getID());
			}
		}
	}

}
