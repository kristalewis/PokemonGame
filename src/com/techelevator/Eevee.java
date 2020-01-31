package com.techelevator;

public class Eevee extends Pokemon {

	String[] eeveeMoves = new String[] {"Tackle", "Tail Whip", "Quick Attack", "Growl"};
	Integer[] eeveeMoveValues = new Integer[] {5, 0, 6, 0};
	
	public Eevee() {
		super("Eevee", 24);
		super.setMoves(eeveeMoves);
		super.setMovesMap(eeveeMoves, eeveeMoveValues);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
