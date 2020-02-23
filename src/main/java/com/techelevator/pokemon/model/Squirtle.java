package com.techelevator.pokemon.model;

public class Squirtle extends Pokemon {

	String[] squirtleMoves = new String[] {"Tackle", "Tail Whip", "Bubble", "Water Gun"};
	int[] squirtleMoveValues = new int[] {5, 0, 7, 8};
	String[] squirtleMoveTypes = new String[] {"normal", "normal", "water", "water"};
	String[] squirtleWeaknesses = new String[] {"electric", "grass"};
	String[] squirtleStrengths = new String[] {"water", "fire", "ice"};
	
//	public Squirtle() {
//		super("Squirtle", 24, "water");
//		super.setWeaknesses(squirtleWeaknesses);
//		super.setStrengths(squirtleStrengths);
//		super.createMoveList(squirtleMoves, squirtleMoveTypes, squirtleMoveValues);
//	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
