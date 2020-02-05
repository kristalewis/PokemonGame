package com.techelevator;

public class Eevee extends Pokemon {

	String[] eeveeMoves = new String[] {"Tackle", "Tail Whip", "Quick Attack", "Growl"};
	Integer[] eeveeMoveValues = new Integer[] {5, 0, 6, 0};
	String[] eeveeMoveTypes = new String[] {"normal", "normal", "normal", "normal"};
	String[] eeveeWeaknesses = new String[] {"fighting"};
	String[] eeveeStrengths = new String[] {"none"};
	
	public Eevee() {
		super("Eevee", 24, "normal");
		super.setMoves(eeveeMoves);
		super.setMovesDamageMap(eeveeMoves, eeveeMoveValues);
		super.setMovesTypeMap(eeveeMoves, eeveeMoveTypes);
		super.setWeaknesses(eeveeWeaknesses);
		super.setStrengths(eeveeStrengths);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	
}
