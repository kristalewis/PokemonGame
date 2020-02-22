package com.techelevator.pokemon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Battle {

	private List<Pokemon> chosenPokemon = new ArrayList<Pokemon>();
	private Map<Integer, String> possiblePokemon = new HashMap<Integer, String>();
	private Pokemon pokemonGoingFirst;
	private Pokemon pokemonGoingSecond;
	private boolean isOver = false;
	private Pokemon winner = pokemonGoingFirst;
	private int turnCount = 1;
	private Pokemon pokemonAttacking;
	private Pokemon pokemonGettingAttacked;
	private Move moveUsed;
	private int moveDamage = 0;
	private boolean attackHit;
	private int typeOfBattle;
	private static final int COM_VS_COM = 1;
	private static final int TRAINER_VS_COM = 2;
	private static final int TRAINER_VS_TRAINER = 3;
	private boolean pokemonCreated;
	
	public String createPokemon(Battle battle, String input) {
		pokemonCreated = false;
		String result = "";
		String announcement = battle.createBattlePokemon(input);
		if (announcement.equals("")) {
			result += "That's not a valid pokemon, choose again!";
		} else {
			result += announcement;
			pokemonCreated = true;
		}
		return result;
	}
	
	public boolean pokemonCreated() {
		return pokemonCreated;
	}
	
	public String createBattlePokemon(String input) {
		String result = "";
		Pokemon pokemon = new Pokemon();
		if (input.equals("B")) {
			pokemon = new Bulbasaur();
		} else if (input.equals("C")) {
			pokemon = new Charmander();
		} else if (input.equals("S")) {
			pokemon = new Squirtle();
		} else if (input.equals("E")) {
			pokemon = new Eevee();
		} else if (input.equals("P")) {
			pokemon = new Pikachu();	
		}
		
		setIfPokemonHasTrainer(pokemon);
		setTrainerOrComNumber(pokemon);
		
		if (pokemon.getName() != null) {
			chosenPokemon.add(pokemon);
			result = pokemon.getName() + ", the " + pokemon.getType() + " type pokemon was chosen!";
		} 
	
		return result;
	}
	
	public void setTypeOfBattle(int battleType) {
		this.typeOfBattle = battleType;
	}
	
	private void setIfPokemonHasTrainer(Pokemon pokemon) {
		if (typeOfBattle == COM_VS_COM) {
			pokemon.setHasTrainer(false);
		} else if (typeOfBattle == TRAINER_VS_TRAINER) {
			pokemon.setHasTrainer(true);
		} else if (typeOfBattle == TRAINER_VS_COM && chosenPokemon.size() == 0) {
			pokemon.setHasTrainer(true);
		}
	}
	
	private void setTrainerOrComNumber(Pokemon pokemon) {
		if(chosenPokemon.size() == 0) {
			pokemon.setTrainerOrCom(1);
		} else {
			pokemon.setTrainerOrCom(2);
		}
	}
	
	private void generatePossiblePokemonMap() {
		possiblePokemon.put(0, "B");
		possiblePokemon.put(1, "C");
		possiblePokemon.put(2, "S");
		possiblePokemon.put(3, "E");
		possiblePokemon.put(4, "P");
	}
	
	public String chooseComPokemon() {
		generatePossiblePokemonMap();
		Random rand = new Random();
		int pokemonChosen = rand.nextInt(4);
		return createBattlePokemon(possiblePokemon.get(pokemonChosen));
	}
 
	public String announceBattle() {
		return "This battle is between " + chosenPokemon.get(0) + " and " + chosenPokemon.get(1) + "!";
	}

	public String whoGoesFirst() {
		String result = "";
		Random rand = new Random();
		int goingFirst = rand.nextInt(2);
		setWhoIsGoingFirst(goingFirst);
		if (pokemonGoingFirst.hasTrainer() && pokemonGoingSecond.hasTrainer()) { 
			result += "Trainer " + pokemonGoingFirst.getTrainerOrCom();
		} else if (!pokemonGoingFirst.hasTrainer() && !pokemonGoingSecond.hasTrainer()) {
			result += "Com " + pokemonGoingFirst.getTrainerOrCom();
 		} else {
 			if (pokemonGoingFirst.hasTrainer()) {
 				result += "The trainer";
 			} else {
 				result += "The com";
 			}
		}
		result += " will go first." + "\nLet the battle begin!\n";
		return result;
	}

	private void setWhoIsGoingFirst(int goingFirst) {
		pokemonGoingFirst = chosenPokemon.get(goingFirst);
		pokemonGoingSecond = chosenPokemon.get((goingFirst + 1) % 2);
	}
	
	public boolean isBattleOver() {
		return isOver;
	}
	
	public void setWhoIsAttacking() {
		if (turnCount % 2 != 0) {
			pokemonAttacking = pokemonGoingFirst;
			pokemonGettingAttacked = pokemonGoingSecond;
		} else {
			pokemonAttacking = pokemonGoingSecond;
			pokemonGettingAttacked = pokemonGoingFirst;
		}
	}
	
	public String displayMoves() {
		String result = "";
		if (pokemonAttacking.getName().equals(pokemonGettingAttacked.getName())) {
			result += "\nTrainer " + pokemonAttacking.getTrainerOrCom() + ", w";
		} else {
			result += "\nW";
		}
		result += "hat move will " + pokemonAttacking + " do?\n";
		if (pokemonAttacking.hasTrainer()) {
			for (int i = 0; i < 4; i++) { 
				result += (i + 1) + ". ";
				result += pokemonAttacking.moves.get(i).getName() + "\n";
			}
		}
		
		return result;
	}

	public String comPokemonTurn() {
		return pokemonTurn(pokemonAttacking.pickMove());
	}
	
	public String pokemonTurn(int moveChoice) {
		String result = "";
		moveUsed = pokemonAttacking.moves.get(moveChoice);
		moveDamage = moveUsed.getDamage();
		if (pokemonAttacking.getName().equals(pokemonGettingAttacked.getName()) 
			&& typeOfBattle == COM_VS_COM) {
			 result += "Com " + pokemonAttacking.getTrainerOrCom() + "'s ";
		}
		result += pokemonAttacking + " used " + moveUsed.getName() + "!";

		if (moveUsed.getDamage() == 0) {
			turnCount++;
			result += doStatChangingMove(moveUsed);
			return result;
		} else {
			moveDamage = damageDelt(moveUsed);
			result += attackingMoveTurn(moveUsed);
		}

		if (attackHit) {
			updateAttackedPokemonHp();
			result += determineIfBattleIsOver();
		}
		
		turnCount++;
		
		return result;
	}

	private String doStatChangingMove(Move moveUsed) {
		String result = "";
		if (moveUsed.attackFailed()) {
			result += "\nBut it failed.\n";
		} else {
			if (moveUsed.isDefenseLoweringMove()) {
				result += "\n" + pokemonGettingAttacked + "'s defense fell.\n";
				pokemonAttacking.setAttackStatChange(1);
			} else if (moveUsed.isAttackLoweringMove()) {
				result += "\n" + pokemonGettingAttacked + "'s attack fell.\n";
				pokemonGettingAttacked.setAttackStatChange(-1);
			}
		}
		return result;
	}

	private int damageDelt(Move moveUsed) {
		int result = moveDamage;
		moveUsed.rollForCriticalHit();
		if (moveUsed.isSuperEffective(pokemonGettingAttacked) 
				&& moveUsed.isCriticalHit()) {
			result = ((moveDamage + pokemonAttacking.getAttackStatChange()) * 3);
		} else if (moveUsed.isNotVeryEffective(pokemonGettingAttacked)
				&& moveUsed.isCriticalHit()) {
			result = (moveDamage + pokemonAttacking.getAttackStatChange());
		} else if (moveUsed.isSuperEffective(pokemonGettingAttacked)
				|| moveUsed.isCriticalHit()) {
			result = ((moveDamage + pokemonAttacking.getAttackStatChange()) * 2);
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
		if (moveUsed.attackMissed()) {
			attackHit = false;
			result += "\n" + pokemonAttacking + "'s attack missed!\n";
		} else {
			attackHit = true;
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
		}
		
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
		} else if (attackHit){
			result += "\n" + pokemonGettingAttacked + " has " + pokemonGettingAttacked.gethP() + " Hp left.\n";
		}
		return result;
	}

	public String getwinner() {
		return "The battle is over! " + winner + " has won!\nCongratulations " + winner + "!";
	}
	
	public Pokemon getPokemonAttacking() {
		return pokemonAttacking;
	}

	public List<Pokemon> printList() {
		return chosenPokemon;
	}

}
