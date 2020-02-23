package com.techelevator.pokemon.model;

import java.util.Random;

public class Move {

	private int moveId;
	private String name;
	private String elementType;
	private String moveType;
	private int damage;
	private boolean isCriticalHit = false;
	
	public int getMoveId() {
		return moveId;
	}
	
	public void setMoveId(int moveId) {
		this.moveId = moveId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getElementType() {
		return elementType;
	}
	
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	
	public String getMoveType() {
		return moveType;
	}
	
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public Move() {
		
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
		return this.isCriticalHit;
	}
	
	public void rollForCriticalHit() {
		boolean result = false;
		Random criticalHit = new Random();
		if (criticalHit.nextInt(35) == 0) {
			result = true;
		}
		this.isCriticalHit = result;
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
		if (attackFailed.nextInt(15) == 0) {
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
