package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PokemonTest {

	public static void main(String[] args) {
	
		List<Pokemon> chosenPokemon = new ArrayList<Pokemon>();
		
		Scanner scan = new Scanner(System.in);
		
		for(int i = 1; i < 3; i++) {
			System.out.println("Choose pokemon number " + i + "!");
			System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee? ");
			String input = scan.nextLine();
			if (input.equals("B")) {
				Bulbasaur newBulbasaur = new Bulbasaur();
				chosenPokemon.add(newBulbasaur);
				System.out.println(newBulbasaur.getName() + ", the " + newBulbasaur.getType() + " type pokemon was chosen!");
			} else if (input.equals("C")) {
				Charmander newCharmander = new Charmander();
				chosenPokemon.add(newCharmander);
				System.out.println(newCharmander.getName() + ", the " + newCharmander.getType() + " type pokemon was chosen!");
			} else if (input.equals("S")) {
				Squirtle newSquirtle = new Squirtle();
				chosenPokemon.add(newSquirtle);
				System.out.println(newSquirtle.getName() + ", the " + newSquirtle.getType() + " type pokemon was chosen!");
			} else {
				Eevee newEevee = new Eevee();
				chosenPokemon.add(newEevee);
				System.out.println(newEevee.getName() + ", the " + newEevee.getType() + " type pokemon was chosen!");
			}
			System.out.println();
		}
		
		System.out.println("This battle is between " + chosenPokemon.get(0) + " and " + chosenPokemon.get(1) + "!");
		System.out.println();
		
		Random rand = new Random();
		int goingFirst = rand.nextInt(2);
		System.out.println(chosenPokemon.get(goingFirst) + " will go first.");
		System.out.println("Let the battle begin!");
		
		Pokemon pokemonGoingFirst = chosenPokemon.get(goingFirst);
		Pokemon pokemonGoingSecond = chosenPokemon.get((goingFirst + 1) % 2);
		int firstPokemonHp = chosenPokemon.get(goingFirst).gethP();
		int secondPokemonHp = chosenPokemon.get((goingFirst + 1)% 2).gethP();
		int firstPokemonAttackStatChange = 0;
		int secondPokemonAttackStatChange = 0;
		boolean isOver = false;
		Pokemon winner = chosenPokemon.get(goingFirst);
		
		for(int i = 0; !isOver; i = (i + 1) % 2) {
			String moveUsed = pokemonGoingFirst.fight();
			int moveDamage = pokemonGoingFirst.moveDamageMap.get(moveUsed);
			System.out.println();
			System.out.println(pokemonGoingFirst + " used " + moveUsed + "!");
			
			if (pokemonGoingFirst.usedDefenseLoweringMove(moveUsed)) {
				System.out.println(pokemonGoingSecond + "'s defense fell.");
				firstPokemonAttackStatChange++;
				continue;
			} else if (pokemonGoingFirst.usedAttackLoweringMove(moveUsed)) {
				System.out.println(pokemonGoingSecond + "'s attack fell.");
				secondPokemonAttackStatChange--;
				continue;
			} else {
				if (pokemonGoingFirst.isSuperEffective(moveUsed, pokemonGoingSecond)) {
					System.out.print("It's super effective! ");
					if (moveDamage + firstPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage + firstPokemonAttackStatChange) * 2) + " damage!");
					}
				} else if (pokemonGoingFirst.isNotVeryEffective(moveUsed, pokemonGoingSecond)) {
					System.out.print("It's not very effective.... ");
					if (moveDamage + firstPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage + firstPokemonAttackStatChange) / 2) + " damage!");
					}
				} else if(pokemonGoingSecond.isCriticalHit()) {
					System.out.print("Critical hit! ");
					if (moveDamage + firstPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage + firstPokemonAttackStatChange) * 2) + " damage!");
					}
				} else if (pokemonGoingFirst.isSuperEffective(moveUsed, pokemonGoingSecond) && pokemonGoingSecond.isCriticalHit()) {
					System.out.println("Critical hit!");
					System.out.print("It's super effective! ");
					if (moveDamage + firstPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage + firstPokemonAttackStatChange) * 2) + " damage!");
					}
				} else if (pokemonGoingFirst.isNotVeryEffective(moveUsed, pokemonGoingSecond) && pokemonGoingSecond.isCriticalHit()) {
					System.out.println("Critical hit!");
					System.out.print("It's not very effective... ");
					if (moveDamage + firstPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage + firstPokemonAttackStatChange) * 2) + " damage!");
					}
				} else {
					System.out.println("It did " + (moveDamage + firstPokemonAttackStatChange) + " damage!");
				}
			}
			secondPokemonHp -= pokemonGoingFirst.moveDamageMap.get(moveUsed);
			if (secondPokemonHp <= 0) {
				isOver = true;
				System.out.println(pokemonGoingSecond + " has fainted!");
				winner = pokemonGoingFirst;
				break;
			}
			System.out.println(pokemonGoingSecond + " has " + secondPokemonHp + " Hp left.");
			
			String moveUsedSecondPokemon = pokemonGoingSecond.fight();
			int moveDamage2 = pokemonGoingSecond.moveDamageMap.get(moveUsedSecondPokemon);
			System.out.println();
			System.out.println(pokemonGoingSecond + " used " + moveUsedSecondPokemon + "!");
			if (pokemonGoingSecond.usedDefenseLoweringMove(moveUsedSecondPokemon)) {
				System.out.println(pokemonGoingFirst + "'s defense fell.");
				secondPokemonAttackStatChange++;
				continue;
			} else if (pokemonGoingSecond.usedAttackLoweringMove(moveUsedSecondPokemon)) {
				System.out.println(pokemonGoingFirst + "'s attack fell.");
				firstPokemonAttackStatChange--;
				continue;
			} else {
				if (pokemonGoingSecond.isSuperEffective(moveUsedSecondPokemon, pokemonGoingFirst)) {
					System.out.print("It's super effective! ");
					if (moveDamage2 + secondPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage2 + secondPokemonAttackStatChange) * 2) + " damage!");
					}
				} else if (pokemonGoingSecond.isNotVeryEffective(moveUsedSecondPokemon, pokemonGoingFirst)) {
					System.out.print("It's not very effective... ");
					if (moveDamage2 + secondPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage2 + secondPokemonAttackStatChange) / 2) + " damage!");
					}
				} else if(pokemonGoingSecond.isCriticalHit()) {
					System.out.print("Critical hit! ");
					if (moveDamage2 + secondPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage2 + secondPokemonAttackStatChange) * 2) + " damage!");
					}
				} else if (pokemonGoingSecond.isSuperEffective(moveUsedSecondPokemon, pokemonGoingFirst) && pokemonGoingSecond.isCriticalHit()) {
					System.out.println("Critical hit!");
					System.out.print("It's super effective! ");
					if (moveDamage + secondPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + ((moveDamage + secondPokemonAttackStatChange) * 4) + " damage!");
					}
				} else if (pokemonGoingSecond.isNotVeryEffective(moveUsedSecondPokemon, pokemonGoingFirst) && pokemonGoingSecond.isCriticalHit()) {
					System.out.println("Critical hit!");
					System.out.print("It's not very effective... ");
					if (moveDamage + secondPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + (moveDamage + secondPokemonAttackStatChange) + " damage!");
					}
				} else {
					if (moveDamage + secondPokemonAttackStatChange < 1) {
						System.out.println("It did 1 damage!");
					} else {
						System.out.println("It did " + (moveDamage + secondPokemonAttackStatChange) + " damage!");
					}
				}
			}
			firstPokemonHp -= pokemonGoingSecond.moveDamageMap.get(moveUsedSecondPokemon);
			if(firstPokemonHp <= 0) {
				isOver = true;
				System.out.println(pokemonGoingFirst + " has fainted!");
				winner = pokemonGoingSecond;
				break;
			}
			
			System.out.println(pokemonGoingFirst + " has " + firstPokemonHp + " Hp left.");
		}
		
		System.out.println();
		System.out.println("The battle is over! " + winner + " has won!");
		System.out.println("Congradulations " + winner + "!");
		
		scan.close();
	}

}
