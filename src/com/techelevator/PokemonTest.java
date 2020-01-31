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
				System.out.println("Bulbasaur, the grass type pokemon was chosen!");
				chosenPokemon.add(new Bulbasaur());
			} else if (input.equals("C")) {
				System.out.println("Charmander, the fire type pokemon was chosen!");
				chosenPokemon.add(new Charmander());
			} else if (input.equals("S")) {
				System.out.println("Squirtle, the water type pokemon was chosen!");
				chosenPokemon.add(new Squirtle());
			} else {
				System.out.println("Eevee, the normal type pokemon was chosen!");
				chosenPokemon.add(new Eevee());
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
		boolean isOver = false;
		Pokemon winner = chosenPokemon.get(goingFirst);
		
		for(int i = 0; !isOver; i = (i + 1) % 2) {
			String moveUsed = pokemonGoingFirst.fight();
			System.out.println();
			System.out.println(pokemonGoingFirst + " used " + moveUsed + "!");
			secondPokemonHp -= pokemonGoingFirst.moveDamageMap.get(moveUsed);
			System.out.println("It did " + pokemonGoingFirst.moveDamageMap.get(moveUsed) + " damage!");
			if (secondPokemonHp <= 0) {
				isOver = true;
				System.out.println(pokemonGoingSecond + " has fainted!");
				winner = pokemonGoingFirst;
				break;
			}
			System.out.println(pokemonGoingSecond + " has " + secondPokemonHp + " Hp left.");
			
			String moveUsedSecondPokemon = pokemonGoingSecond.fight();
			System.out.println();
			System.out.println(pokemonGoingSecond + " used " + moveUsedSecondPokemon + "!");
			firstPokemonHp -= pokemonGoingSecond.moveDamageMap.get(moveUsedSecondPokemon);
			System.out.println("It did " + pokemonGoingSecond.moveDamageMap.get(moveUsedSecondPokemon) + " damage!");
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
