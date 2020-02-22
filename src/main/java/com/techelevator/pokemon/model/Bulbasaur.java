package com.techelevator.pokemon.model;

public class Bulbasaur extends Pokemon {

	String[] bulbasaurMoves = new String[] {"Tackle", "Growl", "Leech Seed", "Vine Whip"};
	int[] bulbasaurMoveValues = new int[] {5, 0, 2, 7};
	String[] bulbasaurMoveTypes = new String[] {"normal", "normal", "grass", "grass"};
	String[] bulbasaurWeaknesses = new String[] {"fire", "ice", "flying", "bug"};
	String[] bulbasaurStrengths = new String[] {"water", "grass", "electric", "ground"};
	
	public Bulbasaur() {
		super("Bulbasaur", 24, "grass/poison");
		super.setWeaknesses(bulbasaurWeaknesses);
		super.setStrengths(bulbasaurStrengths);
		super.createMoveList(bulbasaurMoves, bulbasaurMoveTypes, bulbasaurMoveValues);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
