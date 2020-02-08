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
	public String turn = "";

	public String createBattlePokemon(String input) {
		String result = "";
		if (input.equals("B")) {
			Bulbasaur newBulbasaur = new Bulbasaur();
			chosenPokemon.add(newBulbasaur);
			result = newBulbasaur.getName() + ", the " + newBulbasaur.getType() + " type pokemon was chosen!";
		} else if (input.equals("C")) {
			Charmander newCharmander = new Charmander();
			chosenPokemon.add(newCharmander);
			result = newCharmander.getName() + ", the " + newCharmander.getType() + " type pokemon was chosen!";
		} else if (input.equals("S")) {
			Squirtle newSquirtle = new Squirtle();
			chosenPokemon.add(newSquirtle);
			result = newSquirtle.getName() + ", the " + newSquirtle.getType() + " type pokemon was chosen!";
		} else if (input.equals("E")) {
			Eevee newEevee = new Eevee();
			chosenPokemon.add(newEevee);
			result = newEevee.getName() + ", the " + newEevee.getType() + " type pokemon was chosen!";
		} else if (input.equals("P")) {
			Pikachu newPikachu = new Pikachu();
			chosenPokemon.add(newPikachu);
			result = newPikachu.getName() + ", the " + newPikachu.getType() + " type pokemon was chosen!";
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
			turn += pokemonTurn(pokemonGoingFirst, pokemonGoingSecond);
			if (!isOver) {
				turn += pokemonTurn(pokemonGoingSecond, pokemonGoingFirst);
			}
		}
	}

	private String pokemonTurn(Pokemon pokemonAttacking, Pokemon pokemonGettingAttacked) {
		String result = "";
		String moveUsed = pokemonAttacking.fight();
		int moveDamage = pokemonAttacking.moveDamageMap.get(moveUsed);
		result += "\n" + pokemonAttacking + " used " + moveUsed + "!";

		if (usedStatChangingMove(pokemonAttacking, moveUsed)) {
			return doStatChangingMove(pokemonAttacking, pokemonGettingAttacked, moveUsed);
		} else {
			moveDamage = damageDelt(pokemonAttacking, pokemonGettingAttacked, moveDamage, moveUsed);
			result += attackingMoveTurn(pokemonAttacking, pokemonGettingAttacked, moveUsed, moveDamage);
		}

		updateAttackedPokemonHp(pokemonGettingAttacked, pokemonAttacking, moveDamage);
		result += determineIfBattleIsOver(pokemonAttacking, pokemonGettingAttacked);

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
			result += "\n" + pokemonGettingAttacked + " has fainted!";
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