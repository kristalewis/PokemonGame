package com.techelevator;

import java.util.Scanner;

public class PokemonCLI {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		Battle battle = new Battle();
		
		for(int i = 1; i < 3; i++) {
			System.out.println("Choose pokemon number " + i + "!");
			System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee, or (P)ikachu? ");
			String input = scan.nextLine();
			String announcement = battle.createBattlePokemon(input);
			if (announcement.equals("")) {
				i--;
				System.out.println("That's not a valid pokemon, choose again!");
			} else {
				System.out.println(announcement);
			}
			
			System.out.println();
		}
		
		System.out.println(battle.announceBattle());
		System.out.println();
		System.out.println(battle.whoGoesFirst());
		
		while (!battle.isOver) {
			Thread.sleep(2000);
			System.out.print(battle.pokemonTurn());
		}
		
		System.out.println();
		System.out.println(battle.getwinner());
		
		scan.close();
	}

}
