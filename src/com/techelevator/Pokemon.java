package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Pokemon {

	private String name;
	private int hP;
	String[] moves = new String[4];
	Map<String, Integer> moveDamageMap = new HashMap<String, Integer>();
	
	public Pokemon (String name, int hP) {
		this.name = name;
		this.hP = hP;
	}
	
	public String getName() {
		return name;
	}
	
	public int gethP() {
		return hP;
	}
	
	public void setMoves(String[] moves) {
		for(int i = 0; i < 4; i++) {
			this.moves[i] = moves[i];
		}
	}
	
	public void setMovesMap(String[] moves, Integer[] moveValues) {
		for(int i = 0; i < 4; i++) {
			moveDamageMap.put(moves[i], moveValues[i]);
		}	
	}
	
	Random rand = new Random();
	public String fight() {
		int randomMove = rand.nextInt(4);
		String movePicked = moves[randomMove];
		return movePicked;
	}
	
	public String[] getMoves() {
		return moves;
	}

	
	
}
