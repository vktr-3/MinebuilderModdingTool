package com.pixinator.mbtool.mod.item;

public final class Equip {
	// ############################################################
	// # CONSTANTS
	// ############################################################

	public static final String HAT = "hat";
	public static final String CHEST = "chest";
	public static final String LEGS = "legs";
	public static final String BOOTS = "boots";
	public static final String BACKPACK = "backpack";
	public static final String RING = "ring";

	// ############################################################
	// # VARIABLES
	// ############################################################

	private String type;

	private int attack;
	private int defense;
	private int energy;
	private int flyingSpeed;
	private int health;
	private int jumpHeight;
	private int miningSpeed;
	private int speed;

	// ############################################################
	// # CONSTRUCTORS
	// ############################################################

	public Equip(String type, int attack, int defense, int energy, int flyingSpeed, int health, int jumpHeight,
			int miningSpeed, int speed) {
		this.type = type;

		this.attack = attack;
		this.defense = defense;
		this.energy = energy;
		this.flyingSpeed = flyingSpeed;
		this.health = health;
		this.jumpHeight = jumpHeight;
		this.miningSpeed = miningSpeed;
		this.speed = speed;
	}

	// ############################################################
	// # GETTERS, SETTERS
	// ############################################################

	public String getType() {
		return this.type;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getDefense() {
		return this.defense;
	}

	public int getEnergy() {
		return this.energy;
	}

	public int getFlyingSpeed() {
		return this.flyingSpeed;
	}

	public int getHealth() {
		return this.health;
	}

	public int getJumpHeight() {
		return this.jumpHeight;
	}

	public int getMiningSpeed() {
		return this.miningSpeed;
	}

	public int getSpeed() {
		return this.speed;
	}

}
