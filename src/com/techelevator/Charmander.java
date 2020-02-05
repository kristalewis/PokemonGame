package com.techelevator;


public class Charmander extends Pokemon {
	
	String[] charmanderMoves = new String[] {"Growl", "Scratch", "Ember", "Leer"};
	Integer[] charmanderMoveValues = new Integer[] {0, 5, 7, 0};
	String[] charmanderMoveTypes = new String[] {"normal", "normal", "fire", "normal"};
	String[] charmanderWeaknesses = new String[] {"water", "rock", "ground"};
	String[] charmanderStrengths = new String[] {"fire", "grass", "ice", "bug"};
	

	public Charmander() {
		super("Charmander", 23, "fire");
		super.setMoves(charmanderMoves);
		super.setMovesDamageMap(charmanderMoves, charmanderMoveValues);
		super.setMovesTypeMap(charmanderMoves, charmanderMoveTypes);
		super.setWeaknesses(charmanderWeaknesses);
		super.setStrengths(charmanderStrengths);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
