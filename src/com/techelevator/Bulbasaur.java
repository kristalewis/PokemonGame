package com.techelevator;

public class Bulbasaur extends Pokemon {

	String[] bulbasaurMoves = new String[] {"Tackle", "Growl", "Leech Seed", "Vine Whip"};
	Integer[] bulbasaurMoveValues = new Integer[] {5, 0, 1, 7};
	
	public Bulbasaur() {
		super("Bulbasaur", 24);
		super.setMoves(bulbasaurMoves);
		super.setMovesMap(bulbasaurMoves, bulbasaurMoveValues);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
