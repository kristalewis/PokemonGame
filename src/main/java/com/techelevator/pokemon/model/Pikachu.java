package com.techelevator.pokemon.model;

public class Pikachu extends Pokemon {

	String[] pikachuMoves = new String[] {"Thundershock", "Growl", "Tail Whip", "Quick Attack"};
	int[] pikachuMoveValues = new int[] {7, 0, 0, 6};
	String[] pikachuMoveTypes = new String[] {"electric", "normal", "normal", "normal"};
	String[] pikachuWeaknesses = new String[] {"ground"};
	String[] pikachuStrengths = new String[] {"electric", "flying"};
//	
//	public Pikachu() {
//		super("Pikachu", 23, "electric");
//		super.setWeaknesses(pikachuWeaknesses);
//		super.setStrengths(pikachuStrengths);
//		super.createMoveList(pikachuMoves, pikachuMoveTypes, pikachuMoveValues);
//	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
