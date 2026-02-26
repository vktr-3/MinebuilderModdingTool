package com.pixinator.mbtool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.pixinator.mbtool.mod.Item;
import com.pixinator.mbtool.mod.ItemTexture;
import com.pixinator.mbtool.mod.ModEvent;
import com.pixinator.mbtool.mod.ModListener;
import com.pixinator.mbtool.mod.item.Equip;
import com.pixinator.mbtool.mod.item.Food;
import com.pixinator.mbtool.mod.item.Sword;

public class Mod {
	// ############################################################
	// # CONSTANTS
	// ############################################################

	public static final int BLOCK_ID_MIN = 1;
	public static final int BLOCK_ID_MAX = 255;
	public static final int ITEM_ID_MIN = 256;
	public static final int ITEM_ID_MAX = 4095;

	// ############################################################
	// # VARIABLES
	// ############################################################

	private static List<ModListener> listeners = new ArrayList<ModListener>();

	private static List<Item> items = new ArrayList<Item>();
	private static List<ItemTexture> itemTextures = new ArrayList<ItemTexture>();

	// ############################################################
	// # METHODS
	// ############################################################

	public static void load() throws FileNotFoundException, IOException {
		loadItems();
	}

	public static void save() {
		saveItems();
	}

	private static void onItemUpdate() {
		ModEvent e = new ModEvent();

		for (ModListener listener : listeners) {
			listener.onItemChange(e);
		}
	}

	private static void loadItems() throws FileNotFoundException, IOException {
		Element root = new XmlReader().parse(new FileReader(new File("files/Data/items.xml")));
		for (Element itemElement : root.getChildrenByName("item")) {
			int id = 0;
			String name = "";
			Equip equip = null;
			Food food = null;
			Sword sword = null;

			if (itemElement.hasAttribute("id")) {
				id = itemElement.getInt("id");
			}

			if (itemElement.hasChild("name")) {
				name = itemElement.get("name");
			}

			if (itemElement.hasChild("equip")) {
				String type = "";
				int attack = 0;
				int defense = 0;
				int energy = 0;
				int flyingSpeed = 0;
				int health = 0;
				int jumpHeight = 0;
				int miningSpeed = 0;
				int speed = 0;

				Element equipElement = itemElement.getChildByName("equip");
				if (equipElement.hasAttribute("type")) {
					type = equipElement.get("type");
				}

				if (equipElement.hasChild("attack")) {
					attack = equipElement.getInt("attack");
				}

				if (equipElement.hasChild("defense")) {
					defense = equipElement.getInt("defense");
				}

				if (equipElement.hasChild("energy")) {
					energy = equipElement.getInt("energy");
				}

				if (equipElement.hasChild("flyingspeed")) {
					flyingSpeed = equipElement.getInt("flyingspeed");
				}

				if (equipElement.hasChild("health")) {
					health = equipElement.getInt("health");
				}

				if (equipElement.hasChild("jumpheight")) {
					jumpHeight = equipElement.getInt("jumpheight");
				}

				if (equipElement.hasChild("miningspeed")) {
					miningSpeed = equipElement.getInt("miningspeed");
				}

				if (equipElement.hasChild("speed")) {
					speed = equipElement.getInt("speed");
				}

				equip = new Equip(type, attack, defense, energy, flyingSpeed, health, jumpHeight, miningSpeed, speed);
			}

			if (itemElement.hasChild("food")) {
				int value = 0;

				if (itemElement.hasChild("food")) {
					value = itemElement.getInt("food");
				}

				food = new Food(value);
			}

			if (itemElement.hasChild("sword") && food == null) {
				int value = 0;

				if (itemElement.hasChild("sword")) {
					value = itemElement.getInt("sword");
				}

				sword = new Sword(value);
			}

			// CHECK IF ID IS IN RANGE
			Item item = new Item(id, name, equip, food, sword);
			if (item.getID() >= ITEM_ID_MIN && item.getID() <= ITEM_ID_MAX) {
				items.add(item);
			}
		}

		sortItems();
	}

	private static void saveItems() {

	}

	private static void sortItems() {
		List<Item> _items = new ArrayList<Item>(items);
		items.clear();

		while (!_items.isEmpty()) {
			Item itemToAdd = _items.remove(0);
			int i = 0;

			for (Item item : items) {
				if (item.getID() < itemToAdd.getID()) {
					i++;
				} else {
					break;
				}
			}

			items.add(i, itemToAdd);
		}
	}

	public static void replaceItem(Item oldItem, Item newItem) {
		items.remove(oldItem);
		items.add(newItem);

		sortItems();
		onItemUpdate();
	}

	// ############################################################
	// # GETTERS, SETTERS
	// ############################################################

	public static void addModListener(ModListener modListener) {
		listeners.add(modListener);
	}

	public static void removeModListenr(ModListener modListener) {
		listeners.remove(modListener);
	}

	public static int itemsCount() {
		return items.size();
	}

	public static Item getItem(int index) {
		return items.get(index);
	}

	public static Item getItemByID(int id) {
		for (Item item : items) {
			if (item.getID() == id) {
				return item;
			}
		}

		return null;
	}

	public static BufferedImage getItemTexture(int id) {
		File file = new File("files/Items/item_" + id + ".png");

		for (ItemTexture itemTexture : itemTextures) {
			if (itemTexture.getID() == id && itemTexture.getLastModified() == file.lastModified()) {
				return itemTexture.getTexture();
			} else if (itemTexture.getID() == id) {
				itemTextures.remove(itemTexture);
				itemTexture.dispose();
				break;
			}
		}

		// TRY TO LOAD TEXTURE
		try {
			ItemTexture itemTexture = ItemTexture.createItemTexture(id);
			itemTextures.add(itemTexture);

			return itemTexture.getTexture();
		} catch (IOException e) {
			return null;
		}
	}

}
