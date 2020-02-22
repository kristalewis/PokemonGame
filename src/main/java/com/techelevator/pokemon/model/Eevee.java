package com.techelevator.pokemon.model;

public class Eevee extends Pokemon {

	String[] eeveeMoves = new String[] {"Tackle", "Tail Whip", "Quick Attack", "Growl"};
	int[] eeveeMoveValues = new int[] {5, 0, 6, 0};
	String[] eeveeMoveTypes = new String[] {"normal", "normal", "normal", "normal"};
	String[] eeveeWeaknesses = new String[] {"fighting"};
	String[] eeveeStrengths = new String[] {"none"};
	
	public Eevee() {
		super("Eevee", 24, "normal");
		super.setWeaknesses(eeveeWeaknesses);
		super.setStrengths(eeveeStrengths);
		super.createMoveList(eeveeMoves, eeveeMoveTypes, eeveeMoveValues);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
