package com.pixinator.mbtool.ui.mainframe.selection;

import com.pixinator.mbtool.mod.Item;
import com.pixinator.mbtool.mod.Mod;
import com.pixinator.mbtool.mod.ModEvent;
import com.pixinator.mbtool.mod.ModListener;
import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.ui.mainframe.ModePanel;
import com.pixinator.mbtool.ui.mainframe.editmode.ItemPanel;
import com.pixinator.mbtool.ui.mainframe.selection.itemspanel.ItemsCellRenderer;
import com.pixinator.mbtool.ui.widget.TextBox;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ItemsPanel extends ModePanel implements ListSelectionListener, ModListener {
  // ############################################################
  // # CONSTRUCTORS
  // ############################################################

  private ItemPanel pnlItem;

  private TextBox txtSearch;
  private JScrollPane pnlList;
  private JList<Item> lstItems;
  private DefaultListModel<Item> model;

  public ItemsPanel(MainFrame frame, ItemPanel pnlItem) {
    super(frame);

    this.pnlItem = pnlItem;

    Mod.addModListener(this);
    this.updateItemsList();

    if (this.model.size() > 0 && this.lstItems.getSelectedValue() == null) {
      this.lstItems.setSelectedIndex(0);
    }
  }

  // ############################################################
  // # METHODS
  // ############################################################

  private void updateItemsList() {
    this.model.clear();

    for (int i = 0; i < Mod.itemsCount(); i++) {
      Item item = Mod.getItem(i);
      this.model.addElement(item);
    }
  }

  private void filter() {
    // this.model.clear();

    for (int i = 0; i < this.model.size(); i++) {
      Item item = this.model.getElementAt(i);
    }
  }

  public void txtSearch_TextChanged(Object sender) {
    this.filter();
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    Item curItem = this.pnlItem.getItem();
    Item newItem = this.lstItems.getSelectedValue();

    if (curItem == null || !this.pnlItem.isItemEdited()) {
      this.pnlItem.setItem(newItem);
    } else {
      for (int i = 0; i < this.model.getSize(); i++) {
        Item item = this.model.getElementAt(i);
        if (item == curItem) {
          this.lstItems.setSelectedIndex(i);
          break;
        }
      }

      this.pnlItem.highlight(1000, Color.RED);
    }
  }

  @Override
  public void onItemChange(ModEvent e) {
    int id = this.lstItems.getSelectedValue().getID();
    this.updateItemsList();

    Item item = Mod.getItemByID(id);
    if (item != null) {
      this.lstItems.setSelectedValue(item, true);
    } else {
      this.lstItems.setSelectedValue(this.pnlItem.getItem(), true);
    }
  }

  @Override
  public void onBlockChange(ModEvent e) {}

  @Override
  protected void initWidgets() {
    this.txtSearch = new TextBox();
    this.txtSearch.setTextChanged("txtSearch_TextChanged", this);
    super.add(this.txtSearch);

    this.model = new DefaultListModel<Item>();
    this.lstItems = new JList<Item>(this.model);
    this.lstItems.setCellRenderer(new ItemsCellRenderer());
    this.lstItems.setFixedCellHeight(32);
    this.lstItems.addListSelectionListener(this);

    this.pnlList = new JScrollPane(this.lstItems);
    super.add(this.pnlList);
  }

  @Override
  public void componentResized() {
    this.txtSearch.setLocation(12, 12);
    this.txtSearch.setSize(super.getWidth() - 24, 26);

    this.pnlList.setLocation(12, this.txtSearch.getY() + this.txtSearch.getHeight() + 12);
    this.pnlList.setSize(super.getWidth() - 24, super.getHeight() - this.pnlList.getY() - 12);

    this.lstItems.setLocation(0, 0);
    this.lstItems.setSize(this.pnlList.getWidth(), this.pnlList.getHeight());
  }

  @Override
  public void windowActivated() {}

}
