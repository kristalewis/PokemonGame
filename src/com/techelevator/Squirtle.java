package com.techelevator;

public class Squirtle extends Pokemon {

	String[] squirtleMoves = new String[] {"Tackle", "Tail Whip", "Bubble", "Water Gun"};
	Integer[] squirtleMoveValues = new Integer[] {5, 0, 7, 8};
	String[] squirtleMoveTypes = new String[] {"normal", "normal", "water", "water"};
	String[] squirtleWeaknesses = new String[] {"electric", "grass"};
	String[] squirtleStrengths = new String[] {"water", "fire", "ice"};
	
	public Squirtle() {
		super("Squirtle", 24, "water");
		super.setMoves(squirtleMoves);
		super.setMovesDamageMap(squirtleMoves, squirtleMoveValues);
		super.setMovesTypeMap(squirtleMoves, squirtleMoveTypes);
		super.setWeaknesses(squirtleWeaknesses);
		super.setStrengths(squirtleStrengths);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
