package com.pixinator.mbtool.mod;

import com.pixinator.mbtool.mod.item.Equip;
import com.pixinator.mbtool.mod.item.Food;
import com.pixinator.mbtool.mod.item.Sword;

public final class Item {
	// ############################################################
	// # VARIABLES
	// ############################################################

	private int id;
	private String name;

	private Equip equip;
	private Food food;
	private Sword sword;

	private String itemInfo;
	private boolean equipAllowed;
	private boolean foodAllowed;
	private boolean swordAllowed;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public Item(int id, String name, Equip equip, Food food, Sword sword) {
		this.id = id;
		this.name = name;

		this.equip = equip;
		this.food = food;
		this.sword = sword;

		this.itemInfo = "";
		this.equipAllowed = true;
		this.foodAllowed = true;
		this.swordAllowed = true;

		switch (this.id) {
		case 262: // arrow
			this.itemInfo = "In original Minebuilder item 262(arrow) is used by a 385(bow).";
			break;
		case 267: // iron sword
			this.itemInfo = "In original Minebuilder item 267(iron sword) makes damage.";
			this.food = null;
			this.sword = new Sword(4);
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 268: // bat
			this.itemInfo = "In original Minebuilder item 268(bat) makes damage.";
			this.food = null;
			this.sword = new Sword(2);
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 272: // stone sword
			this.itemInfo = "In original Minebuilder item 272(stone sword) makes damage.";
			this.food = null;
			this.sword = new Sword(3);
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 276: // diamond sword
			this.itemInfo = "In original Minebuilder item 267(diamond sword) makes damage.";
			this.food = null;
			this.sword = new Sword(6);
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 283: // gold sword
			this.itemInfo = "In original Minebuilder item 283(gold sword) makes damage.";
			this.food = null;
			this.sword = new Sword(5);
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 300: // plow
			this.itemInfo = "In original Minebuilder item 300(plow) is used to make 60(Farmland).";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 325: // bucket
			this.itemInfo = "In original Minebuilder item 325(bucket) is used to collect 8(water) or 10(lava).";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 326: // bucket
			this.itemInfo = "In original Minebuilder item 326(bucket) is used to place 8(water).";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 327: // bucket
			this.itemInfo = "In original Minebuilder item 327(bucket) is used to place 10(lava).";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 385: // bow
			this.itemInfo = "In original Minebuilder item 385(bow) is used to shoot 262(arrow).";
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 403: // potion
			this.itemInfo = "In original Minebuilder item 403(potion) is used to restore health.";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 404: // saddle
			this.itemInfo = "In original Minebuilder item 404(saddle) is used to ride an unicorn.";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 405: // cart
			this.itemInfo = "In original Minebuilder item 405(cart) is used to place a cart on 105(Rail).";
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 412: // brush
			this.itemInfo = "In original Minebuilder item 412(brush) is used to draw on 116(painting).";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 413: // carrot soup
			this.itemInfo = "In original Minebuilder item 413(carrot soup) is used to restore energy.";
			this.food = new Food(180);
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 416: // Mjolnir
			this.itemInfo = "In original Minebuilder item 416(Mjolnir) is used to remove blocks in creative mode.";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 418: // compost
			this.itemInfo = "In original Minebuilder item 418(compost) is used to do actions on blocks(e.g. 59(Wheat) to accelerate growth).";
			this.food = null;
			this.sword = null;
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 470: // Dream Disk
			this.itemInfo = "In original Minebuilder item 470(Dream Disk) is used to visit Dreamworld.";
			break;
		case 471: // Nightmare Disk
			this.itemInfo = "In original Minebuilder item 471(Dream Disk) is used to visit Nightmare.";
			break;
		case 473: // Ancient Sword
			this.itemInfo = "In original Minebuilder item 473(Ancient Sword) makes damage.";
			this.food = null;
			this.sword = new Sword(7);
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		case 474: // Broken Ancient Sword
			this.itemInfo = "In original Minebuilder item 474(Broken Ancient Sword) makes damage.";
			this.food = null;
			this.sword = new Sword(3);
			this.foodAllowed = false;
			this.swordAllowed = false;
			break;
		}
	}

	// ############################################################
	// # GETTERS, SETTERS
	// ############################################################

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Equip getEquip() {
		return this.equip;
	}

	public Food getFood() {
		return this.food;
	}

	public Sword getSword() {
		return this.sword;
	}

	public String getItemInfo() {
		return this.itemInfo;
	}

	public boolean isEquipAllowed() {
		return this.equipAllowed;
	}

	public boolean isFoodAllowed() {
		return this.foodAllowed;
	}

	public boolean isSwordAllowed() {
		return this.swordAllowed;
	}

}
