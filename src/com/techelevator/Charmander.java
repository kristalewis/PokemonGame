package com.techelevator;

public class Charmander extends Pokemon {
	
	String[] charmanderMoves = new String[] {"Growl", "Scratch", "Ember", "Leer"};
	Integer[] charmanderMoveValues = new Integer[] {0, 5, 7, 0};

	public Charmander() {
		super("Charmander", 23);
		super.setMoves(charmanderMoves);
		super.setMovesMap(charmanderMoves, charmanderMoveValues);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
