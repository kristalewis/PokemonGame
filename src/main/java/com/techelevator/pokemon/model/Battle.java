package com.techelevator.pokemon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.techelevator.pokemon.move.model.Move;

public class Battle {

	private List<Pokemon> battlingPokemon = new ArrayList<Pokemon>();
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
	public static final int COM_VS_COM = 1;
	public static final int TRAINER_VS_COM = 2;
	public static final int TRAINER_VS_TRAINER = 3;
	public static final String[] BATTLE_TYPES = {"Computer Vs. Computer", "Trainer Vs. Computer",
												  "Trainer Vs. Trainer"};
	
	public List<Pokemon> getBattlingPokemon() {
		return battlingPokemon;
	}
	
	public void setTypeOfBattle(int battleType) {
		this.typeOfBattle = battleType;
	}
	
	public int getTypeOfBattle() {
		return this.typeOfBattle;
	}
	
	public void addPokemonToBattle(Pokemon chosenPokemon) {
		battlingPokemon.add(chosenPokemon);
	}
	
	public void setIfPokemonHasTrainer(Pokemon pokemon) {
		if (typeOfBattle == COM_VS_COM) {
			pokemon.setHasTrainer(false);
		} else if (typeOfBattle == TRAINER_VS_TRAINER) {
			pokemon.setHasTrainer(true);
		} else if (typeOfBattle == TRAINER_VS_COM && battlingPokemon.size() == 1) {
			pokemon.setHasTrainer(true);
		}
	}
	
	public void setTrainerOrComNumber(Pokemon pokemon) {
		if (battlingPokemon.size() == 1) {
			pokemon.setTrainerOrCom(1);
		} else {
			pokemon.setTrainerOrCom(2);
		}
	}
	
	public int chooseComPokemon() {
		Random rand = new Random();
		return (rand.nextInt(5) + 1);
	}
 
	public String announceBattle() {
		return "This battle is between " + battlingPokemon.get(0).getName() + " and " + battlingPokemon.get(1).getName() + "!";
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
		pokemonGoingFirst = battlingPokemon.get(goingFirst);
		pokemonGoingSecond = battlingPokemon.get((goingFirst + 1) % 2);
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
	
	public String displayMoves(List<Move> moves) {
		String result = "";
		if (pokemonAttacking.getName().equals(pokemonGettingAttacked.getName()) && typeOfBattle == 3) {
			result += "\nTrainer " + pokemonAttacking.getTrainerOrCom() + ", w";
		} else {
			result += "W";
		}
		result += "hat move will " + pokemonAttacking.getName() + " do?\n";
		if (pokemonAttacking.hasTrainer()) {
			for (int i = 0; i < 4; i++) { 
				result += (i + 1) + ". ";
				result += moves.get(i).getName() + "\n";
			}
		}
		
		return result;
	}
	
	public String pokemonTurn(Move moveUsed) {
		String result = "";
		this.moveUsed = moveUsed;
		moveDamage = moveUsed.getDamage();
		if (pokemonAttacking.getName().equals(pokemonGettingAttacked.getName()) 
			&& typeOfBattle == COM_VS_COM) {
			 result += "Com " + pokemonAttacking.getTrainerOrCom() + "'s ";
		}
		result += pokemonAttacking.getName() + " used " + moveUsed.getName() + "!";
		System.out.println(moveUsed.getMoveType());
		if (moveUsed.isStatChangingMove()) {
			System.out.println("Hit branch");
			turnCount++;
			result += doStatChangingMove(moveUsed);
			return result;
		} else if (moveUsed.isNoEffectMove()) {
			result += "\nBut it had no effect.\n";
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
				result += "\n" + pokemonGettingAttacked.getName() + "'s defense fell.\n";
				pokemonAttacking.setAttackStatChange(1);
			} else if (moveUsed.isAttackLoweringMove()) {
				result += "\n" + pokemonGettingAttacked.getName() + "'s attack fell.\n";
				pokemonGettingAttacked.setAttackStatChange(-1);
			} else if (moveUsed.isAccuracyLoweringMove()) {
				result += "\n" + pokemonGettingAttacked.getName() + "'s accuracy fell.\n";
				pokemonGettingAttacked.setAccuracyStat(-1);
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
		if (moveUsed.attackMissed(pokemonAttacking.getAccuracyStat())) {
			attackHit = false;
			result += "\n" + pokemonAttacking.getName() + "'s attack missed!\n";
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
			result += "\n" + pokemonGettingAttacked.getName() + " has fainted!\n";
			winner = pokemonAttacking;
		} else if (attackHit){
			result += "\n" + pokemonGettingAttacked.getName() + " has " + pokemonGettingAttacked.gethP() + " Hp left.\n";
		}
		return result;
	}

	public String getwinner() {
		return "The battle is over! " + winner.getName() + " has won!\nCongratulations " + winner.getName() + "!";
	}
	
	public Pokemon getWinningPokemon() {
		return winner;
	}
	
	public Pokemon getPokemonAttacking() {
		return pokemonAttacking;
	}
	
	public Pokemon getPokemonGettingAttacked() {
		return pokemonGettingAttacked;
	}


}
