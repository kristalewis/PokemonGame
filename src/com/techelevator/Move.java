package com.techelevator;

import java.util.Random;

public class Move {

	private String name;
	private String elementType;
	private int damage;
	
	public String getName() {
		return name;
	}

	public String getElementType() {
		return elementType;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Move(String name, String elementType, int damage) {
		this.name = name;
		this.elementType = elementType;
		this.damage = damage;
	}
	
	public boolean isNotVeryEffective(Pokemon foe) {
		boolean result = false;
		for (String type : foe.getStrengths()) {
			if (this.elementType.equals(type)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public boolean isSuperEffective(Pokemon foe) {
		boolean result = false;
		for (String type : foe.getWeaknesses()) {
			if (this.elementType.equals(type)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public boolean isCriticalHit() {
		boolean result = false;
		Random criticalHit = new Random();
		if (criticalHit.nextInt(45) == 0) {
			result = true;
		}
		return result;
	}
	
	public boolean isAttackLoweringMove() {
		return this.name.equals("Growl");
	}
	
	public boolean isDefenseLoweringMove() {
		return this.name.equals("Tail Whip") || this.name.equals("Leer");
	}

	public boolean attackFailed() {
		boolean result = false;
		Random attackFailed = new Random();
		if (attackFailed.nextInt(20) == 0) {
			result = true;
		}
		return result;
	}
	
	public boolean attackMissed() {
		boolean result = false;
		Random attackMissed = new Random();
		if (attackMissed.nextInt(20) == 0) {
			result = true;
		}
		return result;
	}

}
