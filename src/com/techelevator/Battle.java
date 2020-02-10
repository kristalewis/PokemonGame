package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.jndi.url.dns.dnsURLContext;

public class Battle {

	private List<Pokemon> chosenPokemon = new ArrayList<Pokemon>();
	private Pokemon pokemonGoingFirst;
	private Pokemon pokemonGoingSecond;
	public boolean isOver = false;
	private Pokemon winner = pokemonGoingFirst;
	private int turnCount = 1;
	private Pokemon pokemonAttacking;
	private Pokemon pokemonGettingAttacked;

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
		
		chosenPokemon.add(p);
		result = p.getName() + ", the " + p.getType() + " type pokemon was chosen!";
		
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

	public String pokemonTurn() {
		String result = "";
		if (turnCount % 2 != 0) {
			pokemonAttacking = pokemonGoingFirst;
			pokemonGettingAttacked = pokemonGoingSecond;
		} else {
			pokemonAttacking = pokemonGoingSecond;
			pokemonGettingAttacked = pokemonGoingFirst;
		}
		String moveUsed = pokemonAttacking.fight();
		int moveDamage = pokemonAttacking.moveDamageMap.get(moveUsed);
		result += "\n" + pokemonAttacking + " used " + moveUsed + "!";

		if (usedStatChangingMove(pokemonAttacking, moveUsed)) {
			turnCount++;
			return doStatChangingMove(pokemonAttacking, pokemonGettingAttacked, moveUsed);
		} else {
			moveDamage = damageDelt(pokemonAttacking, pokemonGettingAttacked, moveDamage, moveUsed);
			result += attackingMoveTurn(pokemonAttacking, pokemonGettingAttacked, moveUsed, moveDamage);
		}

		updateAttackedPokemonHp(pokemonGettingAttacked, pokemonAttacking, moveDamage);
		result += determineIfBattleIsOver(pokemonAttacking, pokemonGettingAttacked);
		turnCount++;
		
		return result;
	}

	private boolean usedStatChangingMove(Pokemon pokemonAttacking, String moveUsed) {
		return pokemonAttacking.usedDefenseLoweringMove(moveUsed) || pokemonAttacking.usedAttackLoweringMove(moveUsed);
	}

	private String doStatChangingMove(Pokemon pokemonAttacking, Pokemon pokemonGettingAttacked, String moveUsed) {
		String result = "";
		if (pokemonAttacking.usedDefenseLoweringMove(moveUsed)) {
			result += "\n" + pokemonAttacking + " used " + moveUsed + "!";
			result += "\n" + pokemonGettingAttacked + "'s defense fell.\n";
			pokemonAttacking.setAttackStatChange(1);
		} else if (pokemonAttacking.usedAttackLoweringMove(moveUsed)) {
			result += "\n" + pokemonAttacking + " used " + moveUsed + "!";
			result += "\n" + pokemonGettingAttacked + "'s attack fell.\n";
			pokemonGettingAttacked.setAttackStatChange(-1);
		}
		return result;
	}

	private int damageDelt(Pokemon pokemonAttacking, Pokemon pokemonGettingAttacked, int moveDamage, String moveUsed) {
		int result = moveDamage;
		
		if (pokemonAttacking.isSuperEffective(moveUsed, pokemonGettingAttacked) && pokemonAttacking.isCriticalHit()) {
			result = (moveDamage + pokemonAttacking.getAttackStatChange()) * 3;
		} else if (pokemonAttacking.isNotVeryEffective(moveUsed, pokemonGettingAttacked)
				&& pokemonAttacking.isCriticalHit()) {
			result = (moveDamage + pokemonAttacking.getAttackStatChange());
		} else if (pokemonAttacking.isSuperEffective(moveUsed, pokemonGettingAttacked)
				|| pokemonAttacking.isCriticalHit()) {
			result = (moveDamage + pokemonAttacking.getAttackStatChange()) * 2;
		} else if (pokemonAttacking.isNotVeryEffective(moveUsed, pokemonGettingAttacked)) {
			result = ((moveDamage + pokemonAttacking.getAttackStatChange()) / 2);
		} else {
			result = moveDamage + pokemonAttacking.getAttackStatChange();
		}
		if (result < 1) {
			result = 1;
		}

		return result;
	}
	
	private String attackingMoveTurn(Pokemon pokemonAttacking, Pokemon pokemonGettingAttacked, String moveUsed,
			int moveDamage) {
		String result = "";
		if (pokemonAttacking.isSuperEffective(moveUsed, pokemonGettingAttacked)) {
			result += "\nIt's super effective! ";
		}
		if (pokemonAttacking.isNotVeryEffective(moveUsed, pokemonGettingAttacked)) {
			result += "\nIt's not very effective.... ";
		}
		if (pokemonAttacking.isCriticalHit()) {
			result += "\nCritical hit! ";
		}

		result += "\nIt did " + moveDamage + " damage!";
		return result;
	}

	private void updateAttackedPokemonHp(Pokemon pokemonGettingAttacked, Pokemon pokemonAttacking, int moveDamage) {
		int currentHp = pokemonGettingAttacked.gethP() - (moveDamage);
		pokemonGettingAttacked.sethP(currentHp);
	}

	private String determineIfBattleIsOver(Pokemon pokemonAttacking, Pokemon pokemonGettingAttacked) {
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
