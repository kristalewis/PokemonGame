package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class Pokemon {

	private String name;
	private int hP;
	private String type;
	String[] moves = new String[4];
	Map<String, Integer> moveDamageMap = new HashMap<String, Integer>();
	Map<String, String> moveTypeMap = new HashMap<String, String>();
	String[] types = new String[] {"bug", "dragon", "electric", "fighting", "fire", "flying", "ghost", "grass", 
								   "ground", "ice", "normal", "poison", "psychic", "rock", "water"};
	List<String> weaknesses = new ArrayList <String>();
	List<String> strengths = new ArrayList <String>();
	
	
	public Pokemon (String name, int hP, String type) {
		this.name = name;
		this.hP = hP;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int gethP() {
		return hP;
	}
	
	public String getType() {
		return type;
	}
	
	public void setMoves(String[] moves) {
		for(int i = 0; i < 4; i++) {
			this.moves[i] = moves[i];
		}
	}
	
	public void setMovesDamageMap(String[] moves, Integer[] moveValues) {
		for(int i = 0; i < 4; i++) {
			moveDamageMap.put(moves[i], moveValues[i]);
		}	
	}
	
	public void setMovesTypeMap(String[] moves, String[] moveTypes) {
		for(int i = 0; i < 4; i++) {
			moveTypeMap.put(moves[i], moveTypes[i]);
		}	
	}
	
	public void setWeaknesses(String[] weaknesses) {
		for(int i = 0; i < weaknesses.length; i++) {
			this.weaknesses.add(weaknesses[i]);
		}
	}
	
	public void setStrengths(String[] strengths) {
		for(int i = 0; i < strengths.length; i++) {
			this.strengths.add(strengths[i]);
		}
	}
	
	Random rand = new Random();
	int randomMove;
	public String fight() {
		randomMove = rand.nextInt(4);
		String movePicked = moves[randomMove];
		return movePicked;
	}
	
	public String[] getMoves() {
		return moves;
	}
	
	public List<String> getWeaknesses() {
		return weaknesses;
	}

	public List<String> getStrengths() {
		return strengths;
	}
	
	public boolean usedDefenseLoweringMove(String move) {
		boolean result = false;
		if (move.equals("Tail Whip") || move.equals("Leer")) {
			result = true;
		}
		return result;
	}
	
	public boolean usedAttackLoweringMove(String move) {
		boolean result = false;
		if (move.equals("Growl")) {
			result = true;
		}
		return result;
	}
	
	public boolean isCriticalHit() {
		boolean result = false;
		Random criticalHit = new Random();
		if(criticalHit.nextInt(50) == 0) {
			result = true;
		}
		return result;
	}

	public boolean isSuperEffective(String move, Pokemon foe) {
		boolean result = false;
		String moveType = moveTypeMap.get(move);
		for(String type : foe.getWeaknesses()) {
			if(moveType.equals(type)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public boolean isNotVeryEffective(String move, Pokemon foe) {
		boolean result = false;
		String moveType = moveTypeMap.get(move);
		for(String type : foe.getStrengths()) {
			if(moveType.equals(type)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	
	
	
	
	
	
}
