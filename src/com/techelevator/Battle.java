package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

	private List<Pokemon> chosenPokemon = new ArrayList<Pokemon>();
	private Pokemon pokemonGoingFirst;
	private Pokemon pokemonGoingSecond;
	private boolean isOver = false;
	private Pokemon winner = pokemonGoingFirst;
	private int turnCount = 1;
	private Pokemon pokemonAttacking;
	private Pokemon pokemonGettingAttacked;
	private Move moveUsed;
	private int moveDamage = 0;

	public String createBattlePokemon(String input) {
		String result = "";
		Pokemon p = new Pokemon();
		if (input.equals("B")) {
			p = new Bulbasaur();
		} else if (input.equals("C")) {
			p = new Charmander();
		} else if (input.equals("S")) {
			p = new Squirtle();
		} else if (input.equals("E")) {
			p = new Eevee();
		} else if (input.equals("P")) {
			p = new Pikachu();	
		} 
		if (p.getName() != null) {
			chosenPokemon.add(p);
			result = p.getName() + ", the " + p.getType() + " type pokemon was chosen!";
		} 
		
		return result;
	}
 
	public String announceBattle() {
		return "This battle is between " + chosenPokemon.get(0) + " and " + chosenPokemon.get(1) + "!";
	}

	public String whoGoesFirst() {
		Random rand = new Random();
		int goingFirst = rand.nextInt(2);
		setWhoIsGoingFirst(goingFirst);
		return chosenPokemon.get(goingFirst) + " will go first." + "\nLet the battle begin!";
	}

	private void setWhoIsGoingFirst(int goingFirst) {
		pokemonGoingFirst = chosenPokemon.get(goingFirst);
		pokemonGoingSecond = chosenPokemon.get((goingFirst + 1) % 2);
	}

	public void battle() {
		while (!isOver) {
			pokemonTurn();
		}
	}
	
	public boolean isBattleOver() {
		return isOver;
	}

	public String pokemonTurn() {
		String result = "";
		if (turnCount % 2 != 0) {
			pokemonAttacking = pokemonGoingFirst;
			pokemonGettingAttacked = pokemonGoingSecond;
		} else {
			pokemonAttacking = pokemonGoingSecond;
			pokemonGettingAttacked = pokemonGoingFirst;
		}
		moveUsed = pokemonAttacking.pickMove();
		moveDamage = moveUsed.getDamage();
		result += "\n" + pokemonAttacking + " used " + moveUsed.getName() + "!";

		if (moveUsed.getDamage() == 0) {
			turnCount++;
			return doStatChangingMove(moveUsed);
		} else {
			moveDamage = damageDelt(moveUsed);
			result += attackingMoveTurn(moveUsed);
		}

		updateAttackedPokemonHp();
		result += determineIfBattleIsOver();
		turnCount++;
		
		return result;
	}

	private String doStatChangingMove(Move moveUsed) {
		String result = "";
		if (moveUsed.isDefenseLoweringMove()) {
			result += "\n" + pokemonAttacking + " used " + moveUsed.getName() + "!";
			result += "\n" + pokemonGettingAttacked + "'s defense fell.\n";
			pokemonAttacking.setAttackStatChange(1);
		} else if (moveUsed.isAttackLoweringMove()) {
			result += "\n" + pokemonAttacking + " used " + moveUsed.getName() + "!";
			result += "\n" + pokemonGettingAttacked + "'s attack fell.\n";
			pokemonGettingAttacked.setAttackStatChange(-1);
		}
		return result;
	}

	private int damageDelt(Move moveUsed) {
		int result = moveDamage;
		
		if (moveUsed.isSuperEffective(pokemonGettingAttacked) 
				&& moveUsed.isCriticalHit()) {
			result = (moveDamage + pokemonAttacking.getAttackStatChange()) * 3;
		} else if (moveUsed.isNotVeryEffective(pokemonGettingAttacked)
				&& moveUsed.isCriticalHit()) {
			result = (moveDamage + pokemonAttacking.getAttackStatChange());
		} else if (moveUsed.isSuperEffective(pokemonGettingAttacked)
				|| moveUsed.isCriticalHit()) {
			result = (moveDamage + pokemonAttacking.getAttackStatChange()) * 2;
		} else if (moveUsed.isNotVeryEffective(pokemonGettingAttacked)) {
			result = ((moveDamage + pokemonAttacking.getAttackStatChange()) / 2);
		} else {
			result = moveDamage + pokemonAttacking.getAttackStatChange();
		}
		if (result < 1) {
			result = 1;
		}

		return result;
	}
	
	private String attackingMoveTurn(Move moveUsed) {
		String result = "";
		if (moveUsed.isSuperEffective(pokemonGettingAttacked)) {
			result += "\nIt's super effective! ";
		}
		if (moveUsed.isNotVeryEffective(pokemonGettingAttacked)) {
			result += "\nIt's not very effective.... ";
		}
		if (moveUsed.isCriticalHit()) {
			result += "\nCritical hit! ";
		}

		result += "\nIt did " + moveDamage + " damage!";
		return result;
	}

	private void updateAttackedPokemonHp() {
		int currentHp = pokemonGettingAttacked.gethP() - (moveDamage);
		pokemonGettingAttacked.sethP(currentHp);
	}

	private String determineIfBattleIsOver() {
		String result = "";
		if (pokemonGettingAttacked.gethP() <= 0) {
			isOver = true;
			result += "\n" + pokemonGettingAttacked + " has fainted!\n";
			winner = pokemonAttacking;
		} else {
			result += "\n" + pokemonGettingAttacked + " has " + pokemonGettingAttacked.gethP() + " Hp left.\n";
		}
		return result;
	}

	public String getwinner() {
		return "The battle is over! " + winner + " has won!\nCongratulations " + winner + "!";
	}

	public List<Pokemon> printList() {
		return chosenPokemon;
	}

}
