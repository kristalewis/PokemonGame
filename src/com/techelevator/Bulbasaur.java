package com.techelevator;

public class Bulbasaur extends Pokemon {

	String[] bulbasaurMoves = new String[] {"Tackle", "Growl", "Leech Seed", "Vine Whip"};
	Integer[] bulbasaurMoveValues = new Integer[] {5, 0, 1, 7};
	String[] bulbasaurMoveTypes = new String[] {"normal", "normal", "grass", "grass"};
	String[] bulbasaurWeaknesses = new String[] {"fire", "ice", "flying", "bug"};
	String[] bulbasaurStrengths = new String[] {"water", "grass", "electric", "ground"};
	
	public Bulbasaur() {
		super("Bulbasaur", 24, "grass");
		super.setMoves(bulbasaurMoves);
		super.setMovesDamageMap(bulbasaurMoves, bulbasaurMoveValues);
		super.setMovesTypeMap(bulbasaurMoves, bulbasaurMoveTypes);
		super.setWeaknesses(bulbasaurWeaknesses);
		super.setStrengths(bulbasaurStrengths);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
