package com.techelevator;

public class Pikachu extends Pokemon {

	String[] pikachuMoves = new String[] {"Thundershock", "Growl", "Tail Whip", "Quick Attack"};
	Integer[] pikachuMoveValues = new Integer[] {7, 0, 0, 6};
	String[] pikachuMoveTypes = new String[] {"electric", "normal", "normal", "normal"};
	String[] pikachuWeaknesses = new String[] {"electric", "grass", "dragon", "ground"};
	String[] pikachuStrengths = new String[] {"electric"};
	
	public Pikachu() {
		super("Pikachu", 23, "electric");
		super.setMoves(pikachuMoves);
		super.setMovesDamageMap(pikachuMoves, pikachuMoveValues);
		super.setMovesTypeMap(pikachuMoves, pikachuMoveTypes);
		super.setWeaknesses(pikachuWeaknesses);
		super.setStrengths(pikachuStrengths);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
