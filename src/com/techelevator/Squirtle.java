package com.techelevator;

public class Squirtle extends Pokemon {

	String[] squirtleMoves = new String[] {"Tackle", "Tail Whip", "Bubble", "Water Gun"};
	Integer[] squirtleMoveValues = new Integer[] {5, 0, 7, 8};
	
	public Squirtle() {
		super("Squirtle", 24);
		super.setMoves(squirtleMoves);
		super.setMovesMap(squirtleMoves, squirtleMoveValues);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
